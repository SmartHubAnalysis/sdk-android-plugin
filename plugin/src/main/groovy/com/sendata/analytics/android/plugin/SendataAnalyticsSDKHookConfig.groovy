/*
 * Created by renqingyou on 2018/12/01.
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

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.Opcodes


class SendataAnalyticsSDKHookConfig {

    HashMap<String, HashMap<String, ArrayList<SendataAnalyticsMethodCell>>> methodCells = new HashMap<>()

    void disableIMEI(String methodName) {
        def imei = new SendataAnalyticsMethodCell('getIMEI', '(Landroid/content/Context;)Ljava/lang/String;', 'createGetIMEI')
        def deviceID = new SendataAnalyticsMethodCell('getDeviceID', '(Landroid/content/Context;I)Ljava/lang/String;', 'createGetDeviceID')
        def imeiMethods = [imei, deviceID]
        def imeiMethodCells = new HashMap<String, ArrayList<SendataAnalyticsMethodCell>>()
        imeiMethodCells.put("com/sendata/analytics/android/sdk/util/SendataUtils", imeiMethods)
        methodCells.put(methodName, imeiMethodCells)
    }

    void disableAndroidID(String methodName) {
        def androidID = new SendataAnalyticsMethodCell('getAndroidID', '(Landroid/content/Context;)Ljava/lang/String;', 'createGetAndroidID')
        def androidIDMethods = [androidID]
        def androidIdMethodCells = new HashMap<String, ArrayList<SendataAnalyticsMethodCell>>()
        androidIdMethodCells.put('com/sendata/analytics/android/sdk/util/SendataUtils', androidIDMethods)
        methodCells.put(methodName, androidIdMethodCells)
    }

    void disableLog(String methodName) {
        def info = new SendataAnalyticsMethodCell('info', '(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V', "createSALogInfo")
        def printStackTrace = new SendataAnalyticsMethodCell('printStackTrace', '(Ljava/lang/Exception;)V', "createPrintStackTrack")
        def sALogMethods = [info, printStackTrace]
        def sALogMethodCells = new HashMap<String, ArrayList<SendataAnalyticsMethodCell>>()
        sALogMethodCells.put('com/sendata/analytics/android/sdk/SALog', sALogMethods)
        methodCells.put(methodName, sALogMethodCells)
    }

    void disableJsInterface(String methodName) {
        def showUpWebView = new SendataAnalyticsMethodCell("showUpWebView", '(Landroid/webkit/WebView;Lorg/json/JSONObject;ZZ)V', "createShowUpWebViewFour")
        def showUpX5WebView = new SendataAnalyticsMethodCell("showUpX5WebView", '(Ljava/lang/Object;Lorg/json/JSONObject;ZZ)V', "createShowUpX5WebViewFour")
        def showUpX5WebView2 = new SendataAnalyticsMethodCell("showUpX5WebView", '(Ljava/lang/Object;Z)V', "createShowUpX5WebViewTwo")
        def sendataAPIMethods = [showUpWebView, showUpX5WebView, showUpX5WebView2]
        def sendataAPIMethodCells = new HashMap<String, ArrayList<SendataAnalyticsMethodCell>>()
        sendataAPIMethodCells.put('com/sendata/analytics/android/sdk/SendataAPI', sendataAPIMethods)
        methodCells.put(methodName, sendataAPIMethodCells)
    }

    void disableMacAddress(String methodName) {
        def macAddress = new SendataAnalyticsMethodCell('getMacAddress', '(Landroid/content/Context;)Ljava/lang/String;', 'createGetMacAddress')
        def macMethods = [macAddress]
        def macMethodCells = new HashMap<String, ArrayList<SendataAnalyticsMethodCell>>()
        macMethodCells.put("com/sendata/analytics/android/sdk/util/SendataUtils", macMethods)
        methodCells.put(methodName, macMethodCells)
    }

    void disableCarrier(String methodName) {
        def carrier = new SendataAnalyticsMethodCell('getCarrier', '(Landroid/content/Context;)Ljava/lang/String;', 'createGetCarrier')
        def macMethods = [carrier]
        def macMethodCells = new HashMap<String, ArrayList<SendataAnalyticsMethodCell>>()
        macMethodCells.put("com/sendata/analytics/android/sdk/util/SendataUtils", macMethods)
        methodCells.put(methodName, macMethodCells)
    }

    //todo 扩展

    void createGetIMEI(ClassVisitor classVisitor, SendataAnalyticsMethodCell methodCell) {
        def mv = classVisitor.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, methodCell.name, methodCell.desc, null, null)
        mv.visitCode()
        mv.visitLdcInsn("")
        mv.visitInsn(Opcodes.ARETURN)
        mv.visitMaxs(1, 1)
        mv.visitEnd()
    }

    void createGetAndroidID(ClassVisitor classVisitor, SendataAnalyticsMethodCell methodCell) {
        def mv = classVisitor.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, methodCell.name, methodCell.desc, null, null)
        mv.visitCode()
        mv.visitLdcInsn("")
        mv.visitInsn(Opcodes.ARETURN)
        mv.visitMaxs(1, 1)
        mv.visitEnd()
    }

    void createSALogInfo(ClassVisitor classVisitor, SendataAnalyticsMethodCell methodCell) {
        def mv = classVisitor.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, methodCell.name, methodCell.desc, null, null)
        mv.visitCode()
        mv.visitInsn(Opcodes.RETURN)
        mv.visitMaxs(0, 3)
        mv.visitEnd()
    }

    void createPrintStackTrack(ClassVisitor classVisitor, SendataAnalyticsMethodCell methodCell) {
        def mv = classVisitor.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, methodCell.name, methodCell.desc, null, null)
        mv.visitCode()
        mv.visitInsn(Opcodes.RETURN)
        mv.visitMaxs(0, 1)
        mv.visitEnd()
    }

    void createShowUpWebViewFour(ClassVisitor classVisitor, SendataAnalyticsMethodCell methodCell) {
        def mv = classVisitor.visitMethod(Opcodes.ACC_PUBLIC, methodCell.name, methodCell.desc, null, null)
        mv.visitCode()
        mv.visitInsn(Opcodes.RETURN)
        mv.visitMaxs(0, 5)
        mv.visitEnd()
    }

    void createShowUpX5WebViewFour(ClassVisitor classVisitor, SendataAnalyticsMethodCell methodCell) {
        def mv = classVisitor.visitMethod(Opcodes.ACC_PUBLIC, methodCell.name, methodCell.desc, null, null)
        mv.visitCode()
        mv.visitInsn(Opcodes.RETURN)
        mv.visitMaxs(0, 5)
        mv.visitEnd()
    }

    void createShowUpX5WebViewTwo(ClassVisitor classVisitor, SendataAnalyticsMethodCell methodCell) {
        def mv = classVisitor.visitMethod(Opcodes.ACC_PUBLIC, methodCell.name, methodCell.desc, null, null)
        mv.visitCode()
        mv.visitInsn(Opcodes.RETURN)
        mv.visitMaxs(0, 3)
        mv.visitEnd()
    }

    void createGetMacAddress(ClassVisitor classVisitor, SendataAnalyticsMethodCell methodCell) {
        def mv = classVisitor.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, methodCell.name, methodCell.desc, null, null)
        mv.visitCode()
        mv.visitLdcInsn("")
        mv.visitInsn(Opcodes.ARETURN)
        mv.visitMaxs(1, 1)
        mv.visitEnd()
    }

    void createGetCarrier(ClassVisitor classVisitor, SendataAnalyticsMethodCell methodCell) {
        def mv = classVisitor.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, methodCell.name, methodCell.desc, null, null)
        mv.visitCode()
        mv.visitLdcInsn("")
        mv.visitInsn(Opcodes.ARETURN)
        mv.visitMaxs(1, 1)
        mv.visitEnd()
    }

    void createGetDeviceID(ClassVisitor classVisitor, SendataAnalyticsMethodCell methodCell) {
        def mv = classVisitor.visitMethod(Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, methodCell.name, methodCell.desc, null, null)
        mv.visitCode()
        mv.visitLdcInsn("")
        mv.visitInsn(Opcodes.ARETURN)
        mv.visitMaxs(1, 1)
        mv.visitEnd()
    }

    //todo 扩展

}
