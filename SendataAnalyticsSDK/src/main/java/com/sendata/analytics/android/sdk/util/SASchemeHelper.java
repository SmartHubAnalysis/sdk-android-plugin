/*
 * Created by yuejianzhong on 2020/11/04.
 * Copyright 2015－2021 Sensors Data Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sendata.analytics.android.sdk.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

import com.sendata.analytics.android.sdk.SAConfigOptions;
import com.sendata.analytics.android.sdk.SALog;
import com.sendata.analytics.android.sdk.SendataAPI;
import com.sendata.analytics.android.sdk.SendataAutoTrackHelper;
import com.sendata.analytics.android.sdk.ServerUrl;
import com.sendata.analytics.android.sdk.aop.push.PushAutoTrackHelper;
import com.sendata.analytics.android.sdk.dialog.SendataDialogUtils;
import com.sendata.analytics.android.sdk.remote.BaseSendataSDKRemoteManager;
import com.sendata.analytics.android.sdk.remote.SendataRemoteManagerDebug;

public class SASchemeHelper {

    private final static String TAG = "SA.SASchemeUtil";

    public static void handleSchemeUrl(Activity activity, Intent intent) {
        if (SendataAPI.isSDKDisabled()) {
            SALog.i(TAG, "SDK is disabled,scan code function has been turned off");
            return;
        }
        try {
            PushAutoTrackHelper.trackJPushOpenActivity(intent);
            Uri uri = null;
            if (activity != null && intent != null) {
                uri = intent.getData();
            }
            if (uri != null) {
                String host = uri.getHost();
                if ("heatmap".equals(host)) {
                    String featureCode = uri.getQueryParameter("feature_code");
                    String postUrl = uri.getQueryParameter("url");
                    if (checkProjectIsValid(postUrl)) {
                        SendataDialogUtils.showOpenHeatMapDialog(activity, featureCode, postUrl);
                    } else {
                        SendataDialogUtils.showDialog(activity, "App 集成的项目与电脑浏览器打开的项目不同，无法进行点击分析");
                    }
                    intent.setData(null);
                } else if ("debugmode".equals(host)) {
                    String infoId = uri.getQueryParameter("info_id");
                    String locationHref = uri.getQueryParameter("sf_push_distinct_id");
                    String project = uri.getQueryParameter("project");
                    SendataDialogUtils.showDebugModeSelectDialog(activity, infoId, locationHref, project);
                    intent.setData(null);
                } else if ("visualized".equals(host)) {
                    String featureCode = uri.getQueryParameter("feature_code");
                    String postUrl = uri.getQueryParameter("url");
                    if (checkProjectIsValid(postUrl)) {
                        SendataDialogUtils.showOpenVisualizedAutoTrackDialog(activity, featureCode, postUrl);
                    } else {
                        SendataDialogUtils.showDialog(activity, "App 集成的项目与电脑浏览器打开的项目不同，无法进行可视化全埋点。");
                    }
                    intent.setData(null);
                } else if ("popupwindow".equals(host)) {
                    SendataDialogUtils.showPopupWindowDialog(activity, uri);
                    intent.setData(null);
                } else if ("encrypt".equals(host)) {
                    String version = uri.getQueryParameter("v");
                    String key = Uri.decode(uri.getQueryParameter("key"));
                    SALog.d(TAG, "Encrypt, version = " + version + ", key = " + key);
                    String tip;
                    if (TextUtils.isEmpty(version) || TextUtils.isEmpty(key)) {
                        tip = "密钥验证不通过，所选密钥无效";
                    } else if (SendataAPI.sharedInstance().getSendataEncrypt() != null) {
                        tip = SendataAPI.sharedInstance().getSendataEncrypt().checkPublicSecretKey(version, key);
                    } else {
                        tip = "当前 App 未开启加密，请开启加密后再试";
                    }
                    Toast.makeText(activity, tip, Toast.LENGTH_LONG).show();
                    SendataDialogUtils.startLaunchActivity(activity);
                    intent.setData(null);
                } else if ("channeldebug".equals(host)) {
                    if (ChannelUtils.hasUtmByMetaData(activity)) {
                        SendataDialogUtils.showDialog(activity, "当前为渠道包，无法使用联调诊断工具");
                        return;
                    }

                    String monitorId = uri.getQueryParameter("monitor_id");
                    if (TextUtils.isEmpty(monitorId)) {
                        SendataDialogUtils.startLaunchActivity(activity);
                        return;
                    }
                    String url = SendataAPI.sharedInstance().getServerUrl();
                    if (TextUtils.isEmpty(url)) {
                        SendataDialogUtils.showDialog(activity, "数据接收地址错误，无法使用联调诊断工具");
                        return;
                    }
                    ServerUrl serverUrl = new ServerUrl(url);
                    String projectName = uri.getQueryParameter("project_name");
                    if (serverUrl.getProject().equals(projectName)) {
                        String projectId = uri.getQueryParameter("project_id");
                        String accountId = uri.getQueryParameter("account_id");
                        String isReLink = uri.getQueryParameter("is_relink");
                        if ("1".equals(isReLink)) {//续连标识 1 :续连
                            String deviceCode = uri.getQueryParameter("device_code");
                            if (ChannelUtils.checkDeviceInfo(activity, deviceCode)) {//比较设备信息是否匹配
                                SendataAutoTrackHelper.showChannelDebugActiveDialog(activity);
                            } else {
                                SendataDialogUtils.showDialog(activity, "无法重连，请检查是否更换了联调手机");
                            }
                        } else {
                            SendataDialogUtils.showChannelDebugDialog(activity, serverUrl.getBaseUrl(), monitorId, projectId, accountId);
                        }
                    } else {
                        SendataDialogUtils.showDialog(activity, "App 集成的项目与电脑浏览器打开的项目不同，无法使用联调诊断工具");
                    }
                    intent.setData(null);
                } else if ("abtest".equals(host)) {
                    try {
                        ReflectUtil.callStaticMethod(Class.forName("com.sendata.abtest.core.SendataABTestSchemeHandler"), "handleSchemeUrl", uri.toString());
                    } catch (Exception e) {
                        SALog.printStackTrace(e);
                    }
                    SendataDialogUtils.startLaunchActivity(activity);
                    intent.setData(null);
                } else if ("sendataremoteconfig".equals(host)) {
                    // 开启日志
                    SendataAPI.sharedInstance().enableLog(true);
                    BaseSendataSDKRemoteManager sendataSDKRemoteManager = SendataAPI.sharedInstance().getRemoteManager();
                    // 取消重试
                    if (sendataSDKRemoteManager != null) {
                        sendataSDKRemoteManager.resetPullSDKConfigTimer();
                    }
                    final SendataRemoteManagerDebug sendataRemoteManagerDebug =
                            new SendataRemoteManagerDebug(SendataAPI.sharedInstance());
                    // 替换为 SendataRemoteManagerDebug 对象
                    SendataAPI.sharedInstance().setRemoteManager(sendataRemoteManagerDebug);
                    // 验证远程配置
                    SALog.i(TAG, "Start debugging remote config");
                    sendataRemoteManagerDebug.checkRemoteConfig(uri, activity);
                    intent.setData(null);
                } else if ("assistant".equals(host)) {
                    SAConfigOptions configOptions = SendataAPI.getConfigOptions();
                    if (configOptions != null && configOptions.mDisableDebugAssistant) {
                        return;
                    }
                    String service = uri.getQueryParameter("service");
                    if ("pairingCode".equals(service)) {
                        SendataDialogUtils.showPairingCodeInputDialog(activity);
                    }
                } else {
                    SendataDialogUtils.startLaunchActivity(activity);
                }
            }
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    private static boolean checkProjectIsValid(String url) {
        String serverUrl = SendataAPI.sharedInstance().getServerUrl();
        String sdkProject = null, serverProject = null;
        if (!TextUtils.isEmpty(url)) {
            Uri schemeUri = Uri.parse(url);
            if (schemeUri != null) {
                sdkProject = schemeUri.getQueryParameter("project");
            }
        }
        if (!TextUtils.isEmpty(serverUrl)) {
            Uri serverUri = Uri.parse(serverUrl);
            if (serverUri != null) {
                serverProject = serverUri.getQueryParameter("project");
            }
        }
        return !TextUtils.isEmpty(sdkProject) && !TextUtils.isEmpty(serverProject) && TextUtils.equals(sdkProject, serverProject);
    }
}
