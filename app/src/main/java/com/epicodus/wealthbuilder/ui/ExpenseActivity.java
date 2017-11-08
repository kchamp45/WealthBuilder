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

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ExpenseActivity extends AppCompatActivity implements View.OnClickListener{

    @Bind(R.id.housingEditText) EditText mHousingEditText;
    @Bind(R.id.transportationEditText) EditText mTransportationEditText;
    @Bind(R.id.foodEditText) EditText mFoodEditText;
    @Bind(R.id.miscEditText) EditText mMiscEditText;
    @Bind(R.id.submitExpenseButton) Button mSubmitExpenseButton;
    @Bind(R.id.expenseChartButton) Button mExpenseChartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        ButterKnife.bind(this);

        mSubmitExpenseButton.setOnClickListener(this);
        mExpenseChartButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        int housing = Integer.parseInt(mHousingEditText.getText().toString());
        int transportation = Integer.parseInt(mTransportationEditText.getText().toString());
        int food = Integer.parseInt(mFoodEditText.getText().toString());
        int misc = Integer.parseInt(mMiscEditText.getText().toString());
        int totalExpense = housing + transportation + food + misc;

        Intent intent = getIntent();
        String income = intent.getStringExtra("income");

        if(v == mSubmitExpenseButton) {

            Intent nextIntent = new Intent(ExpenseActivity.this, DepositActivity.class);
            nextIntent.putExtra("expense", totalExpense);
            nextIntent.putExtra("income", income);
            startActivity(nextIntent);
        }
        if(v == mExpenseChartButton) {
            Intent chartIntent = new Intent(ExpenseActivity.this, PieChartActivity.class);
            chartIntent.putExtra("housing", housing);
            chartIntent.putExtra("transportation", transportation);
            chartIntent.putExtra("food", food);
            chartIntent.putExtra("misc", misc);
            startActivity(chartIntent);
        }
    }

}
