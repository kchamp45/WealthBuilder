package com.epicodus.wealthbuilder.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.epicodus.wealthbuilder.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class iraActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.iraInfoTextView) TextView mIRAInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ira);
        ButterKnife.bind(this);

        mIRAInfo.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        if (v == mIRAInfo) {
            Uri webpage = Uri.parse("http://money.cnn.com/retirement/guide/IRA_Basics.moneymag/index.htm");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        }
    }
}
