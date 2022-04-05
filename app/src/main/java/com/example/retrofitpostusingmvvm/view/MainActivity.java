package com.example.retrofitpostusingmvvm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.retrofitpostusingmvvm.R;
import com.example.retrofitpostusingmvvm.databinding.ActivityMainBinding;
import com.example.retrofitpostusingmvvm.helper.DataResource;
import com.example.retrofitpostusingmvvm.model.User;
import com.example.retrofitpostusingmvvm.viewModel.UserViewModel;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    String name;
    String job;
    UserViewModel userViewModel;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        userViewModel.userLiveData.observe(this, new Observer<DataResource<User>>() {
            @Override
            public void onChanged(DataResource<User> userDataResource) {
                switch (userDataResource.getStatus()) {
                    case SUCCESS:
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        binding.name.setText("");
                        binding.job.setText("");
                        break;

                    case ERROR:
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        break;

                    case LOADING:
                        progressDialog.show();
                        break;

                }

            }
        });


        binding.saveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = binding.name.getText().toString();
                job = binding.job.getText().toString();

                if (!isEmpty(name,job)){
                    User user = new User(name, job);
                    userViewModel.postUserData(user);
                }


            }
        });

    }

    public boolean isEmpty(String name, String job) {
        boolean isEmptyResult = false;
        if (name.length() == 0) {
            binding.name.setError("Enter Name");
            isEmptyResult = true;
        }
        if (job.length() == 0) {
            binding.job.setError("Enter Job");
            isEmptyResult = true;
        }
        return isEmptyResult;
    }


}
