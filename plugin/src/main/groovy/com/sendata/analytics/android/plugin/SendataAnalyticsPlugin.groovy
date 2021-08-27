/*
 * Created by wangzhuozhou on 2015/08/12.
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
package com.sendata.analytics.android.plugin

import com.android.build.gradle.AppExtension
import com.sendata.analytics.android.plugin.utils.VersionUtils
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.internal.reflect.Instantiator
import org.gradle.invocation.DefaultGradle

class SendataAnalyticsPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        Instantiator ins = ((DefaultGradle) project.getGradle()).getServices().get(Instantiator)
        def args = [ins] as Object[]
        SendataAnalyticsExtension extension = project.extensions.create("sendataAnalytics", SendataAnalyticsExtension, args)

        boolean disableSendataAnalyticsPlugin = false
        boolean disableSendataAnalyticsMultiThreadBuild = false
        boolean disableSendataAnalyticsIncrementalBuild = false
        boolean isHookOnMethodEnter = false
        boolean isAndroidTv = false
        Properties properties = new Properties()
        if (project.rootProject.file('gradle.properties').exists()) {
            properties.load(project.rootProject.file('gradle.properties').newDataInputStream())
            disableSendataAnalyticsPlugin = Boolean.parseBoolean(properties.getProperty("sendataAnalytics.disablePlugin", "false")) ||
                    Boolean.parseBoolean(properties.getProperty("disableSendataAnalyticsPlugin", "false"))
            disableSendataAnalyticsMultiThreadBuild = Boolean.parseBoolean(properties.getProperty("sendataAnalytics.disableMultiThreadBuild", "false"))
            disableSendataAnalyticsIncrementalBuild = Boolean.parseBoolean(properties.getProperty("sendataAnalytics.disableIncrementalBuild", "false"))
            isHookOnMethodEnter = Boolean.parseBoolean(properties.getProperty("sendataAnalytics.isHookOnMethodEnter", "false"))
            isAndroidTv = Boolean.parseBoolean(properties.getProperty("sendataAnalytics.isAndroidTv", "false"))
        }
        if (!disableSendataAnalyticsPlugin) {
            AppExtension appExtension = project.extensions.findByType(AppExtension.class)
            SendataAnalyticsTransformHelper transformHelper = new SendataAnalyticsTransformHelper(extension, appExtension)
            transformHelper.disableSendataAnalyticsIncremental = disableSendataAnalyticsIncrementalBuild
            transformHelper.disableSendataAnalyticsMultiThread = disableSendataAnalyticsMultiThreadBuild
            transformHelper.isHookOnMethodEnter = isHookOnMethodEnter
            VersionUtils.isAndroidTv = isAndroidTv
            appExtension.registerTransform(new SendataAnalyticsTransform(transformHelper))
        } else {
            Logger.error("------------您已关闭了Smarthub插件--------------")
        }

    }
}