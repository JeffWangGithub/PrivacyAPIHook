package com.glanwang.privacyapihook;

import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.ModuleInfo;
import android.content.pm.PackageInfo;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @title:
 * @description:
 * @version: Created on 2021/6/23.
 */
public class PrivacyAPINullImpl {

    private static final String TAG = "PrivacyAPI";
    private static final boolean DEBUG = true;
    public static final String SP_KEY_AGREE = "sp_key_agree";

    public static byte[] getHardwareAddress() {
        printHookInfo("hook getHardwareAddress 被调用");
        printMethodStack();
        return new byte[0];
    }

    public static String getMacAddress() {
        printHookInfo("hook getMacAddress 被调用");
        printMethodStack();
        return "";
    }

    public static String getDeviceId() {
        printHookInfo("hook getDeviceId 被调用");
        printMethodStack();
        return "";
    }

    public static String getSubscriberId() {
        printHookInfo("hook getSubscriberId 被调用");
        printMethodStack();
        return "";
    }

    public static List<ApplicationInfo> getInstalledApplications(int flags) {
        printHookInfo("hook getInstalledApplications 被调用");
        printMethodStack();
        return new ArrayList<>();
    }

    public static List<ApplicationInfo> getInstalledApplicationsAsUser(int flag, int user) {
        printHookInfo("hook getInstalledApplicationsAsUser 被调用");
        printMethodStack();
        return new ArrayList<>();
    }

    public static List<ModuleInfo> getInstalledModules(int flags) {
        printHookInfo("hook getInstalledModules 被调用");
        printMethodStack();
        return new ArrayList<>();
    }

    public static List<PackageInfo> getInstalledPackages(int flags) {
        printHookInfo("hook getInstalledPackages 被调用");
        printMethodStack();
        return new ArrayList<>();
    }


    public static boolean isRejectMode() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(CustomApp.getContext());
        return !sp.getBoolean(SP_KEY_AGREE, false);
    }

    private static void printHookInfo(String msg) {
        if (!DEBUG) {
            return;
        }
        Log.i(TAG, msg);
    }

    private static String printMethodStack() {
        if (!DEBUG) {
            return "";
        }
        //打印堆栈
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("调用堆栈:\n");
        for (StackTraceElement temp : stackTraceElements) {
            stringBuilder.append(temp.toString() + "\n");
        }
        String result = stringBuilder.toString();
        Log.i(TAG, result);
        return result;
    }
}
