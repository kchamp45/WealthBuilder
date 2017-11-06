package com.epicodus.wealthbuilder.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.epicodus.wealthbuilder.R;

public class WealthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wealth);
    }
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
