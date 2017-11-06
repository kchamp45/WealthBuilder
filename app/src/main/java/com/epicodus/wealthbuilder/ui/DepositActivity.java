package com.epicodus.wealthbuilder.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.epicodus.wealthbuilder.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DepositActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.incomeTextView) TextView mIncomeTextView;
    @Bind(R.id.expenseTextView) TextView mExpenseTextView;
    @Bind(R.id.netTextView) TextView mNetTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
        ButterKnife.bind(this);

        Intent intent = getIntent();
//        String income = intent.getStringExtra("income");
//        int monthlyIncome = Integer.parseInt(income) / 12;
//        mIncomeTextView.setText("Here is your income per month: " + monthlyIncome);
//
        int expense = intent.getIntExtra("expense", 0);
        mExpenseTextView.setText("Here is your expense per month: " + expense);
//
//        int net = monthlyIncome - monthlyExpense;
//        mNetTextView.setText("Here's what you have left to save: " + net);

    }

    @Override
    public void onClick(View view) {

    }
}
