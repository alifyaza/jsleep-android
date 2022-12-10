package com.alifyaZhafiraJSleepJS.jsleep_android.request;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.Call;
import retrofit2.http.Query;

import com.alifyaZhafiraJSleepJS.jsleep_android.model.Account;
import com.alifyaZhafiraJSleepJS.jsleep_android.model.Renter;

public interface BaseApiService {
    @GET("account/{id}")
    Call<Account> getAccount (@Path("id") int id);

    @POST("account/login")
    Call<Account> login (@Query("email") String email, @Query("password") String password);

    @POST("account/register")
    Call<Account> register (@Query("email") String email, @Query("password") String password, @Query("name") String name);

    @GET("renter/{id}")
    Call<Renter> getRenter(@Path("id") int id);

    @POST("renter/registerRenter")
    Call<Renter> registerRenter(int id, @Query("username") String username, @Query("address") String address, @Query("phoneNumber") String phoneNumber);
}

