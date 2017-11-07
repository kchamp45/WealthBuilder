package com.epicodus.wealthbuilder.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.wealthbuilder.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DepositActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.incomeTextView) TextView mIncomeTextView;
    @Bind(R.id.expenseTextView) TextView mExpenseTextView;
    @Bind(R.id.netTextView) TextView mNetTextView;
    @Bind(R.id.initialDepositEditText) EditText mInitialDepositEditText;
    @Bind(R.id.additionalDepositEditText) EditText mAdditionalDepositEditText;
    @Bind(R.id.durationEditText) EditText mDurationEditText;

    @Bind(R.id.depositButton) Button mDepositButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String income = intent.getStringExtra("income");
        int monthlyIncome = Integer.parseInt(income) / 12;
        Log.d("DepositActivity", income);
        mIncomeTextView.setText("Income per month: " + "$"+ monthlyIncome);

        int expense = intent.getIntExtra("expense", 0);
        mExpenseTextView.setText("Expense per month: " + "$"+ expense);

        int net = monthlyIncome - expense;
        mNetTextView.setText("Net: " + "$" + net);

        mDepositButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String initialDeposit = mInitialDepositEditText.getText().toString();
        String additionalDeposit = mAdditionalDepositEditText.getText().toString();
        String duration = mDurationEditText.getText().toString();

        if(v == mDepositButton) {
            Intent newIntent = new Intent(DepositActivity.this, WealthActivity.class);
            newIntent.putExtra("iDeposit", initialDeposit);
            newIntent.putExtra("aDeposit", additionalDeposit);
            newIntent.putExtra("duration", duration);
            startActivity(newIntent);
        }
    }
}
