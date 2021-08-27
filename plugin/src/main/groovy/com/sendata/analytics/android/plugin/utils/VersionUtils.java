package com.sendata.analytics.android.plugin.utils;

import com.sendata.analytics.android.plugin.Logger;

import java.lang.reflect.Field;
import java.net.URLClassLoader;

public class VersionUtils {
    // 是否打开 TV 开关
    public static boolean isAndroidTv;
    // Smarthub埋点 SDK 版本号
    public static String sendataSDKVersion = "";

    /**
     * 是否是 TV 版本
     * @return true 是，false 不是
     */
    public static boolean isTvVersion() {
        return isAndroidTv && sendataSDKVersion.endsWith("tv");
    }

    /**
     * 读取Smarthub Android 埋点 SDK 版本号
     * @param urlClassLoader ClassLoader
     */
    public static void loadAndroidSDKVersion(URLClassLoader urlClassLoader) {
        try {
            Class sendataAPI = urlClassLoader.loadClass("com.sendata.analytics.android.sdk.SendataAPI");
            Field versionField = sendataAPI.getDeclaredField("VERSION");
            versionField.setAccessible(true);
            sendataSDKVersion = (String) versionField.get(null);
            Logger.info("Smarthub埋点 SDK 版本号:" + sendataSDKVersion);
        } catch(Throwable throwable) {
            Logger.info("Smarthub埋点 SDK 版本号读取失败，reason: " + throwable.getMessage());
        }
    }
}
