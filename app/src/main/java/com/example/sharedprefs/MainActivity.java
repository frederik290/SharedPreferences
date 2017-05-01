package com.example.sharedprefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String fName, lName;
        int age, phone;

        //use unique keys if used for something serious (put it in R.string)
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        fName = sharedPref.getString("first_name", "");
        lName = sharedPref.getString("last_name", "");
        age = sharedPref.getInt("age", 0);
        phone = sharedPref.getInt("phone", 0);

        Info info = new Info(fName, lName, age, phone);
        setSavedData(info);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Info info = getInput();
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("first_name", info.fName);
        editor.putString("last_name", info.lName);
        editor.putInt("age", info.age);
        editor.putInt("phone", info.phone);

        editor.commit();
    }

    private Info getInput() {
        EditText fNameView = (EditText) findViewById(R.id.first_name_input);
        EditText lNameView = (EditText) findViewById(R.id.last_name_input);
        EditText ageView = (EditText) findViewById(R.id.age_input);
        EditText phoneView = (EditText) findViewById(R.id.phone_number_input);

        String fName = fNameView.getText().toString();
        String lName = lNameView.getText().toString();
        int age = Integer.parseInt(ageView.getText().toString());
        int phone = Integer.parseInt(phoneView.getText().toString());

        return new Info(fName, lName, age, phone);
    }

    private void setSavedData(Info info){
        EditText fNameView = (EditText) findViewById(R.id.first_name_input);
        EditText lNameView = (EditText) findViewById(R.id.last_name_input);
        EditText ageView = (EditText) findViewById(R.id.age_input);
        EditText phoneView = (EditText) findViewById(R.id.phone_number_input);

        fNameView.setText(info.fName);
        lNameView.setText(info.lName);

        if(info.age != 0){
            ageView.setText(info.age + "");
        }

        if(info.phone != 0){
            phoneView.setText(info.phone + "");
        }
    }

    public class Info{
        String fName;
        String lName;
        int age;
        int phone;

        public Info(String fName, String lName, int age, int phone) {
            this.fName = fName;
            this.lName = lName;
            this.age = age;
            this.phone = phone;
        }
    }
}
