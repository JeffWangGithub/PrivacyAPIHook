package com.glanwang.privacyapihook;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @title:
 * @description:
 * @company: Netease
 * @author: GlanWang
 * @blame: 王广丛
 * @version: Created on 2021/6/23.
 */
public class Tools {


    public static String getMac(Context context) {
        String mac = "";
        if (Build.VERSION.SDK_INT < 23) {
            mac = getMacBelowM(context);
        } else {
            mac = getMacUpM();
        }
        return mac;
    }

    private static String getMacBelowM(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            if (wifiInfo != null) {
                return wifiInfo.getMacAddress();
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return "";
    }


    private static String getMacUpM() {
        try {
            String interfaseName = "wlan0";
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            Iterator var2 = interfaces.iterator();

            while (var2.hasNext()) {
                NetworkInterface intf = (NetworkInterface) var2.next();
                if (intf.getName().equalsIgnoreCase(interfaseName)) {
                    byte[] mac = intf.getHardwareAddress();
                    if (mac == null) {
                        return "";
                    }

                    StringBuilder builder = new StringBuilder();
                    byte[] var6 = mac;
                    int var7 = mac.length;

                    for (int var8 = 0; var8 < var7; ++var8) {
                        byte aMac = var6[var8];
                        builder.append(String.format("%02X:", aMac));
                    }

                    if (builder.length() > 0) {
                        builder.deleteCharAt(builder.length() - 1);
                    }

                    return builder.toString();
                }
            }
        } catch (Exception var10) {
            var10.printStackTrace();
        }

        return "";
    }



    public static String getIMEI(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager)context.getSystemService("phone");
            return tm.getDeviceId();
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }


    public static List<PackageInfo> getInstallAppList(Context context) {
        return context.getPackageManager().getInstalledPackages(PackageManager.GET_PROVIDERS);
    }
}
