package com.epicodus.wealthbuilder.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.epicodus.wealthbuilder.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ExpenseActivity extends AppCompatActivity {
    @Bind(R.id.incomeTextView) TextView mIncomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        ButterKnife.bind(this);
        
        Intent intent = getIntent();
        String income = intent.getStringExtra("income");
        mIncomeTextView.setText("Here is your income: " + income);

    }
}
