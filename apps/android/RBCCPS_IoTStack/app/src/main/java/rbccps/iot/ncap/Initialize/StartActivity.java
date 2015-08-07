/*
 *
 * $RCSfile: StartActivity.java $
 *
 * Copyright (c) 2015, RBCCPS, IISc Bangalore.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *    -	Redistributions of source code must retain the above
 *      copyright notice, this list of conditions and the following
 *      disclaimer.
 *    -	Redistributions in binary form must reproduce the above
 *      copyright notice, this list of conditions and the following
 *      disclaimer in the documentation and/or other materials provided
 *      with the distribution.
 *    -	Neither the name of RBCCPS, IISc Bangalore nor the names
 *      of its contributors may be used to endorse or promote products
 *      derived from this software without specific prior written
 *      permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package rbccps.iot.ncap.Initialize;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bluetoothleservice.R;

import java.util.HashMap;

import rbccps.iot.ncap.DA.CEP.Core.CEPManager;
import rbccps.iot.ncap.DTO.DataTransportObject;
import rbccps.iot.ncap.SNaaS.Adapter.BLE.Scan.BluetoothService;
import rbccps.iot.ncap.SNaaS.DP.UserEmailFetcher.UserEmailFetcher;

public class StartActivity extends Activity {


    private HashMap<String,String> BLE_Location_Map;
    public CEPManager manager;
    TextView voltTV;

    static boolean hit = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		Log.e("onCreate", "Activity");
		startService(new Intent(this, BluetoothService.class));
		UserEmailFetcher emailFetcher = new UserEmailFetcher();
		String email = UserEmailFetcher.getEmail(this);
		// Log.i("User Name", email);
		// BLEService.name = email;

       voltTV = (TextView) findViewById(R.id.AvgValue);


  /*
       Button start = (Button) findViewById(R.id.startServiceBTN);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Testing of COAP Connection //

                //initialize hashmap here and add the coordinates data like "ID":"x,y" ----->	"BeaconID1":"22,35"
                BLE_Location_Map=new HashMap<String,String>();
                BLE_Location_Map.put("BeaconID","100,100");
                //initializing CEPManager,change server address to where COAP Engine is running
                manager=new CEPManager();
                manager.setServerAddress("10.156.14.47", OutputHandler.COAP);
                //starting CEPEngine
                try {
                    manager.addStreamProcessor("Coordinates_rcver","int x,int y");
                } catch (UnknownHostException | lessArgumentsException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                manager.createEventQueue("coordinates");
                StreamProcessor Coordinates_Receiver=manager.getSourceStream("Coordinates_rcver");
                InputHandler handler = Coordinates_Receiver.getInputHandler();
                //please do add any query that you feel is suitable for running the module
                try {
                    handler.addQuery("x!=(0) and y!=(0)");
                } catch (differentTypesException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Coordinates_Receiver.setQueueId("coordinates");

		   *//*
			* We get the Beacon ID here and then add a query to call the coap server, for plotting the heatmap
			* I would like to add the query for pusing the current location info to the server for which we used a
			* hashmap, with beacon-id as key and beacon's x,y location as value.
			*
			* The x,y location was added to the query which is then sent to the server.
			*//*
                String x_and_y="100,100";
                StringInputParser parser=new StringInputParser();
                manager.addEventToQueue("coordinates",new TimeEventPair(System.currentTimeMillis(),parser.parse(x_and_y)));
            }
        });

*/

        Thread t = new Thread() {
            @Override
            public void run() {
                while (!isInterrupted()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // update TextView here!

                            voltTV.setText(DataTransportObject.Name + " - " +BluetoothService.volt + " - " + DataTransportObject.rssi);
                                             }
                    });
                }
            }
        };
        t.start();
        Button pushButton = (Button) findViewById(R.id.button);

        pushButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PushNotify("Sent Successfully!",
                        "Report Pushed to server");

            }
        });


                // startService(new Intent(this, BluetoothService.class));
                // finish();
    }



    @SuppressWarnings("deprecation")
    private void PushNotify(String notificationTitle, String notificationMessage) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        @SuppressWarnings("deprecation")
        Notification notification = new Notification(R.drawable.ic_launcher,
                "New Message", System.currentTimeMillis());

        Intent notificationIntent = new Intent(this, StartActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        notification.setLatestEventInfo(StartActivity.this, notificationTitle,
                notificationMessage, pendingIntent);
        notificationManager.notify(9999, notification);
    }


}