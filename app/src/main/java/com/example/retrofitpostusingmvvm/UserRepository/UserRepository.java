package com.example.retrofitpostusingmvvm.UserRepository;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.retrofitpostusingmvvm.helper.DataResource;
import com.example.retrofitpostusingmvvm.model.User;
import com.example.retrofitpostusingmvvm.network.ApiInterface;
import com.example.retrofitpostusingmvvm.network.RetrofitApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    public ApiInterface apiInterface;



    private MutableLiveData mutableLiveData = new MutableLiveData<DataResource<User>>();
    public LiveData<DataResource<User>> liveData = mutableLiveData;

    public void createUser(User user) {

        apiInterface = RetrofitApiClient.getRetrofit().create(ApiInterface.class);
        mutableLiveData.postValue(DataResource.loading());

        Call<User> call = apiInterface.createUser(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User responseUser = response.body();
                if (response.isSuccessful() && responseUser != null) {
                    mutableLiveData.postValue(DataResource.success(response.body()));
                } else {
                    mutableLiveData.postValue(DataResource.error(response.message()));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                mutableLiveData.postValue(DataResource.error(t.getMessage()));

            }
        });
    }
}
