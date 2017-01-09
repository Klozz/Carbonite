package org.carbonrom.carbonite;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class ReenterDoze extends BroadcastReceiver {

    public static String TAG = "ForceDoze";

    public ReenterDoze() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Re-enter broadcast received");
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("reenter-doze"));
    }
}
