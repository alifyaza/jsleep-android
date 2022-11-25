package com.alifyaZhafiraJSleepJS.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alifyaZhafiraJSleepJS.jsleep_android.model.Account;
import com.alifyaZhafiraJSleepJS.jsleep_android.model.Renter;
import com.alifyaZhafiraJSleepJS.jsleep_android.request.BaseApiService;
import com.alifyaZhafiraJSleepJS.jsleep_android.request.UtilsApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutMe extends AppCompatActivity {
    TextView name, email, balance;
    BaseApiService mApiService;
    //EditText username,address,phoneNumber;
    Context mContext;
    EditText RenterNameText,RenterAddressText,RenterPhoneNumberText;
    Button RegisterButton2, CancelButton;
    LinearLayout layout;

    protected static Renter currentRenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        mApiService = UtilsApi.getApiService();
        mContext = this;

        name = findViewById(R.id.displayname);
        name.setText(LoginActivity.currentAccount.name);

        email = findViewById(R.id.displayemail);
        email.setText(LoginActivity.currentAccount.email);

        balance = findViewById(R.id.displaybalance);
        balance.setText(String.valueOf(LoginActivity.currentAccount.balance));


        Button RegisterRenterButton = findViewById(R.id.RegisterRenterButton);
        RegisterRenterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterRenterButton.setVisibility(View.INVISIBLE);
                RenterNameText = findViewById(R.id.RenterName);
                RenterAddressText = findViewById(R.id.RenterAddress);
                RenterPhoneNumberText = findViewById(R.id.RenterPhoneNumber);
                RegisterButton2 = findViewById(R.id.RegisterButton2);
                CancelButton = findViewById(R.id.CancelButton);
                layout = findViewById(R.id.layout);
                layout.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
                layout.setVisibility(View.VISIBLE);


            }
        });
    }

    public void expand (View view) {
        int x = (RenterNameText.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
        int y = (RenterAddressText.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
        int z = (RenterPhoneNumberText.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
        int a = (RenterPhoneNumberText.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
        int b = (RenterPhoneNumberText.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;


        TransitionManager.beginDelayedTransition(layout, new AutoTransition());
        RenterNameText.setVisibility(x);
        RenterAddressText.setVisibility(y);
        RenterPhoneNumberText.setVisibility(z);
        RegisterButton2.setVisibility(a);
        CancelButton.setVisibility(b);
    }
}