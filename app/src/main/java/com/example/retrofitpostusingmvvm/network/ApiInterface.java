package com.example.retrofitpostusingmvvm.network;

import com.example.retrofitpostusingmvvm.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("users")
    Call<User> createUser(@Body User user);
}
