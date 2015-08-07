/*
 *
 * $RCSfile: BluetoothService.java $
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

package rbccps.iot.ncap.SNaaS.Adapter.BLE.Scan;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import rbccps.iot.ncap.BLDM.Subscriber.DASubscriber;
import rbccps.iot.ncap.BLDM.Subscriber.SNaaSSubscriber;
import rbccps.iot.ncap.DA.CEP.Core.CEPManager;
import rbccps.iot.ncap.DA.CEP.Core.InputHandler;
import rbccps.iot.ncap.DA.CEP.Core.OutputHandler;
import rbccps.iot.ncap.DA.CEP.Core.StreamProcessor;
import rbccps.iot.ncap.DA.CEP.Core.differentTypesException;
import rbccps.iot.ncap.DA.CEP.Core.lessArgumentsException;
import rbccps.iot.ncap.DA.CEP.InputParser.StringInputParser;
import rbccps.iot.ncap.DA.Subscriber.BLDMSubscriber;
import rbccps.iot.ncap.DTO.DataTransportObject;
import rbccps.iot.ncap.Initialize.BeaconHeatmapPosition;
import rbccps.iot.ncap.SNaaS.CP.TEDS.LocalCache.TEDSDataStore;
import rbccps.iot.ncap.SNaaS.CPDPSwitch.CPDPSwitch;
import rbccps.iot.ncap.StateMachine.ApplicationContext;

public class BluetoothService extends Service {

    private BluetoothAdapter mBLEAdapter;
    private BluetoothManager bmanager;
    private Handler scanHandler;
    private HashMap<String,String> BLE_Location_Map;
    public static CEPManager manager;
    public static StringInputParser parser;
    public static boolean start = false;
    public static boolean stop = false;
    public static float volt;

    File iroot;
    File file;

    static int level;
    static long temperature;
    static int voltage;

    static String firstPacket;
    static String lastPacket;
    static boolean isfirstPacket = false;

    BufferedWriter bw = null;
    static String BLE_Data;
    static String time;
    static String mac_address;
    static String name;
    static String type;
    static String rssi_value;
    static String Count;
    static int rssivalue;
    static int count = 0;
    static String BLEID_RSSI;
    static long time_stamp;


    // test

    private TEDSDataStore datastore;


    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();

        ApplicationContext applicationContext = new ApplicationContext(BluetoothService.this);
        //initialize hashmap here and add the coordinates data like "ID":"x,y" ----->	"BeaconID1":"22,35"
        BeaconHeatmapPosition.setHeatMapPosition();

        SNaaSSubscriber BLDMeventRegister = new SNaaSSubscriber();
        BLDMSubscriber DAeventRegister = new BLDMSubscriber();
        DASubscriber DASubscriber = new DASubscriber();
        BLDMeventRegister.register();
        DAeventRegister.register();
        DASubscriber.register();


        this.registerReceiver(this.batteryInfoReceiver, new IntentFilter(
                Intent.ACTION_BATTERY_CHANGED));
        iroot = Environment.getExternalStorageDirectory();
        file = new File(iroot, "Log.txt"); // Change to Name



        //initializing CEPManager,change server address to where COAP Engine is running
        manager=new CEPManager();
        parser = new StringInputParser();
        manager.setServerAddress("10.156.14.142",OutputHandler.COAP);
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

        Log.e("BLEService", "onCreate");

        turnonBLE();
        discoverBLEDevices();
        CPDPSwitch.createList();


    }

    private void turnonBLE() {
        // TODO Auto-generated method stub
        bmanager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        mBLEAdapter = bmanager.getAdapter();
        mBLEAdapter.enable();
        Toast.makeText(getApplicationContext(), "BTLE ON Service",
                Toast.LENGTH_LONG).show();
        Log.e("BLEService", "TurnOnBLE");
    }

    private void discoverBLEDevices() {
        // TODO Auto-generated method stub
        // startScan.run();
        mBLEAdapter.startLeScan(mLeScanCallback);
        Log.e("BLEService", "DiscoverBLE");

    }

    private Runnable startScan = new Runnable() {
        @Override
        public void run() {
            scanHandler.postDelayed(stopScan, 5000);
            mBLEAdapter.startLeScan(mLeScanCallback);

            /*Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            r.play();*/
        }
    };


    private Runnable stopScan = new Runnable() {
        @Override
        public void run() {
            scanHandler.postDelayed(startScan, 10);
            mBLEAdapter.stopLeScan(mLeScanCallback);
        }
    };



    // Device scan callback.
    public BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {

        @Override
        public void onLeScan(final BluetoothDevice device, int rssi,
                             byte[] scanRecord) {



            Log.e("BLEService", "onLeScan");
            DataTransportObject.time_stamp = System.currentTimeMillis();
            DataTransportObject.rssi = rssi;
            DataTransportObject.Address = device.getAddress();
            DataTransportObject.Name = device.getName();

            // Simply print all raw bytes


            rssivalue = rssi;
            //Log.e("BLEService", "onLeScan");
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                    .format(Calendar.getInstance().getTime());
            count += 1;
            Count = "S.No : " + count;
            // time = " Time : " + c.ge
            // name = " Prasant : " + device.getName();
            mac_address = " Address : " + device.getAddress();
            type = " Type : " + device.getType();
            rssi_value = "RSSI : " + rssi;

            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            Date date = new Date();

			/*
			 * Log.e("S.No :" +count + " Device : ", "Time : " +
			 * dateFormat.format(date) + " Name : " + device.getName() +
			 * " Address : " + device.getAddress() + " Type : " +
			 * device.getType() + " RSSI : " + rssi);
			 */

            if (!isfirstPacket) {
                firstPacket = dateFormat.format(date);
                isfirstPacket = true;
            }

            BLEID_RSSI = "" + rssi;
            time_stamp = System.currentTimeMillis();



                try {
                    String decodedRecord = new String(scanRecord, "UTF-8");
                    // String bytes = ByteArrayToString(scanRecord);
                    //
                    ByteArrayToString(scanRecord);

                    // Log.d("DEBUG", "decoded String : " + ByteArrayToString(scanRecord));
                    //  Log.d("DEBUG", "decoded String : " +Integer.parseInt(ByteArrayToString(scanRecord), 16));
                    BLE_Data = Count + " : " + device.getName() + " : " + firstPacket  + " : " + BLEID_RSSI
                            + " : " + volt + " : " + dateFormat.format(date) + " : " + level + " : "
                            + temperature + " : " + voltage;

                    // Log.e("Data :", BLE_Data);

                    writetofile();

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }




            if (DataTransportObject.Name.contains("SJRI_ICU_PUMP")) {
                Log.e("BLEService", "SJRI_ICU_PUMP");
                Toast.makeText(getApplicationContext(), "Pump Pressed",
                        Toast.LENGTH_LONG).show();
            }

            if (DataTransportObject.Name == "SJRI_ICU_PUMP") {
                Log.e("BLEService", "SJRI_ICU_PUMP");
                Toast.makeText(getApplicationContext(), "Pump Pressed",
                        Toast.LENGTH_LONG).show();
            }


            // datastore = new TEDSDataStore(ApplicationContext.context);

		   /*
			* We get the Beacon ID here and then add a query to call the coap server, for plotting the heatmap
			* I would like to add the query for pusing the current location info to the server for which we used a
			* hashmap, with beacon-id as key and beacon's x,y location as value.
			*
			* The x,y location was added to the query which is then sent to the server.
			*/
            //String BeaconID= getBeaconID();        //change this line to get the beacon ID appropraitely
            // String x_and_y=BLE_Location_Map.get(BeaconID);
            // x_and_y="200,250";
            // manager.addEventToQueue("coordinates",new TimeEventPair(System.currentTimeMillis(),parser.parse(x_and_y)));
            // mBLEAdapter.startLeScan(mLeScanCallback);


            //

            // Check for the CP configuration time, Should we refetch the TEDS if the time difference is more then 5 hours or so?

            // Check with the TIM configuration here
            // Discard if needed


            // Else call the  CPDP Switch


            // Check if the UUID is available in the LIST

            //    CPDPSwitch.execute();
        }
    };


    private BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,
                    0);
            temperature = temperature / 10;
            //	Log.e("Temperature", "" + temperature);
            voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);
        }
    };


    private void writetofile() {
        // TODO Auto-generated method stub
        // Log.e("BLEService", "FileWrite");

		/*	File iroot = Environment.getExternalStorageDirectory();
			File ifile = new File(iroot, "BLEDeviceList.txt"); // Change to Name

			ifile.delete();*/

        File root = Environment.getExternalStorageDirectory();
        File file = new File(root, "Log.txt"); // Change to Name

        // file.delete();

        try {
            bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(BLE_Data);
            bw.newLine();
            bw.flush();
            bw.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    public static void ByteArrayToString(byte[] ba)
    {
        StringBuilder hex = new StringBuilder(ba.length * 2);
        for ( int i=0; i<ba.length; i++){// byte b : ba){ int i=0; i<ba.length; i++){

            byte x;
            byte y;

                /*System.out.println("Byte x "+String.format("%02X", x));
                System.out.println("Byte y "+String.format("%02X", y));
                */
            /*    System.out.println("Hex X "+String.format("%02X", x<<8 ));
                System.out.println("Hex y "+String.format("%02X", y));



                String _x = String.format("%02X", x<<8 );
                String _y = String.format("%02X", y);

*/
       /*     System.out.println("Int X" + Integer.parseInt(String.format("%02X", x << 8), 16));
            System.out.println("Int Y" +Integer.parseInt(String.format("%02X", y), 16));
*/
         /*   float voltage = (float) ((Integer.parseInt(String.format("%02X", x<<8 ), 16) + Integer.parseInt(String.format("%02X", y), 16)) * 0.0017874165872259);

            System.out.println("Int X+Y " + voltage);

            System.out.println("Byte "+String.format("%02X ", b));
*/


            if(ba[i] == 124){
                x = ba[i+1];
                y = ba[i+2];
                volt = (float) ((Integer.parseInt(String.format("%02X", x<<8 ), 16) + Integer.parseInt(String.format("%02X", y), 16)) * 0.0017874165872259);
                System.out.println("Int X+Y " + volt);
                i+=6;
            }

            // hex.append(b + " ");
        }

        // return hex.toString();
    }
}