package com.example.bandhuapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

import androidx.print.PrintHelper;

public class NearByReport extends Activity {

    // String date[] = {"05/29","05/30","05/31","06/01","06/02" };

    String eIds[]  =  {"USER111","USER076","USER120","USER031","USER009" };
    String phoneno[] = {"9876543210","9843613388","9488370001","9876763211","9874444610" };

    TableLayout tl;
    TableRow tr;
    TextView eid,phone;
    Button btnNearbyReportPrint;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by_report);

        tl = (TableLayout) findViewById(R.id.maintable);
        btnNearbyReportPrint=(Button) findViewById(R.id.printnearbyreport);

        TextView title2 = findViewById((R.id.nearbycounttitle2));
        title2.setText("the past 15 days for "+getIntent().getStringExtra("EmployeeName"));

        title2.setText(getIntent().getStringExtra("EmployeeName"));

        btnNearbyReportPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                view = getWindow().getDecorView().findViewById(android.R.id.content);
                //           view=getWindow().getDecorView().findViewById(R.id.btnNearbyReport);
                view.setDrawingCacheEnabled(true);
                view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),View. MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
                view.buildDrawingCache(true);
                Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
                view.setDrawingCacheEnabled(false);

                //Print
                PrintHelper photoPrinter = new PrintHelper(NearByReport.this); // Asume that 'this' is your activity
                photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);
                photoPrinter.printBitmap("print", bitmap);

                Log.i("Print","onlick");
            }
        });

        addHeaders();
        addData();

    }


    /** This function add the headers to the table **/

    public void addHeaders(){
        /** Create a TableRow dynamically **/
        tr = new TableRow(this);
        tr.setLayoutParams(new LinearLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));

        /** Creating a TextView to add to the row **/
        TextView empIdTextView = new TextView(this);
        empIdTextView.setText("Employee Ids");
        empIdTextView.setTextColor(Color.BLACK);
        empIdTextView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        empIdTextView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
        empIdTextView.setPadding(5, 5, 5, 0);
        tr.addView(empIdTextView);  // Adding textView to tablerow.

        /** Creating another textview **/
        TextView phoneNoTextView = new TextView(this);
        phoneNoTextView.setText("Phone no");
        phoneNoTextView.setTextColor(Color.BLACK);
        phoneNoTextView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
        phoneNoTextView.setPadding(5, 5, 5, 0);
        phoneNoTextView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(phoneNoTextView); // Adding textView to tablerow.

        // Add the TableRow to the TableLayout
        tl.addView(tr, new TableLayout.LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));

        // we are adding two textviews for the divider because we have two columns
        tr = new TableRow(this);
        tr.setLayoutParams(new LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));

        /** Creating another textview **/
        TextView divider = new TextView(this);
        divider.setText("-----------------");
        divider.setTextColor(Color.BLACK);
        divider.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
        divider.setPadding(5, 0, 0, 0);
        divider.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(divider); // Adding textView to tablerow.
        TextView divider2 = new TextView(this);
        divider2.setText("-------------------------");
        divider2.setTextColor(Color.BLACK);
        divider2.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
        divider2.setPadding(5, 0, 0, 0);
        divider2.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(divider2); // Adding textView to tablerow.

        // Add the TableRow to the TableLayout
        tl.addView(tr, new TableLayout.LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));
    }

    /** This function add the data to the table **/

    public void addData(){
        for (int i = 0; i < eIds.length; i++)
        {
            /** Create a TableRow dynamically **/
            tr = new TableRow(this);
            tr.setLayoutParams(new LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
            /** Creating a TextView to add to the row **/
            eid = new TextView(this);
            eid.setText(eIds[i]);
            eid.setTextColor(Color.BLACK);
            eid.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            eid.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
            eid.setPadding(5, 5, 5, 5);
            tr.addView(eid);  // Adding textView to tablerow.

            /** Creating another textview **/
            phone = new TextView(this);
            phone.setText(phoneno[i]);
            phone.setTextColor(Color.BLACK);
            phone.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
            phone.setPadding(5, 5, 5, 5);
            phone.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(phone); // Adding textView to tablerow.

            // Add the TableRow to the TableLayout
            tl.addView(tr, new TableLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
        }

    }

}