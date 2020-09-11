package com.mikami.aad2020leaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SubmitActivity extends AppCompatActivity {


    Toolbar mToolbar;
    Button mBtnSubmit;
    EditText mEtFName;
    EditText mEtLName;
    EditText mEtEmail;
    EditText mEtLink;



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        mToolbar = (Toolbar) findViewById(R.id.toolbar2);
        mBtnSubmit = (Button) findViewById(R.id.btnSubmit);
        mEtEmail = (EditText) findViewById(R.id.etEmail);
        mEtFName = (EditText) findViewById(R.id.etFName);
        mEtLName = (EditText) findViewById(R.id.etLName);
        mEtLink = (EditText) findViewById(R.id.etLink);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getWindow().setStatusBarColor(ContextCompat.getColor(SubmitActivity.this, R.color.colorPrimary));

        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(" https://docs.google.com/forms/d/e/")
                        .build();
                String email = mEtEmail.getText().toString();
                String firstName = mEtFName.getText().toString();
                String lastName = mEtLName.getText().toString();
                String linkToProject = mEtLink.getText().toString();
                RetrofitWebService webservice = retrofit.create(RetrofitWebService.class);
                Call<Void> call = webservice.postValues(email, firstName, lastName, "AAD", linkToProject);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        int responseCode = response.code();
                        Intent intent;
                            if (responseCode == 200) {
                                intent = new Intent(SubmitActivity.this, SuccessPopUpActivity.class);
                                startActivity(intent);
                            }
                            else {
                                intent = new Intent(SubmitActivity.this, FailurePopUpActivity.class);
                                startActivity(intent);
                            }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                       Intent intent = new Intent(SubmitActivity.this, SuccessPopUpActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });





    }
}
