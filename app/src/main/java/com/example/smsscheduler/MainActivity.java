package com.example.smsscheduler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText message,number;
    Button sendsms;
    TimePicker timePicker;
    String messageContent,numberContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timePicker=findViewById(R.id.tp);
        message=findViewById(R.id.message);
        sendsms=findViewById(R.id.sendsms);
        number=findViewById(R.id.number);
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.SEND_SMS,Manifest.permission.RECEIVE_SMS,Manifest.permission.READ_SMS},PackageManager.PERMISSION_GRANTED);
        sendsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                messageContent=message.getText().toString();
                numberContent=number.getText().toString();
                if (Build.VERSION.SDK_INT>=23){

                    calendar.set(
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getHour(),
                            timePicker.getMinute(),
                            0
                    );
                }else{
                    calendar.set(
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getCurrentHour(),
                            timePicker.getCurrentMinute(),
                            0
                    );

                }
                setAlarm(calendar.getTimeInMillis());

            }
        });

    }

    private void setAlarm(long timeInMillis) {
        AlarmManager alarmManager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent=new Intent(this,MyAlarm.class);
        intent.putExtra("message",messageContent);
        intent.putExtra("number",numberContent);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,0,intent,0);
        alarmManager.set(AlarmManager.RTC,timeInMillis,pendingIntent);
        Toast.makeText(this,"Message is scheduled",Toast.LENGTH_SHORT);
    }

}


//
//        message=findViewById(R.id.message);
//        number=findViewById(R.id.number);
//        sendsms=findViewById(R.id.sendmessage);
//
//        sendsms.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View view) {
//        String messagecon=message.getText().toString();
//        String numbercon=number.getText().toString();
//
//        SmsManager smsManager=SmsManager.getDefault();
//        smsManager.sendTextMessage(numbercon,null,messagecon,null,null);
//
//        }
//        });