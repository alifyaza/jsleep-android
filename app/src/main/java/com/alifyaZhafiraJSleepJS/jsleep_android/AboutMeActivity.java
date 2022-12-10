package com.alifyaZhafiraJSleepJS.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
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

public class AboutMeActivity extends AppCompatActivity {
    Context mContext;
    BaseApiService mApiService;

    TextView nameAcc, emailAcc, balanceAcc;
    EditText RenterNameText,RenterAddressText,RenterPhoneNumberText;
    Button RegisterRenterB, RegisterReqB, CancelReqB;
    TextView InputName, InputAddress, InputPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        mApiService = UtilsApi.getApiService();
        mContext = this;

        nameAcc = findViewById(R.id.displayname);
        nameAcc.setText(MainActivity.loginacc.name);
        emailAcc = findViewById(R.id.displayemail);
        emailAcc.setText(MainActivity.loginacc.email);
        balanceAcc = findViewById(R.id.displaybalance);
        balanceAcc.setText(String.valueOf(MainActivity.loginacc.balance));

        RegisterRenterB = findViewById(R.id.RegisterRenterButton);


        //Second Condition
        RenterNameText = findViewById(R.id.RenterName);
        RenterAddressText = findViewById(R.id.RenterAddress);
        RenterPhoneNumberText = findViewById(R.id.RenterPhoneNumber);
        CancelReqB = findViewById(R.id.CancelReq);
        RegisterReqB = findViewById(R.id.RegisterReq);
        CardView CardViewRegister = findViewById(R.id.CardViewRegister);

        //Third Condition
        InputName = findViewById(R.id.OutRentName);
        InputAddress = findViewById(R.id.OutRentAddress);
        InputPhoneNumber = findViewById(R.id.OutRentPhoneNumber);
        CardView CardViewInput = findViewById(R.id.CardViewInput);

        CardViewRegister.setVisibility(CardView.INVISIBLE);
        CardViewInput.setVisibility(CardView.INVISIBLE);

        mApiService = UtilsApi.getApiService();
        mContext = this;

        if (MainActivity.loginacc.renter == null)
        {
            CardViewRegister.setVisibility(View.INVISIBLE);
            CardViewInput.setVisibility(View.INVISIBLE);
            RegisterRenterB.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view) {
                    RegisterRenterB.setVisibility(Button.INVISIBLE);
                    CardViewRegister.setVisibility(CardView.VISIBLE);
                    CardViewInput.setVisibility(CardView.INVISIBLE);

                    RegisterReqB.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View view)  {
                            Renter accountRenter = requestRenter();
                        }
                    });
                    CancelReqB.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            RegisterRenterB.setVisibility(CardView.INVISIBLE);
                            CardViewRegister.setVisibility(CardView.INVISIBLE);
                        }
                    });
                }
            });

        } if(MainActivity.loginacc.renter != null){
            RegisterRenterB.setVisibility(Button.INVISIBLE);
            CardViewRegister.setVisibility(CardView.INVISIBLE);
            CardViewInput.setVisibility(CardView.VISIBLE);

            InputName.setText(MainActivity.loginacc.renter.username);
            InputAddress.setText(MainActivity.loginacc.renter.address);
            InputPhoneNumber.setText(MainActivity.loginacc.renter.phoneNumber);
        }
    }

    protected Renter requestRenter() {
        mApiService.registerRenter(MainActivity.loginacc.id,
                RenterNameText.getText().toString(),
                RenterAddressText.getText().toString(),
                RenterPhoneNumberText.getText().toString()).enqueue(new Callback<Renter>() {
            @Override
            public void onResponse(Call<Renter> call, Response<Renter> response) {

                if (response.isSuccessful()) {
                    Renter renter;
                    renter = response.body();
                    MainActivity.loginacc.renter = renter;
                    System.out.println("Renter Registered Success");
                    Intent move = new Intent(AboutMeActivity.this, AboutMeActivity.class);
                    startActivity(move);
                    Toast.makeText(mContext, "Registered Renter", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Renter> call, Throwable t) {
                System.out.println("GAGAL");
                Toast.makeText(mContext, "Failed to Register!", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }
}