package com.epicodus.wealthbuilder.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.wealthbuilder.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WealthActivity extends AppCompatActivity {
//    @Bind(R.id.initialDepositEditText) EditText mInitialDepositEditText;
//    @Bind(R.id.additionalDepositEditText) EditText mAdditionalDepositEditText;
//    @Bind(R.id.durationEditText) EditText mDurationEditText;
    @Bind(R.id.accountTextView) TextView mAccountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wealth);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String initDeposit = intent.getStringExtra("iDeposit");
        int iDeposit = Integer.parseInt(initDeposit);
        String addDeposit = intent.getStringExtra("aDeposit");
        int aDeposit = Integer.parseInt(addDeposit);
        String duration = intent.getStringExtra("duration");
        int years = Integer.parseInt(duration);

        double FV = 0;
        for (int i = 1; i <= years; i++) {
            FV = (FV + (aDeposit*12)) * (1.07);
        }
        double totalFV = FV + iDeposit * Math.pow(1.07, years);
        double roundOff = Math.floor(totalFV*100)/100;

        Log.d("WealthActivity", "Here is the " + "$"+totalFV);

        mAccountTextView.setText("Future Account value: " + "$"+roundOff);

    }

//
//    Intent intent = getIntent();
//    String income = intent.getStringExtra("income");
//    FV = future value;
//    P = initial principle
//    i = interest rate;
//    r = (i)/100;
//    n = number of investment years;
//    A = additional contribution amount;
//    F = frequency of contribution;
//
//    function accountBalance {
//        FV = P* Math.pow((1 + r), n) + (A*F)(Math.pow(1+r), n-1);
//        return FV;

}
