package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
                Toast.makeText(MainActivity.this, "Click", Toast.LENGTH_SHORT).show();
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
    /** Called when the user taps the Settings button */
    public void configureSetting(MenuItem view) {
        Intent intent = new Intent(this, DatabaseMainActivity.class);
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