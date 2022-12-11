package com.alifyaZhafiraJSleepJS.jsleep_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alifyaZhafiraJSleepJS.jsleep_android.model.Renter;
import com.alifyaZhafiraJSleepJS.jsleep_android.request.BaseApiService;
import com.alifyaZhafiraJSleepJS.jsleep_android.request.UtilsApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.view.MenuItem;

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
        CardView CardViewRegisterRenter = findViewById(R.id.CardViewRegisterRenter);

        //Second Condition
        RenterNameText = findViewById(R.id.RenterName);
        RenterAddressText = findViewById(R.id.RenterAddress);
        RenterPhoneNumberText = findViewById(R.id.RenterPhoneNumber);
        CancelReqB = findViewById(R.id.CancelReq);
        RegisterReqB = findViewById(R.id.RegisterReq);
        CardView CardViewInput = findViewById(R.id.CardViewInput);

        //Third Condition
        InputName = findViewById(R.id.OutRentName);
        InputAddress = findViewById(R.id.OutRentAddress);
        InputPhoneNumber = findViewById(R.id.OutRentPhoneNumber);
        CardView CardViewDisplay = findViewById(R.id.CardViewDisplay);

        CardViewInput.setVisibility(CardView.INVISIBLE);
        CardViewDisplay.setVisibility(CardView.INVISIBLE);

        mApiService = UtilsApi.getApiService();
        mContext = this;

        if (MainActivity.loginacc.renter == null)
        {
            CardViewRegisterRenter.setVisibility(View.VISIBLE);
            CardViewInput.setVisibility(View.INVISIBLE);
            CardViewDisplay.setVisibility(View.INVISIBLE);
            RegisterRenterB.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view) {
                    CardViewRegisterRenter.setVisibility(View.INVISIBLE);
                    CardViewInput.setVisibility(View.VISIBLE);
                    CardViewDisplay.setVisibility(View.INVISIBLE);

                    RegisterReqB.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View view)  {
                            /*int id_temp = MainActivity.loginacc.id;
                            String username_temp = RenterNameText.getText().toString();
                            String address_temp = RenterAddressText.getText().toString();
                            String phoneNumb_temp = RenterPhoneNumberText.getText().toString();
                            Renter accountRenter = requestRenter(id_temp, username_temp, address_temp, phoneNumb_temp);*/
                            CardViewRegisterRenter.setVisibility(View.INVISIBLE);
                            CardViewInput.setVisibility(View.INVISIBLE);
                            CardViewDisplay.setVisibility(View.VISIBLE);

                        }
                    });
                    CancelReqB.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            CardViewRegisterRenter.setVisibility(View.VISIBLE);
                            CardViewInput.setVisibility(View.INVISIBLE);
                            CardViewDisplay.setVisibility(View.INVISIBLE);
                        }
                    });
                }
            });

        } if(MainActivity.loginacc.renter != null){
            CardViewRegisterRenter.setVisibility(View.INVISIBLE);
            CardViewInput.setVisibility(View.INVISIBLE);
            CardViewDisplay.setVisibility(View.VISIBLE);

            InputName.setText(MainActivity.loginacc.renter.username);
            InputAddress.setText(MainActivity.loginacc.renter.address);
            InputPhoneNumber.setText(MainActivity.loginacc.renter.phoneNumber);
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    /*public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home_button:
                Intent aboutMe = new Intent (AboutMeActivity.this, MainActivity.class);
                Toast.makeText(this, "Opening Home", Toast.LENGTH_SHORT).show();
                startActivity(aboutMe);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu, menu);
        return true;
    }

    protected Renter requestRenter(int id, String username, String address, String phoneNumber) throws NullPointerException {
        mApiService.registerRenter(id, username, address, phoneNumber).enqueue(new Callback<Renter>() {
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