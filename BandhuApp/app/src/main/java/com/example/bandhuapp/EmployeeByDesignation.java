package com.example.bandhuapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;

public class EmployeeByDesignation extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_by_designation);
        PieChart pieChart = findViewById(R.id.piechart);
        TextView title=findViewById(R.id.piechartitle);
        TextView workforce=findViewById(R.id.workforce);

        ArrayList bandSpecEmp = new ArrayList();

        //TODO - service call to get the designation count for a specific day
        bandSpecEmp.add(new Entry(25f, 0));
        bandSpecEmp.add(new Entry(10f, 1));
        bandSpecEmp.add(new Entry(5f, 2));
        bandSpecEmp.add(new Entry(8f, 3));

        PieDataSet dataSet = new PieDataSet(bandSpecEmp, "Employee Band Specific");

        ArrayList designation = new ArrayList();
        designation.add("FTE");
        designation.add("Contractors");
        designation.add("House Keeping");
        designation.add("Security");
        PieData data = new PieData(designation, dataSet);
        pieChart.setData(data);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.animateXY(5000, 5000);

        for (int j = 0; pieChart.getData().getDataSets().size() > j; j++) {
            IDataSet iDataSet = pieChart.getData().getDataSets().get(j);
            Log.i("Pie Chart","inside j..."+iDataSet);
            for (int i = 0; i < ((PieDataSet) iDataSet).getYVals().size(); i++) {
                ((PieDataSet) iDataSet).setValueFormatter(new MyFormattedValue());
            }
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    class MyFormattedValue implements ValueFormatter{
        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            return "" + ((int) value);
        }
    }
}