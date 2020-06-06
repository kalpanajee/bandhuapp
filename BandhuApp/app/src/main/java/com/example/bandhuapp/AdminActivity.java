package com.example.bandhuapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

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


/*        Intent intent = new Intent(this, AdminActivity.class);

        intent.putExtra("EmployeeName", empName);
        intent.putExtra("EmployeeMobile", empMob);
        intent.putExtra("DeviceId", devID);
        intent.putExtra("SeatNbr", seatNbr);
        intent.putExtra("privilege",priv);

        startActivity(intent);

        Log.i("Admin","employee name**********-......................"+getIntent().getStringExtra("EmployeeName"));
        finish(); */

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        String empName=getIntent().getStringExtra("EmployeeName");
        String empMob=getIntent().getStringExtra("EmployeeMobile");
        String devID=getIntent().getStringExtra("DeviceId");
        String seatNbr=getIntent().getStringExtra("SeatNbr");
        String priv=getIntent().getStringExtra("privilege");
        switch (item.getItemId()) {
            case R.id.mnSeatAlloc:
                Intent intent1 = new Intent(this, SeatAllocation.class);
                this.startActivity(intent1);
                return true;
            case R.id.mnContactTracing:
                Intent intent2 = new Intent(this, ContactTracing.class);
                intent2.putExtra("EmployeeName", empName);
                intent2.putExtra("EmployeeMobile", empMob);
                intent2.putExtra("DeviceId", devID);
                intent2.putExtra("SeatNbr", seatNbr);
                intent2.putExtra("privilege",priv);
                this.startActivity(intent2);
                //finish();
                return true;
            case R.id.mnNearByReport:
                Intent intent3 = new Intent(this, NearByReport.class);
                intent3.putExtra("EmployeeName", empName);
                intent3.putExtra("EmployeeMobile", empMob);
                intent3.putExtra("DeviceId", devID);
                intent3.putExtra("SeatNbr", seatNbr);
                intent3.putExtra("privilege",priv);
                this.startActivity(intent3);
                //finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

class MyFormattedValue implements ValueFormatter {
    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return "" + ((int) value);
    }
}

}
