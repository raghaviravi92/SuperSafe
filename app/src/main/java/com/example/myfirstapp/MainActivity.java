package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button customButton = findViewById(R.id.custom_button);
        Switch switchEnableButton = findViewById(R.id.switch_enable_button);

        customButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(MainActivity.this, "Click", Toast.LENGTH_SHORT).show();
               // startRecorder(v);
                Intent intent = new Intent(MainActivity.this, LocationInformation.class);
                //EditText editText = (EditText) findViewById(R.id.editText);
                String message = "Hello World";
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Click", Toast.LENGTH_SHORT).show();
                //startRecorder(v);

            }
        });

        switchEnableButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    customButton.setEnabled(true);
                } else {
                    customButton.setEnabled(false);
                }
            }
        });
        Toolbar mainToolbar = (Toolbar) findViewById(R.id.toolbar);
        mainToolbar.setTitle("SuperSafe");
        setSupportActionBar(mainToolbar);
        mainToolbar.setSubtitle("A single click safety solution");

    }

    /*
     * BroadcastReceiver mBrSend; BroadcastReceiver mBrReceive;
     */
    private void sendSMS(String phoneNumber, String message) {
        ArrayList<PendingIntent> sentPendingIntents = new ArrayList<PendingIntent>();
        ArrayList<PendingIntent> deliveredPendingIntents = new ArrayList<PendingIntent>();
        PendingIntent sentPI = PendingIntent.getBroadcast(MainActivity.this, 0,
                new Intent(MainActivity.this, SmsSentReceiver.class), 0);
        PendingIntent deliveredPI = PendingIntent.getBroadcast(MainActivity.this, 0,
                new Intent(MainActivity.this, SmsDeliveredReceiver.class), 0);
        try {
            SmsManager sms = SmsManager.getDefault();
            ArrayList<String> mSMSMessage = sms.divideMessage(message);
            for (int i = 0; i < mSMSMessage.size(); i++) {
                sentPendingIntents.add(i, sentPI);
                deliveredPendingIntents.add(i, deliveredPI);
            }
            sms.sendMultipartTextMessage(phoneNumber, null, mSMSMessage,
                    sentPendingIntents, deliveredPendingIntents);

        } catch (Exception e) {

            e.printStackTrace();
            Toast.makeText(getBaseContext(), "SMS sending failed...",Toast.LENGTH_SHORT).show();
        }

    }

    public void startRecorder(View view){
        startActivity(new Intent(MainActivity.this, VideoCapture.class));
    }
    /** Called when the user taps the Settings button */
    public void configureSetting(MenuItem view) {
        Intent intent = new Intent(this, DatabaseMainActivity.class);
        //EditText editText = (EditText) findViewById(R.id.editText);
        String message = "Hello World";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
    /** Called when the user taps the Settings button */
    public void configureMap(MenuItem view) {
        Intent intent = new Intent(this, LocationInformation.class);
        //EditText editText = (EditText) findViewById(R.id.editText);
        String message = "Hello World";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.ss_menu, menu);
        return true;
    }
}