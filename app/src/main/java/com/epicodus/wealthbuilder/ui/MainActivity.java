package com.epicodus.wealthbuilder.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.epicodus.wealthbuilder.R;

public class MainActivity extends AppCompatActivity {
    private Button mSubmitIncomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSubmitIncomeButton = (Button) findViewById(R.id.submitIncomeButton);
        mSubmitIncomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Toast.makeText(MainActivity.this, "Submitted!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, ExpenseActivity.class);
                startActivity(intent);
            }
        });
    }
}
