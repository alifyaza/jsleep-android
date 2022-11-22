package com.alifyaZhafiraJSleepJS.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alifyaZhafiraJSleepJS.jsleep_android.model.Account;
import com.alifyaZhafiraJSleepJS.jsleep_android.request.BaseApiService;
import com.alifyaZhafiraJSleepJS.jsleep_android.request.UtilsApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    BaseApiService mApiService;
    EditText name, email, password;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mApiService = UtilsApi.getApiService();
        mContext = this;
        TextView signinbutton = findViewById(R.id.RegisterSignIn);
        signinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(move);
            }
        });
        name = findViewById(R.id.RegisterName);
        email = findViewById(R.id.RegisterEmail);
        password = findViewById(R.id.RegisterPassword);
        Button registerbutton = findViewById(R.id.RegisterButton);
        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameR = name.getText().toString();
                String emailR = email.getText().toString();
                String passwordR = password.getText().toString();
                Account account = requestRegister(nameR, emailR, passwordR);
                Intent move = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(move);
            }
        });
    }

    protected Account requestRegister(String nameR, String emailR, String passwordR) {
        mApiService.register(nameR, emailR, passwordR).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful()) {
                    Account account;
                    account = response.body();
                    System.out.println(account.toString());
                    Intent move = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(move);
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                System.out.println("failed");
                System.out.println(t.toString());
                Toast.makeText(mContext, "Account Already Registered", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }
}