package com.alifyaZhafiraJSleepJS.jsleep_android.request;

import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.Call;
import retrofit2.http.Query;

import com.alifyaZhafiraJSleepJS.jsleep_android.model.Account;

public interface BaseApiService {
    @GET("account/{id}")
    Call<Account> getAccount (@Path("id") int id);

    @GET("account/login")
    Call<Account> login (@Query("email") String email, @Query("password") String password);

    @GET("account/register")
    Call<Account> register (@Query("name") String name, @Query("email") String email, @Query("password") String password);



}
