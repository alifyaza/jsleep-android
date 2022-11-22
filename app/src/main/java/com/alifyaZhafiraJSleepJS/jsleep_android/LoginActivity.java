package com.alifyaZhafiraJSleepJS.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.alifyaZhafiraJSleepJS.jsleep_android.model.Account;
import com.alifyaZhafiraJSleepJS.jsleep_android.request.BaseApiService;
import com.alifyaZhafiraJSleepJS.jsleep_android.request.UtilsApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {
    BaseApiService mApiService;
    EditText username,password;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mApiService = UtilsApi.getApiService();
        mContext = this;
        TextView register = findViewById(R.id.LoginSignUp);
        Button login = findViewById(R.id.LoginButton);
        username = findViewById(R.id.LoginUsername);
        password = findViewById(R.id.LoginPassword);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = username.getText().toString();
                String passwordt = password.getText().toString();
                Account account = requestLogin(name,passwordt);
            }

                /*Intent move = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(move);*/
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(move);
            }

        });
    }

    protected Account requestAccount() {
        mApiService.getAccount(1).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful()) {
                    Account account;
                    account = response.body();
                    System.out.println(account.toString());
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                System.out.println("failed");
                System.out.println(t.toString());
                Toast.makeText(mContext, "no Account id=0", Toast.LENGTH_SHORT).show();
            }
        });

        return null;
    }

        protected Account requestLogin(String name, String pass){
            mApiService.login(name, pass).enqueue(new Callback<Account>() {
                @Override
                public void onResponse(Call<Account> call, Response<Account> response) {
                    if(response.isSuccessful()){
                        Account account;
                        account = response.body();
                        System.out.println(account.toString());
                        Intent move = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(move);
                    }
                }

                @Override
                public void onFailure(Call<Account> call, Throwable t){
                    System.out.println("failed");
                    System.out.println(t.toString());
                    Toast.makeText(mContext, "no Account id=0", Toast.LENGTH_SHORT).show();
                }
            });

        return null;
    }
}