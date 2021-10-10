package com.example.seprojectsemester5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.List;

public class PieChart extends AppCompatActivity {
    AnyChartView anyChartView;
    String[] criteria = {"None","Mild","Moderate","Sever"};
    int[] stats = {500,700,200,150,90};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        anyChartView = findViewById(R.id.newPieChart);
        setupPieChart();
    }

    public void setupPieChart(){
        Pie pie = AnyChart.pie();
        List<DataEntry> dataEntries = new ArrayList<>();
        for(int i=0;i<criteria.length;i++){
            dataEntries.add(new ValueDataEntry(criteria[i],stats[i]));
        }
        pie.data(dataEntries);
        anyChartView.setChart(pie);
    }
}