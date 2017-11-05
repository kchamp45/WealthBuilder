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

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private EditText mIncomeEditText;
    private Button mSubmitIncomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIncomeEditText = (EditText) findViewById(R.id.incomeEditText);
        mSubmitIncomeButton = (Button) findViewById(R.id.submitIncomeButton);
        mSubmitIncomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String income = mIncomeEditText.getText().toString();
                Log.d(TAG, income);
            Toast.makeText(MainActivity.this, "$$Hooray$$!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, ExpenseActivity.class);
                intent.putExtra("income", income);
                startActivity(intent);
            }
        });
    }
}
