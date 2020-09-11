package com.mikami.aad2020leaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FailurePopUpActivity extends AppCompatActivity {

ConstraintLayout mClFailure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_failure_pop_up);
        mClFailure = (ConstraintLayout) findViewById(R.id.clFailure);
        mClFailure.setClickable(true);
        mClFailure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(FailurePopUpActivity.this, SubmitActivity.class);
//                startActivity(intent);
                FailurePopUpActivity.this.finish();
            }
        });
    }
}
