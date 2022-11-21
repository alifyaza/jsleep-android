package com.alifyaZhafiraJSleepJS.jsleep_android.request;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.Call;

import com.alifyaZhafiraJSleepJS.jsleep_android.model.Account;

public interface BaseApiService {
    @GET("account/{id}")
    Call<Account> getAccount (@Path("id") int id);
}
