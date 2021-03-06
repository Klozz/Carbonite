package org.carbonrom.carbonite;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class SettingsChangeReceiver extends BroadcastReceiver {

    public static String TAG = "ForceDoze";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "org.carbonrom.carbonite.CHANGE_SETTING broadcast intent received");
        final String settingName = intent.getStringExtra("settingName");
        final String settingValue = intent.getStringExtra("settingValue");

        if (settingName != null && settingValue != null) {
            if (Utils.doesSettingExist(settingName)) {
                if (Utils.isSettingBool(settingName)) {
                    Utils.updateSettingBool(context, settingName, Boolean.valueOf(settingValue));
                    if (Utils.isMyServiceRunning(ForceDozeService.class, context)) {
                        Intent i = new Intent("reload-settings");
                        LocalBroadcastManager.getInstance(context).sendBroadcast(i);
                    }
                } else {
                    Utils.updateSettingInt(context, settingName, Integer.valueOf(settingValue));
                    if (Utils.isMyServiceRunning(ForceDozeService.class, context)) {
                        Intent i = new Intent("reload-settings");
                        LocalBroadcastManager.getInstance(context).sendBroadcast(i);
                    }
                }
            } else {
                Log.i(TAG, "Setting does not exist or not updatable");
            }
        } else {
            Log.i(TAG, "settingName and/or settingValue null");
        }
    }
}
