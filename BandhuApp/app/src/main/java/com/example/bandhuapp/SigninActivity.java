package com.example.bandhuapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class SigninActivity extends AppCompatActivity implements View.OnClickListener  {

    GoogleSignInClient mGoogleSignInClient;
    private static int RC_SIGN_IN = 100;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   View v = inflater.inflate(R.layout.activity_signin, container, false);
        setContentView(R.layout.activity_signin);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Set the dimensions of the sign-in button.
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        signInButton.setOnClickListener(this);

           }


    @Override
    protected void onStart() {
        // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            // ...
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.i("SignIN","Result code......"+resultCode);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 100) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            Log.i("SIgnIN","accout...."+account.getEmail());


            updateUI(account);
            // Signed in successfully, show authenticated UI.
           startActivity(new Intent(this,LauncherActivity.class));
           // startActivity(new Intent(this,RegisterActivity.class));

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("", "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    //Change UI according to user data.
    public void  updateUI(GoogleSignInAccount account){
        if(account != null){
            //Toast.makeText(this,"U Signed In successfully",Toast.LENGTH_LONG).show();
            String empName=getIntent().getStringExtra("EmployeeName");
            String empMob=getIntent().getStringExtra("EmployeeMobile");
            String devID=getIntent().getStringExtra("DeviceId");
            String seatNbr=getIntent().getStringExtra("SeatNbr");
            String priv=getIntent().getStringExtra("privilege");

            Log.i("Signin","employee name**********-......................"+getIntent().getStringExtra("EmployeeName"));

            Intent intent = new Intent(this, MainActivity.class);

            intent.putExtra("EmployeeName", empName);
            intent.putExtra("EmployeeMobile", empMob);
            intent.putExtra("DeviceId", devID);
            intent.putExtra("SeatNbr", seatNbr);
            intent.putExtra("privilege",priv);

            startActivity(intent); //new Intent(this,MainActivity.class));

            Log.i("Signin","employee name**********-......................"+getIntent().getStringExtra("EmployeeName"));
            finish();

            //startActivity(new Intent(this,RegisterActivity.class));
        }else {
            Toast.makeText(this,"Please sign in via Google API",Toast.LENGTH_LONG).show();
        }
    }
}
