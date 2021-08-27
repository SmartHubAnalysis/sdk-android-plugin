/*
 * Created by wangzhuozhou on 2015/08/01.
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

package com.sendata.analytics.android.sdk;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;

import com.sendata.analytics.android.sdk.util.ReflectUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

/* package */ class AppWebViewInterface {
    private static final String TAG = "SA.AppWebViewInterface";
    private Context mContext;
    private JSONObject properties;
    private boolean enableVerify;
    private WeakReference<View> mWebView;


    AppWebViewInterface(Context c, JSONObject p, boolean b) {
        this(c, p, b, null);
    }

    AppWebViewInterface(Context c, JSONObject p, boolean b, View view) {
        this.mContext = c;
        this.properties = p;
        this.enableVerify = b;
        if (view != null) {
            this.mWebView = new WeakReference<>(view);
        }
    }

    @JavascriptInterface
    public String sendata_call_app() {
        try {
            if (properties == null) {
                properties = new JSONObject();
            }
            properties.put("type", "Android");
            String loginId = SendataAPI.sharedInstance(mContext).getLoginId();
            if (!TextUtils.isEmpty(loginId)) {
                properties.put("distinct_id", loginId);
                properties.put("is_login", true);
            } else {
                properties.put("distinct_id", SendataAPI.sharedInstance(mContext).getAnonymousId());
                properties.put("is_login", false);
            }
            return properties.toString();
        } catch (JSONException e) {
            SALog.i(TAG, e.getMessage());
        }
        return null;
    }

    @JavascriptInterface
    public void sendata_track(String event) {
        try {
            SendataAPI.sharedInstance(mContext).trackEventFromH5(event, enableVerify);
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    @JavascriptInterface
    public boolean sendata_verify(String event) {
        try {
            if (!enableVerify) {
                sendata_track(event);
                return true;
            }
            return SendataAPI.sharedInstance(mContext)._trackEventFromH5(event);
        } catch (Exception e) {
            SALog.printStackTrace(e);
            return false;
        }
    }

    @JavascriptInterface
    public String sendata_get_server_url() {
        return SendataAPI.sharedInstance().getConfigOptions().isAutoTrackWebView ? SendataAPI.sharedInstance().getServerUrl() : "";
    }

    /**
     * 解决用户只调用了 showUpWebView 方法时，此时 App 校验 url。JS 需要拿到 App 校验结果。
     *
     * @param event
     * @return
     */
    @JavascriptInterface
    public boolean sendata_visual_verify(String event) {
        try {
            if (!enableVerify) {
                return true;
            }
            if (TextUtils.isEmpty(event)) {
                return false;
            }
            JSONObject eventObject = new JSONObject(event);
            String serverUrl = eventObject.optString("server_url");
            if (!TextUtils.isEmpty(serverUrl)) {
                if (!(new ServerUrl(serverUrl).check(new ServerUrl(SendataAPI.sharedInstance().getServerUrl())))) {
                    return false;
                }
                return true;
            }
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
        return false;
    }

    /**
     * 新打通方案下 js 调用 app 的通道,该接口可复用
     *
     * @param content JS 发送的消息
     */
    @JavascriptInterface
    public void sendata_js_call_app(final String content) {
        try {
            if (mWebView != null) {
                SendataAPI.sharedInstance().handleJsMessage(mWebView,content);
            }
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    /**
     * 判断 A/B Testing 是否初始化
     *
     * @return A/B Testing SDK 是否初始化
     */
    @JavascriptInterface
    public boolean sendata_abtest_module() {
        try {
            Class<?> sendataABTestClass = ReflectUtil.getCurrentClass(new String[]{"com.sendata.abtest.SendataABTest"});
            Object object = ReflectUtil.callStaticMethod(sendataABTestClass, "shareInstance");
            return object != null;
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
        return false;
    }
}
