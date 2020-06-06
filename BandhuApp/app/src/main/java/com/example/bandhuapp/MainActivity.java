package com.example.bandhuapp;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.MessageListener;
import com.google.android.gms.nearby.messages.MessagesClient;
import com.google.android.gms.nearby.messages.MessagesOptions;
import com.google.android.gms.nearby.messages.NearbyPermissions;
import com.google.android.gms.nearby.messages.PublishCallback;
import com.google.android.gms.nearby.messages.PublishOptions;
import com.google.android.gms.nearby.messages.Strategy;
import com.google.android.gms.nearby.messages.SubscribeCallback;
import com.google.android.gms.nearby.messages.SubscribeOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    /**
     * The entry point to Google Play Services.
     */
    private MessagesClient mMessagesClient;
    /**
     * The {@link Message} object used to broadcast information about the device to nearby devices.
     */
    private Message mPubMessage;

    /**
     * A {@link MessageListener} for processing messages from nearby devices.
     */
    private MessageListener mMessageListener;

    private List<String> messagesList;


    /**
     * The entry point to Google Play Services.
     */
    private GoogleApiClient mGoogleApiClient;


//        .setDiscoveryMode(Strategy.DISCOVERY_MODE_DEFAULT)
    //  .build();

    Button button;

    private ListView nearbyDevicesListView;
    //  private List<String> nearbyDevicesArrayList ;
    DeviceAdapter deviceAdapter;
    //TODO - UI display fields
    ArrayList<String> name;
    ArrayList<String> phoneNo;
    //int images[] = {R.drawable.ic_launcher_background, R.drawable.ic_launcher_background};
    int images[] = {R.drawable.social_dist, R.drawable.social_dist};
    private final static String default_notification_channel_id = "default";
    Handler mHandler;
    private static final int TTL_IN_SECONDS = 3 * 60; // Three minutes.
    /*
        private static final Strategy P2P_DISTANCE = new Strategy.Builder().setDistanceType(Strategy.DISTANCE_TYPE_DEFAULT)
                .setDiscoveryMode(Strategy.DISCOVERY_MODE_BROADCAST)
                .build();
        */
    private static final Strategy P2P_DISTANCE = new Strategy.Builder().setDistanceType(Strategy.DISTANCE_TYPE_EARSHOT)
            .setTtlSeconds(TTL_IN_SECONDS).build();


    private String empName;
    private String opponentEndpointId;
    private String opponentName;
    private TextView opponentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMessagesClient = Nearby.getMessagesClient(this, new MessagesOptions.Builder()
                    .setPermissions(NearbyPermissions.BLE)
                    .build());

        }


        mGoogleApiClient = new GoogleApiClient.Builder(this).addApi(Nearby.MESSAGES_API)
                .addConnectionCallbacks(this).enableAutoManage(this, this)
                .addOnConnectionFailedListener(this).build();

        mGoogleApiClient.connect();

        mMessageListener = new MessageListener() {
            @Override
            public void onFound(Message message) {
                Log.d(TAG, "Found message: " + new String(message.getContent()));

                //TODO - service call to store the details into DB
                //TODO alarmSound
/*
                Uri alarmSound =
                        RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), alarmSound);
                mp.start();

                // Notification
                Log.i(TAG, "Create Notification Channel....");
                createNotificationChannel();
                Log.i(TAG, "Create Notification Channel..completed..");
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(MainActivity.this, default_notification_channel_id)
                                .setSmallIcon(R.drawable.ic_launcher_foreground)
                                .setContentTitle("NearBy Device Found....")
                                .setContentText("Hello! You are nearby your peers...");
                Log.i(TAG, "Create Notification Builder....");
                NotificationManager mNotificationManager = (NotificationManager)
                        getSystemService(Context.NOTIFICATION_SERVICE);
                Log.i(TAG, "Create Notification Manager....");
                mNotificationManager.notify((int) System.currentTimeMillis(), mBuilder.build());
                Log.i(TAG, "Create Notification Notify....");
*/
                //TODO - service call to insert the data into db

                //Add the details into view

                byte publisedMsg[]=message.getContent();
                String mPubMsg=new String(publisedMsg);
                Log.i (TAG,"Publised msg........................................"+mPubMessage);
                boolean isExists = name.contains(mPubMsg);
                Log.i(TAG,"List cotnains......"+isExists);

                if (! name.contains(mPubMsg)) {
                    Log.i(TAG, "Add to the list and notify now...");
                    name.add(mPubMsg);
                    phoneNo.add("");//getIntent().getStringExtra("DeviceId"));
                    deviceAdapter.notifyDataSetChanged();
                }
            }

                /*if( ! name.contains(mPubMsg)) {

                    Log.i( TAG, "Add to the list and notify now...");
                    name.add(mPubMsg);
                    phoneNo.add(mPubMsg + "98765");

                    deviceAdapter.notifyDataSetChanged();
                }
            } */

            @Override
            public void onLost(Message message) {
                Log.d(TAG, "Lost sight of message: " + new String(message.getContent()));
            }
        };

        //TODO get details
        // Seat Allocation text - display, get intent
        // send nearby eid to the tables
        // set eid from intent instead of device name
        //empName = getDeviceName();

        empName = getIntent().getStringExtra("EmployeeName");//getDeviceName();

        Log.i("Main","Main Emp name**********-......................"+getIntent().getStringExtra("EmployeeName"));

        if (empName != null) {
            mPubMessage = new Message(empName.getBytes());
        }

        messagesList = new ArrayList<String>();

        TextView nameView = findViewById(R.id.name);
        nameView.setText(getString(R.string.codename, empName));

        TextView peersnearbyView = findViewById(R.id.peersnearby);
        peersnearbyView.setText(R.string.peersnearby);

        TextView seatNbrView = findViewById(R.id.seatnbr);
        seatNbrView.setText("Your allocated seat: "+getIntent().getStringExtra("SeatNbr"));

        //  nearbyDevicesArrayList = new ArrayList<String>();
        nearbyDevicesListView = (ListView) findViewById(R.id.nearby_devices_list_view);

        name = new ArrayList<String>();
        phoneNo = new ArrayList<String>();

        deviceAdapter = new DeviceAdapter(this, name, phoneNo, images);
        nearbyDevicesListView.setAdapter(deviceAdapter);
        //TODO - get phone no from previous screen

        TelephonyManager tMgr = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.i(TAG, "Phone number read status is denyed...");
        } else {
            String getSimSerialNumber = tMgr.getSimSerialNumber();
            String getSimNumber = tMgr.getLine1Number();


            Log.v(TAG, "getSimSerialNumber : " + getSimSerialNumber + " ,getSimNumber : " + getSimNumber);
            String mPhoneNumber = tMgr.getLine1Number();
            Log.i(TAG, "phone number...." + mPhoneNumber);

            String deviceId = tMgr.getDeviceId();
            Log.i(TAG, "deviceId...." + deviceId);
        }

    }

    private final Runnable m_Runnable = new Runnable() {
        public void run() {
            //Toast.makeText(MainActivity.this,"in runnable",Toast.LENGTH_SHORT).show();
            MainActivity.this.mHandler.postDelayed(m_Runnable, 30000);
            if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
                Log.i(TAG, "Run...");
                publish();
                subscribe();
            }
        }

    };

    public String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            Log.i(TAG, "getDeviceName()" + capitalize(model));
            return capitalize(model);
        } else {
            Log.i(TAG, "getDeviceName() capitalize...." + capitalize(manufacturer) + " " + model);
            return capitalize(manufacturer) + " " + model;
        }
    }


    private String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        //  Nearby.getMessagesClient(this).publish(mPubMessage);
        // Nearby.getMessagesClient(this).subscribe(mMessageListener);

        this.mHandler = new Handler();
        m_Runnable.run();

        /*
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int i=0;

                // Code here executes on main thread after user presses button
                if(mGoogleApiClient != null && mGoogleApiClient.isConnected())
                {
                    String msg1=" Hello "+i;
                    mPubMessage = new Message(msg1.getBytes());

                    byte msgByte[]=mPubMessage.getContent();
                    String pubMsg=new String(msgByte);

                    if(! messagesList.contains(pubMsg)) {
                        Log.i(TAG,"----msgByte---"+pubMsg);
                        publish();
                        subscribe();
                        messagesList.add(pubMsg);
//                        Log.i(TAG, "Message..." + messagesList.get(0));
                        i++;
                        Log.i(TAG, "i value........" + i);
                    }

                }
                else
                {
                    Log.i(TAG, "waiting for connection...");

                }
                //publish();
               // subscribe();
            }
        });
*/
        //   Nearby.Messages.subscribe(mMessagesClient, getPendingIntent());
        Log.i(TAG, "OnStart ends....");
    }

    @Override
    public void onStop() {
        //Nearby.getMessagesClient(this).unpublish(mPubMessage);
        //Nearby.getMessagesClient(this).unsubscribe(mMessageListener);
        super.onStop();
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            unpublish();
            unsubscribe();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i(TAG, "GoogleApiClient connected");
        // We use the Switch buttons in the UI to track whether we were previously doing pub/sub (
        // switch buttons retain state on orientation change). Since the GoogleApiClient disconnects
        // when the activity is destroyed, foreground pubs/subs do not survive device rotation. Once
        // this activity is re-created and GoogleApiClient connects, we check the UI and pub/sub
        // again if necessary.

        Log.i(TAG, "Publish is going to initiate...");

        publish();
        Log.i(TAG, "Publish is completed...");
        // publish();

        Log.i(TAG, "Subscribe yet to start....");
        subscribe();
        Log.i(TAG, "Subscribe completed");


    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        //mPublishSwitch.setEnabled(false);
        //mSubscribeSwitch.setEnabled(false);
        Log.i(TAG, "Exception while connecting to Google Play services: " +
                connectionResult.getErrorMessage());
        Log.d(TAG, "Exception while connecting to Google Play services: " +
                connectionResult.getErrorMessage());
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Connection suspended ...");

    }

    private void subscribe() {
        Log.i(TAG, "Subscribing");

        SubscribeOptions options = new SubscribeOptions.Builder()
                .setStrategy(P2P_DISTANCE)
                .setCallback(new SubscribeCallback() {
                    @Override
                    public void onExpired() {
                        super.onExpired();
                        Log.i(TAG, "No longer subscribing");

                    }
                }).build();

        Nearby.Messages.subscribe(mGoogleApiClient, mMessageListener, options)
                .setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        if (status.isSuccess()) {
                            Log.i(TAG, "Subscribed successfully.");

                        } else {
                            Log.i(TAG, "Could not subscribe, status = " + status);

                        }
                    }
                });
    }

    /**
     * Publishes a message to nearby devices and updates the UI if the publication either fails or
     * TTLs.
     */

    private void publish() {
        Log.i(TAG, "Publishing");
        PublishOptions options = new PublishOptions.Builder()
                .setStrategy(P2P_DISTANCE)
                .setCallback(new PublishCallback() {
                    @Override
                    public void onExpired() {
                        super.onExpired();
                        Log.i(TAG, "No longer publishing");

                    }
                }).build();
        Log.i(TAG, "Publishing options " + options.toString());

        if (empName != null) {

        Nearby.Messages.publish(mGoogleApiClient, mPubMessage, options)
                .setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        if (status.isSuccess()) {
                            Log.i(TAG, "Published successfully.");
                        } else {
                            Log.i(TAG, "Could not publish, status = " + status);

                        }
                    }
                });
    }
}
    /**
     * Stops subscribing to messages from nearby devices.
     */

    private void unsubscribe() {
        Log.i(TAG, "Unsubscribing.");
        Nearby.Messages.unsubscribe(mGoogleApiClient, mMessageListener);
    }

    /**
     * Stops publishing message to nearby devices.
     */

    private void unpublish() {
        Log.i(TAG, "Unpublishing.");
        Nearby.Messages.unpublish(mGoogleApiClient, mPubMessage);
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "SocialDistancing";
            String description = "You are close to someone";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(default_notification_channel_id, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    class DeviceAdapter extends ArrayAdapter<String> {

        Context context;
        ArrayList<String> eName;
        ArrayList<String> ePoneNo;
        int eImg[];

        DeviceAdapter (Context c, ArrayList<String> name, ArrayList<String> phoneno, int imgs[])
        {
            super(c,R.layout.nearbydevices_layout,R.id.empname,name);
            context=c;
            eName=name;
            ePoneNo=phoneno;
            eImg=imgs;

        }

        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {
            LayoutInflater layoutInflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.nearbydevices_layout,parent,false);
            ImageView images= row.findViewById(R.id.image);
            TextView ename=row.findViewById(R.id.empname);
            TextView ephone=row.findViewById(R.id.phoneno);

            //now set our resources on Views
            images.setImageResource(eImg[0]);
            ename.setText(eName.get(position));
            ephone.setText(ePoneNo.get(position));

            return row;
        }
    }
}