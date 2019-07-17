package com.example.myfirstapp;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class DatabaseMainActivity extends AppCompatActivity {

    private static final String TAG = "DatabaseMainActivity";

    DatabaseHelper mDatabaseHelper;
    PreferenceActivity mPreferenceActivity;
    private Button btnAdd, btnViewData;
    private EditText editText;
    private EditText editText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        editText = (EditText) findViewById(R.id.editText);
        editText1 = (EditText) findViewById(R.id.editText1);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnViewData = (Button) findViewById(R.id.btnView);
        mDatabaseHelper = new DatabaseHelper(this);
        mPreferenceActivity = new PreferenceActivity(this);
        Switch switchEnableButton1 = findViewById(R.id.switch3);
        switchEnableButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    mPreferenceActivity.updatePref("false");
                    Cursor data = mPreferenceActivity.getData();
                    while(data.moveToNext()){
                        //get the value from the database in column 1
                        //then add it to the ArrayList
                        System.out.println("*** The inserted value is  ::::: ***" + data.getString(0));
                    }
                } else {
                    mPreferenceActivity.updatePref("true");
                    Cursor data = mPreferenceActivity.getData();
                    while(data.moveToNext()){
                        //get the value from the database in column 1
                        //then add it to the ArrayList
                        System.out.println("*** The inserted value is  ::::: ***" + data.getString(0));
                    }                }
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString()+" : "+ editText1.getText().toString();
                if (editText.length() != 0) {
                    AddData(newEntry);
                    editText.setText("");
                    editText1.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }

            }
        });

        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatabaseMainActivity.this, ListDataActivity.class);
                startActivity(intent);
            }
        });

    }

    public void AddData(String newEntry) {
        boolean insertData = mDatabaseHelper.addData(newEntry);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
