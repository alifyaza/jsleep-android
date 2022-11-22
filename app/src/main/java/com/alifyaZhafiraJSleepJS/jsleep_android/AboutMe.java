package com.alifyaZhafiraJSleepJS.jsleep_android;

import static com.alifyaZhafiraJSleepJS.jsleep_android.LoginActivity.currentAccount;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AboutMe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
    }

    public class AboutMeActivity extends AppCompatActivity {

        TextView myname, myemail, mybalance;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_about_me);

            myname = findViewById(R.id.displayname);
            myemail = findViewById(R.id.displayemail);
            mybalance = findViewById(R.id.displaybalance);

            myname.setText(currentAccount.name);
            myemail.setText(currentAccount.email);
            mybalance.setText(String.valueOf(currentAccount.balance));


        }


    }
}