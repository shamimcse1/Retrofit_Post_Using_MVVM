package com.example.retrofitpostusingmvvm.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofitpostusingmvvm.UserRepository.UserRepository;
import com.example.retrofitpostusingmvvm.helper.DataResource;
import com.example.retrofitpostusingmvvm.model.User;

public class UserViewModel extends ViewModel {

    UserRepository userRepository = new UserRepository();
    public LiveData<DataResource<User>> userLiveData = userRepository.liveData;

    public void postUserData(User user) {
        userRepository.createUser(user);

    }
}
