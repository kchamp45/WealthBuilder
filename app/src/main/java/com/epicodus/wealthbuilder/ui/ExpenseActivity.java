package com.epicodus.wealthbuilder.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.wealthbuilder.Constants;
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

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        mSubmitExpenseButton.setOnClickListener(this);
        mExpenseChartButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (TextUtils.isEmpty(mHousingEditText.getText()) || TextUtils.isEmpty(mFoodEditText.getText()) || TextUtils.isEmpty(mTransportationEditText.getText()) || TextUtils.isEmpty(mMiscEditText.getText()))
        {
            Toast.makeText(getApplicationContext(), "please fill in all fields", Toast.LENGTH_SHORT).show();

        } else{
            int housing = Integer.parseInt(mHousingEditText.getText().toString());
            int transportation = Integer.parseInt(mTransportationEditText.getText().toString());
            int food = Integer.parseInt(mFoodEditText.getText().toString());
            int misc = Integer.parseInt(mMiscEditText.getText().toString());
            int totalExpense = housing + transportation + food + misc;

            addToSharedPreferences(housing, food, transportation, misc);

            Intent intent = getIntent();
            double incomeA = intent.getDoubleExtra("income", 0);

            if (v == mSubmitExpenseButton) {

                Intent nextIntent = new Intent(ExpenseActivity.this, DepositActivity.class);
                nextIntent.putExtra("expense", totalExpense);
                nextIntent.putExtra("incomeB", incomeA);
                startActivity(nextIntent);
            }
            if (v == mExpenseChartButton) {

                Intent chartIntent = new Intent(ExpenseActivity.this, PieChartActivity.class);
                chartIntent.putExtra("housing", housing);
                chartIntent.putExtra("transportation", transportation);
                chartIntent.putExtra("food", food);
                chartIntent.putExtra("misc", misc);
                startActivity(chartIntent);
            }
        }
    }

        private void addToSharedPreferences(int housing, int food, int transportation, int misc) {
            mEditor.putInt(Constants.PREFERENCES_HOUSING_KEY, housing).apply();
            mEditor.putInt(Constants.PREFERENCES_FOOD_KEY, food).apply();
            mEditor.putInt(Constants.PREFERENCES_TRANSPORTATION_KEY, transportation).apply();
            mEditor.putInt(Constants.PREFERENCES_MISC_KEY, misc).apply();

        }
    

}
