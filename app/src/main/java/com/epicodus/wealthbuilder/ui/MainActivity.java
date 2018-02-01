package com.epicodus.wealthbuilder.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
    @Bind(R.id.incomeEditText)
    EditText mIncomeEditText;
    @Bind(R.id.submitIncomeButton)
    Button mSubmitIncomeButton;
    @Bind(R.id.radioButton1)
    RadioButton mRadioButton1;
    @Bind(R.id.radioButton2)
    RadioButton mRadioButton2;
    @Bind(R.id.perExemption) EditText mPerExemption;

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
        if (TextUtils.isEmpty(mIncomeEditText.getText()) || TextUtils.isEmpty(mPerExemption.getText()))
        {
            Toast.makeText(getApplicationContext(), "income is empty", Toast.LENGTH_SHORT).show();

        } else {
        int income = Integer.parseInt(mIncomeEditText.getText().toString());
        int n = Integer.parseInt(mPerExemption.getText().toString());
        int taxIncome = 0;
        double afterTaxIncome = 0;
        double tax = 0;

        if (mRadioButton1.isChecked()) {

            afterTaxIncome = income - this.sFedTax(income) - this.sOrTax(income, n);
        }
        if (mRadioButton2.isChecked()) {

            afterTaxIncome = income - this.mFedTax(income) - this.mOrTax(income, n);
        }

        if (v == mSubmitIncomeButton) {


                Intent intent = new Intent(getApplicationContext(), ExpenseActivity.class);
                intent.putExtra("income", afterTaxIncome);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "$$$$$!", Toast.LENGTH_LONG).show();
            }
        }
    }

    double sFedTax(int income) {
        double sfedTax = 0;
        double fedTaxableInc = 0;

        if (income > 12000) {
            fedTaxableInc = income - 12000;
            if (fedTaxableInc <= 9525) {
                sfedTax = fedTaxableInc * .10;
            } else if (fedTaxableInc > 9525 && fedTaxableInc <= 38700) {
                sfedTax = 952.5 + (fedTaxableInc - 9525) * .12;
            } else if (fedTaxableInc > 38700 && fedTaxableInc <= 82500) {
                sfedTax = 5596.5 + (fedTaxableInc - 38700) * .22;
            } else if(fedTaxableInc >82500 && fedTaxableInc<= 157500) {
                sfedTax = 18150 + (fedTaxableInc - 82500) * .24;
            } else if(fedTaxableInc > 157500 && fedTaxableInc <= 200000) {
                sfedTax = 37800 + (fedTaxableInc - 157500) * .32;
            } else if(fedTaxableInc > 200000 && fedTaxableInc <= 500000) {
                sfedTax = 64000 + (fedTaxableInc - 200000) * .35;
            } else {
                sfedTax = 175000 + (fedTaxableInc - 500000) * .37;
            }

        }
        return sfedTax;
    }

    double mFedTax(int income) {
        double mfedTax = 0;
        double fedTaxableInc = 0;
        if (income > 24000) {
            fedTaxableInc = income - 24000;
            if (fedTaxableInc <= 19050) {
                mfedTax = fedTaxableInc * .10;
            } else if (fedTaxableInc > 19050 && fedTaxableInc <= 77400) {
                mfedTax = 1905.1 + (fedTaxableInc - 19050) * .12;
            } else if (fedTaxableInc > 77400 && fedTaxableInc <= 165000) {
                mfedTax = 9288 + (fedTaxableInc - 77400) * .22;
            } else if(fedTaxableInc > 165000 && fedTaxableInc <= 315000) {
                mfedTax = 36300 + (fedTaxableInc - 165000) * .24;
            } else if(fedTaxableInc > 315000 && fedTaxableInc <= 400000) {
                mfedTax = 75600 + (fedTaxableInc - 315000) * .32;
            } else if(fedTaxableInc > 400000 && fedTaxableInc <= 600000) {
                mfedTax = 128000 + (fedTaxableInc - 400000) * .35;
            } else {
                mfedTax = 210000 + (fedTaxableInc - 600000) * .37;
            }
        }
        return mfedTax;
    }

    double sOrTax(int income, int n) {
        double sOrTax = 0;
        double OrTaxableInc = 0;
        double OrIncome = 0;
        OrIncome = income - 2175 - this.sOrFedExemption(income) - this.OrPerExemption(n) - 197;
        if(OrIncome > 0 && OrIncome < 3350){
            sOrTax = OrIncome * .05;
        } else if(OrIncome >= 3350 && OrIncome < 8500){
            sOrTax = 167.45 + (OrIncome - 3350) * .07;
        } else if(OrIncome >= 8450 && OrIncome < 125000) {
            sOrTax = 524.45 + (OrIncome - 8450) * .09;
        } else if (OrIncome >= 125000){
            sOrTax = 11013.86 + (OrIncome - 125000) * .099;
        } else {
            sOrTax = 0;
        }
        return sOrTax;
    }

    double mOrTax(int income, int n) {
        double mOrTax = 0;
        double OrTaxableInc = 0;
        double OrIncome = 0;
        OrIncome = income - 4350 - this.mOrFedExemption(income) - this.OrPerExemption(n) - 394;
        if(OrIncome > 0 && OrIncome < 6700){
            mOrTax = OrIncome * .05;
        } else if(OrIncome >= 6700 && OrIncome < 16900){
            mOrTax = 335 + (OrIncome - 6700) * .07;
        } else if(OrIncome >= 16900 && OrIncome < 250000) {
            mOrTax = 1049 + (OrIncome - 16900) * .09;
        } else if (OrIncome >= 250000){
            mOrTax = 22028 + (OrIncome - 250000) * .099;
        } else {
            mOrTax = 0;
        }
        return mOrTax;
    }

    double sOrFedExemption(int income){
        double fedExemption = 0;
        if(income > 0 && income < 125000){
            if (this.sFedTax(income) < 6550){
                fedExemption = this.sFedTax(income);
            } else {
                fedExemption = 6550;
            }
        } else if(income >= 125000 && income < 130000) {
            if (this.sFedTax(income) < 5200){
                fedExemption = this.sFedTax(income);
            } else {
                fedExemption = 5200;
            }
        } else if(income >= 130000 && income < 135000) {
            fedExemption = 3900;
        } else if(income >= 135000 && income < 140000) {
            fedExemption = 2600;
        } else if(income >= 140000 && income < 145000) {
            fedExemption = 1300;
        } else {
            fedExemption = 0;
        }
        return fedExemption;
    }

    double mOrFedExemption(int income){
        double fedExemption = 0;
        if(income > 0 && income <= 250000){
            if (this.mFedTax(income) < 6550){
                fedExemption = this.mFedTax(income);
            } else {
                fedExemption = 6550;
            }
        } else if(income > 250000 && income <= 260000) {
            fedExemption =  5200;
        } else if(income > 260000 && income <= 270000) {
            fedExemption = 3900;
        } else if(income > 270000 && income <= 280000) {
            fedExemption = 2600;
        } else if(income > 280000 && income <= 290000) {
            fedExemption = 1300;
        } else {
            fedExemption = 0;
        }
        return fedExemption;
    }
    double OrPerExemption(int n){

        int OrPerExemption = n * 197;
        return OrPerExemption;
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
