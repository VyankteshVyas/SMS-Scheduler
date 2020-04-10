package com.example.smsscheduler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;

public class MyAlarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String messagecon=intent.getStringExtra("message");
        String numbercon=intent.getStringExtra("number");

        SmsManager smsManager=SmsManager.getDefault();
        smsManager.sendTextMessage(numbercon,null,messagecon,null,null);
    }
}
