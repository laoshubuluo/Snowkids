package com.rat.snowkids.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class USBBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_MEDIA_MOUNTED)) {
            Toast.makeText(context, "ACTION_MEDIA_MOUNTED", Toast.LENGTH_LONG).show();
            System.out.println("=======================ACTION_MEDIA_MOUNTED================");
        } else if (action.equals(Intent.ACTION_MEDIA_EJECT)) {
            Toast.makeText(context, "ACTION_MEDIA_EJECT", Toast.LENGTH_LONG).show();

            System.out.println("=======================ACTION_MEDIA_EJECT================");
        } else if (action.equals(Intent.ACTION_MEDIA_UNMOUNTED)) {
            Toast.makeText(context, "ACTION_MEDIA_UNMOUNTED", Toast.LENGTH_LONG).show();

            System.out.println("=======================ACTION_MEDIA_UNMOUNTED================");
        }
    }
}