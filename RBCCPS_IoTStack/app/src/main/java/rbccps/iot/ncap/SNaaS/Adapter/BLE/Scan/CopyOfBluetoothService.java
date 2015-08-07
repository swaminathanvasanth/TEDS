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
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.net.UnknownHostException;
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

public class CopyOfBluetoothService extends Service {

    private BluetoothAdapter mBLEAdapter;
    private BluetoothManager bmanager;
    private Handler scanHandler;
    private HashMap<String,String> BLE_Location_Map;
    public static CEPManager manager;
    public static StringInputParser parser;
    public static boolean start = false;
    public static boolean stop = false;
    public static float volt;

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

        ApplicationContext applicationContext = new ApplicationContext(CopyOfBluetoothService.this);
        //initialize hashmap here and add the coordinates data like "ID":"x,y" ----->	"BeaconID1":"22,35"
         BeaconHeatmapPosition.setHeatMapPosition();

        SNaaSSubscriber BLDMeventRegister = new SNaaSSubscriber();
        BLDMSubscriber DAeventRegister = new BLDMSubscriber();
        DASubscriber DASubscriber = new DASubscriber();
        BLDMeventRegister.register();
        DAeventRegister.register();
        DASubscriber.register();

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

            // Check for the CP configuration time, Should we refetch the TEDS if the time difference is more then 5 hours or so?

            // Check with the TIM configuration here
            // Discard if needed


            // Else call the  CPDP Switch


            // Check if the UUID is available in the LIST

             CPDPSwitch.execute();
        }
    };

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }
}