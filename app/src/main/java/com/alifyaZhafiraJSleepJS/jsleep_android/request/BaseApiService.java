package com.alifyaZhafiraJSleepJS.jsleep_android.request;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.Call;
import retrofit2.http.Query;

import com.alifyaZhafiraJSleepJS.jsleep_android.model.Account;
import com.alifyaZhafiraJSleepJS.jsleep_android.model.BedType;
import com.alifyaZhafiraJSleepJS.jsleep_android.model.City;
import com.alifyaZhafiraJSleepJS.jsleep_android.model.Facility;
import com.alifyaZhafiraJSleepJS.jsleep_android.model.Renter;
import com.alifyaZhafiraJSleepJS.jsleep_android.model.Room;

import java.util.ArrayList;
import java.util.List;

public interface BaseApiService {
    @GET("account/{id}")
    Call<Account> getAccount (@Path("id") int id);

    @POST("account/login")
    Call<Account> login (@Query("email") String email, @Query("password") String password);

    @POST("account/register")
    Call<Account> register (@Query("email") String email, @Query("password") String password, @Query("name") String name);

    @POST("account/{id}/topUp")
    Call<Boolean> topUp(@Path("id") int id,
                        @Query("balance") int balance);

    @GET("renter/{id}")
    Call<Renter> getRenter(@Path("id") int id);

    @POST("renter/registerRenter")
    Call<Renter> registerRenter(@Path("id") int id, @Query("username") String username, @Query("address") String address, @Query("phoneNumber") String phoneNumber);

    @GET("room/getAllRoom")
    Call<List<Room>> getAllRoom (@Query("page") int page, @Query("pageSize") int pageSize);

    @POST("room/create")
    Call<Room> createRoom (@Query("accountId") int id,
                           @Query("name") String name,
                           @Query("size") int size,
                           @Query("price") int price,
                           @Query("facility") ArrayList<Facility> facility,
                           @Query("city") City city,
                           @Query("address") String address,
                           @Query("bedType") BedType bedType
    );

}

