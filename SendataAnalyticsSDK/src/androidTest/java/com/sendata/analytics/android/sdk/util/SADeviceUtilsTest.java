package com.sendata.analytics.android.sdk.util;

import android.Manifest;
import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.GrantPermissionRule;

import com.sendata.analytics.android.sdk.SAConfigOptions;
import com.sendata.analytics.android.sdk.SendataAPI;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class SADeviceUtilsTest {
    @Rule
    public GrantPermissionRule grantPermissionRule = GrantPermissionRule.grant(Manifest.permission.READ_PHONE_STATE);

    @Before
    public void initSendataAPI() {
        Context context = ApplicationProvider.getApplicationContext();
        SendataAPI.sharedInstance(context, new SAConfigOptions("").enableLog(true));
    }

    /**
     * 需集成 oaid 的 aar 包
     */
    @Test
    public void getOAID() {
        try {
            String oaid = OaidHelper.getOAID(ApplicationProvider.getApplicationContext());
            assertNull(oaid);
            SendataAPI.sharedInstance().trackInstallation("AppInstall");
        } catch (Exception ex) {
            //ignore
        }
    }
}
