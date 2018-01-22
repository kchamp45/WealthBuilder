package com.epicodus.wealthbuilder.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.epicodus.wealthbuilder.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.incomeEditText) EditText mIncomeEditText;
    @Bind(R.id.submitIncomeButton) Button mSubmitIncomeButton;
    @Bind(R.id.radioButton1) RadioButton mRadioButton1;
    @Bind(R.id.radioButton2) RadioButton mRadioButton2;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName() + "!");
                } else {

                }

            }
        };

        mSubmitIncomeButton.setOnClickListener(this);
        mRadioButton1.setOnClickListener(this);
        mRadioButton2.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    @Override
    public void onClick(View v) {
        int income = Integer.parseInt( mIncomeEditText.getText().toString());
        int taxIncome = 0;
        double afterTaxIncome = 0;
        double tax = 0;

        Toast.makeText(MainActivity.this, "$$$$$!", Toast.LENGTH_LONG).show();
        if(mRadioButton1.isChecked()) {
            if(income > 12000) {
                taxIncome = income - 12000;
                if (taxIncome <= 9525) {
                    tax = taxIncome * .10;
                } else if (taxIncome >= 9526 && taxIncome <= 38700) {
                    tax = 952.5 + (taxIncome - 9525) * .12;
                } else if (taxIncome >= 38701 && taxIncome <= 82500) {
                    tax = 5596.5 + (taxIncome - 38700) * .22;
                }
                afterTaxIncome = income - tax;
            }
            else {
                afterTaxIncome = income;
            }
        }
        else {
            if(income > 24000) {
                taxIncome = income - 24000;
                    if (taxIncome <= 19050) {
                        tax = taxIncome * .10;
                    } else if (taxIncome >= 19051 && taxIncome <= 77400) {
                        tax = 1905.1 + (taxIncome - 19050) * .12;
                    } else if (taxIncome >= 77401 && taxIncome <= 165000) {
                        tax = 9288 + (taxIncome - 77400) * .22;
                    }
                    afterTaxIncome = income - tax;
                }
                else {
                    afterTaxIncome = income;
                }
            }

        Intent intent = new Intent(MainActivity.this, ExpenseActivity.class);
        intent.putExtra("income", afterTaxIncome);
        Log.d("MainActivity", "taxIncome");
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}

