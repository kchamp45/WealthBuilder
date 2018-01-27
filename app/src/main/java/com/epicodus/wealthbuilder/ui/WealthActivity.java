package com.epicodus.wealthbuilder.ui;

import android.content.Intent;
import android.net.Uri;
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

public class WealthActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.mutualFundsTextView) TextView mMutualFunds;
    @Bind(R.id.iRATextView) TextView mIRA;
    @Bind(R.id.rEITTextView) TextView mREIT;
    @Bind(R.id.c529TextView) TextView m529;
    @Bind(R.id.accountTextView) TextView mAccountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wealth);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String initDeposit = intent.getStringExtra("iDeposit");
        int iDeposit = Integer.parseInt(initDeposit);
        String addDeposit = intent.getStringExtra("aDeposit");
        int aDeposit = Integer.parseInt(addDeposit);
        String duration = intent.getStringExtra("duration");
        int years = Integer.parseInt(duration);

        double FV = 0;
        for (int i = 1; i <= years; i++) {
            FV = (FV + (aDeposit*12)) * (1.07);
        }
        double totalFV = FV + iDeposit * Math.pow(1.07, years);
        double roundOff = Math.floor(totalFV*100)/100;

        Log.d("WealthActivity", "Here is the " + "$"+totalFV);

        mAccountTextView.setText("CONGRATULATIONS! Here is your future account value: " + "$"+roundOff);

        mMutualFunds.setOnClickListener(this);
        m529.setOnClickListener(this);
        mIRA.setOnClickListener(this);
        mREIT.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == mMutualFunds) {
            Uri webpage = Uri.parse("https://www.kiplinger.com/slideshow/investing/T033-S002-kip-25-best-no-load-mutual-funds-2017/index.html");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        }
        if(v == m529){
            Uri webpage = Uri.parse("https://www.savingforcollege.com/intro-to-529s/what-is-a-529-plan");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        }
        if(v == mIRA) {
            Intent newIntent = new Intent(WealthActivity.this, iraActivity.class);
            startActivity(newIntent);
        }
//        if(v == mIRA){
//                Uri webpage = Uri.parse("http://money.cnn.com/retirement/guide/IRA_Basics.moneymag/index.htm");
//                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
//                startActivity(webIntent);
//        }
        if(v == mREIT){
            Uri webpage = Uri.parse("https://www.investopedia.com/articles/etfs/top-real-estate-etfs/");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        }
    }


}
