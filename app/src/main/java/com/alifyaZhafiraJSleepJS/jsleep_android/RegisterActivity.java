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
    EditText name,email,password;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mApiService = UtilsApi.getApiService();
        mContext = this;
        Button register = findViewById(R.id.RegisterButton);
        EditText name = findViewById(R.id.RegisterName);
        EditText email = findViewById(R.id.RegisterEmail);
        EditText password = findViewById(R.id.RegisterPassword);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tempemail = email.getText().toString();
                String temppass = password.getText().toString();
                String tempname = name.getText().toString();
                Account account = requestRegister(tempemail,temppass,tempname);
            }
        });

        TextView Login = findViewById(R.id.RegisterSignIn);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(move);
            }
        });
    }
    protected Account requestRegister(String email, String password, String name ){
        mApiService.register(email, password, name).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if(response.isSuccessful()){
                    Account account;
                    account = response.body();
                    System.out.println(account.toString());
                    Intent move = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(move);
                    System.out.println("Success");
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Toast.makeText(mContext, "Account Already Registered", Toast.LENGTH_SHORT).show();
                System.out.println("Failed");
            }
        });
        return null;
    }


}