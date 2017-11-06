package com.epicodus.wealthbuilder.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.epicodus.wealthbuilder.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.incomeEditText) EditText mIncomeEditText;
    @Bind(R.id.submitIncomeButton) Button mSubmitIncomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mSubmitIncomeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String income = mIncomeEditText.getText().toString();
        Toast.makeText(MainActivity.this, "$$$$$!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainActivity.this, ExpenseActivity.class);
        intent.putExtra("income", income);
        startActivity(intent);
    }

}

