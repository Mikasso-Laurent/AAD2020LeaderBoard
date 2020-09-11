package com.mikami.aad2020leaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SuccessPopUpActivity extends AppCompatActivity {

    ConstraintLayout mClSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_pop_up);
        mClSuccess = (ConstraintLayout) findViewById(R.id.clSuccess);
        mClSuccess.setClickable(true);
        mClSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(SuccessPopUpActivity.this, SubmitActivity.class);
//                startActivity(intent);
                SuccessPopUpActivity.this.finish();
            }
        });
    }
}
