package com.example.seprojectsemester5.dataanalyst;

import com.anychart.AnyChart;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.example.seprojectsemester5.databinding.DataAnalystPieChartBinding;

import java.util.ArrayList;
import java.util.List;

public class PieChart {
    private final DataAnalystPieChartBinding binding;

    private final String[] criteria = {"None","Mild","Moderate","Sever"};
    private final int[] stats = {500,700,200,150,90};

    PieChart(DataAnalystPieChartBinding binding) {
        this.binding = binding;
    }

    public void setupPieChart(){
        Pie pie = AnyChart.pie();
        List<DataEntry> dataEntries = new ArrayList<>();
        for(int i=0;i<criteria.length;i++){
            dataEntries.add(new ValueDataEntry(criteria[i],stats[i]));
        }
        pie.data(dataEntries);
        binding.newPieChart.setChart(pie);
    }
}