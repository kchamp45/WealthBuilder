package com.epicodus.wealthbuilder.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.epicodus.wealthbuilder.R;

public class ExpenseActivity extends AppCompatActivity {
    private TextView mIncomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        mIncomeTextView = (TextView) findViewById(R.id.incomeTextView);
        Intent intent = getIntent();
        String income = intent.getStringExtra("income");
        mIncomeTextView.setText("Here is your income: " + income);

    }
}
