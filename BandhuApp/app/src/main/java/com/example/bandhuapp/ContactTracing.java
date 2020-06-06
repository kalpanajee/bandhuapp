package com.example.bandhuapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;

public class ContactTracing extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_tracing);
        BarChart chart = findViewById(R.id.barchart);

        TextView titleView1 = findViewById(R.id.nearbyempcount1);
        titleView1.setText("Nearby Contacts for ");

        TextView titleView2 = findViewById((R.id.nearbyempcount2));
        titleView2.setText("the past 15 days - "+getIntent().getStringExtra("EmployeeName"));

        ArrayList NoOfEmp = new ArrayList();

        //TODO - Service call to get the count corresponding to the specific employee
        // store it in the val field
        NoOfEmp.add(new BarEntry(2f, 0));
        NoOfEmp.add(new BarEntry(10f, 1));
        NoOfEmp.add(new BarEntry(11f, 2));
        NoOfEmp.add(new BarEntry(12f, 3));
        NoOfEmp.add(new BarEntry(1f, 4));
        NoOfEmp.add(new BarEntry(18f, 5));
        NoOfEmp.add(new BarEntry(10f, 6));
        NoOfEmp.add(new BarEntry(16f, 7));
        NoOfEmp.add(new BarEntry(9f, 8));
        NoOfEmp.add(new BarEntry(5f, 9));
        NoOfEmp.add(new BarEntry(3f, 10));
        NoOfEmp.add(new BarEntry(7f, 11));
        NoOfEmp.add(new BarEntry(10f, 12));
        NoOfEmp.add(new BarEntry(6f, 13));
        NoOfEmp.add(new BarEntry(5f, 14));


        //Days
        ArrayList days = new ArrayList();
        days.add("1");
        days.add("2");
        days.add("3");
        days.add("4");
        days.add("5");
        days.add("6");
        days.add("7");
        days.add("8");
        days.add("9");
        days.add("10");
        days.add("11");
        days.add("12");
        days.add("13");
        days.add("14");
        days.add("15");

        BarDataSet bardataset = new BarDataSet(NoOfEmp, "No Of Employee");
        chart.animateY(5000);
        BarData data = new BarData(days, bardataset);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.setData(data);

        for (int j = 0; j < chart.getData().getDataSets().size(); j++) {
            IDataSet iDataSet = chart.getData().getDataSets().get(j);
            Log.i("Bar ","inside j..."+iDataSet);
            for (int i = 0; i < ((BarDataSet) iDataSet).getYVals().size(); i++) {
                ((BarDataSet) iDataSet).setValueFormatter(new MyFormattedValue());
            }
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    class MyFormattedValue implements ValueFormatter {
        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            return "" + ((int) value);
        }
    }
}