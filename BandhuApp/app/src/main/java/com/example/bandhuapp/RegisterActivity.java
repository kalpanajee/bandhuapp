package com.example.bandhuapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class RegisterActivity extends AppCompatActivity {

    EditText employee;
    EditText mobile;
    Button register;
    TextView login;


    private static final String TAG = "RegisterActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        employee=findViewById(R.id.employee);
        mobile=findViewById(R.id.mobile);
        register=findViewById(R.id.register);
        login=findViewById(R.id.textViewLink);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDataEntered();

            }
        });

    }

    void checkDataEntered() {
        boolean validData=true;
        if (isEmpty(employee)){
            Toast t = Toast.makeText(this,"You must provide your employee id to register !", Toast.LENGTH_SHORT);
            t.show();
            validData=false;
        }
        if (validData==true && isEmpty(mobile)){
            Toast t = Toast.makeText(this,"You must provide your mobile number to register !", Toast.LENGTH_SHORT);
            t.show();
            validData=false;
        }

        if (validData==true) {
            // regEmpDetails();
            String adminIds = null;
            String userIds = null;
            try {
                adminIds=Util.getProperty("admin.empids",this);
                userIds=Util.getProperty("user.empids",this);
                String empId = employee.getText().toString();
                String userType = "admin";
                Log.v("Emp ID: ", empId);
                if (adminIds.indexOf(empId)!=-1){
                    userType="admin";
                }else{
                    userType="user";
                }
                Log.v("User Type: ", userType);

                //Pick up the user name from properties file
//                String empName = Util.getProperty(empId.toUpperCase(),this);
//                Log.v("Emp Name: ", empName);

                //Pick up the user name from properties file
                String propertyText = Util.getProperty(empId.toUpperCase(),this);
                List<String> tokens = new ArrayList<>();
                StringTokenizer tokenizer = new StringTokenizer(propertyText, ",");
                while (tokenizer.hasMoreElements()) {
                    tokens.add(tokenizer.nextToken());
                }
                String empName =tokens.get(0);
                String mob = tokens.get(1);
                String deviceid = tokens.get(2);
                String seatNbr = tokens.get(3);

                Intent intent = null;
            //If user type is Admin invoke AdminActivity, if user type is User invoke SigninActivity
            if (userType.equalsIgnoreCase("admin")){
                intent = new Intent(RegisterActivity.this, AdminActivity.class);
            }else{
                intent = new Intent(RegisterActivity.this, SigninActivity.class);
            }
                intent.putExtra("EmployeeName", empName);
                intent.putExtra("EmployeeMobile", mob);
                intent.putExtra("DeviceId", deviceid);
                intent.putExtra("SeatNbr", seatNbr);
                intent.putExtra("privilege",userType);

                startActivity(intent);


            /* TODO get values from service response and set it to intent */

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    boolean isEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

            /*try {


                String postUrl = "";
                String endPoint = null;
                String service = null;

                //Response is a JSON object
                try {
                    endPoint = Util.getProperty("endpoint.url", getApplicationContext());
                    service = Util.getProperty("service.registration", getApplicationContext());
                }catch(Exception e){
                    endPoint = "http://10.7.212.162:8333/v1.0.0/Nvlty/NvltyService";
                    service = "regSvc";
                }

                postUrl = endPoint + service ;
                RegistrationData regData = new RegistrationData("DDVID",employee.getText().toString().toUpperCase(),"","","",mobile.getText().toString(),"","");
                syncCall(regData, postUrl);






            }catch(Exception e){
                //to handle json exception, parked
                e.printStackTrace();
            } */

        }

   /* public void syncCall(RegistrationData regData, String postUrl){
        Log.v("syncCall","entered");
        String rspCde = null;
        String fname = null;
        String lname = null;
        String usrTyp = null;
        String eid = null;


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("Your domain URL here")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api apiInterface = retrofit.create(Api .class);
        apiInterface.getStringScalar(regData);

        Log.v("Inflating the menu post","success");
        Intent intent = new Intent(MainActivity.this, MainMenu.class);
        intent.putExtra("EmployeeName", fname + lname);
        startActivity(intent);
        Log.v("Called MainMenuHome","success");
    } */
