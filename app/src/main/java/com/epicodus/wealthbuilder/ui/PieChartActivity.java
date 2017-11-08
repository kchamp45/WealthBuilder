package com.epicodus.wealthbuilder.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.epicodus.wealthbuilder.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PieChartActivity extends AppCompatActivity {
    @Bind(R.id.idPieChart) PieChart mPieChart;
    private List<Integer> yData = new ArrayList<Integer>();
    private String[] xData = {"Housing", "Transportation", "Food", "Misc"};
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        int iHousing = intent.getIntExtra("housing", 0);
        yData.add(iHousing);
        int iTrans = intent.getIntExtra("transportation", 0);
        yData.add(iTrans);
        int iFood = intent.getIntExtra("food", 0);
        yData.add(iFood);
        int iMisc = intent.getIntExtra("misc", 0);
        yData.add(iMisc);

        mPieChart.setRotationEnabled(true);
        mPieChart.setHoleRadius(25f);
        mPieChart.setTransparentCircleAlpha(0);
        mPieChart.setCenterText("Expenses");
        mPieChart.setCenterTextSize(10);

       addDataSet(mPieChart);
   }

    public void addDataSet(PieChart chart) {
       List<PieEntry> entries = new ArrayList<>();
       for (int i = 0; i < yData.size(); i++) {
           entries.add(new PieEntry(yData.get(i), xData[i]));
        }

        PieDataSet pieDataSet = new PieDataSet(entries, "Expenses");
        PieData data = new PieData(pieDataSet);
        mPieChart.setData(data);
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);
        mPieChart.invalidate();

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        colors.add(Color.MAGENTA);

        pieDataSet.setColors(colors);

        Legend legend = mPieChart.getLegend();
        legend.setForm(Legend.LegendForm.SQUARE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
    }
}