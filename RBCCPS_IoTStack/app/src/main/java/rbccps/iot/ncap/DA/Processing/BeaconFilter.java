/*
 *
 * $RCSfile: BeaconFilter.java $
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

package rbccps.iot.ncap.DA.Processing;

import rbccps.iot.ncap.DA.CEP.Core.TimeEventPair;
import rbccps.iot.ncap.DTO.DataTransportObject;
import rbccps.iot.ncap.Initialize.BeaconHeatmapPosition;
import rbccps.iot.ncap.SNaaS.Adapter.BLE.Scan.BluetoothService;
import rbccps.iot.ncap.SNaaS.CPDPSwitch.CPDPSwitch;
import rbccps.iot.ncap.SNaaS.CP.TEDS.LDAP.callLDAPAsyncTask;

public class BeaconFilter {

	// ----------------------------------------------------------------------
	public static int window_time_length = 1500;
	public static int max_no_of_beacons = 40;
	public static float min_varianc_between_max_previousState = (float) 0.20;
	public static int min_beacon_samp_state_transfer = 2; // 2
	public static int window_length = 100; // 100
	public static int mid_range = 0;
	public static int stage = 0;
	// ------------------------------------------------------------------------
	public static int[] window = new int[window_length];
	public static long[] window_time = new long[window_length];
	public static int window_start = 0;
	public static int window_end = 0;
	public static int state_previous = 0;
	// ----------------------------------------------------------------------
	// ----------------------------------------------------------------------
	public static int[] beacon = new int[max_no_of_beacons];
	public static int local_counter1 = 0;
	public static int max_beacon = 1;
	public static float min_variance = 1, previous_min_variance = 1;
	public static int min_variance_beacon = state_previous;
	public static int previous_min_variance_beacon = 1;
	public static int beacon_num = 0;
    public static boolean test = false;

    // ----------------------------------------------------------------------

	public static void filter() {
		// TODO Auto-generated method stub
		if (true) {
            DataTransportObject.timeStamp_prev = DataTransportObject.time_stamp;

            DataTransportObject.beacon_num = BeaconNumber.get_beacon_num(DataTransportObject.Name);
            DataTransportObject.x_and_y = BeaconHeatmapPosition.getHeatmapPosition(DataTransportObject.Name);
            System.out.println(""+ DataTransportObject.beacon_num);
            System.out.println(DataTransportObject.x_and_y);

          	if (DataTransportObject.timeStamp_prev == 0)
                DataTransportObject.timeStamp_prev = DataTransportObject.time_stamp;

			if (DataTransportObject.rssi >= RSSIThreshold.get_rssi_threshold(DataTransportObject.beacon_num)) {
				BeaconWindow.enter_into_window(DataTransportObject.beacon_num, DataTransportObject.time_stamp);
                DataTransportObject.beacon_num = StateTransferDistribution
						.find_distrbution_stateTransfer(DataTransportObject.beacon_num);

				BeaconPositionArray.setBeaconPosition(DataTransportObject.beacon_num, DataTransportObject.time_stamp);
				int beacon_num_Count = BeaconPositionArray
						.getBeaconPosition(DataTransportObject.beacon_num);

				if (DataTransportObject.mid_range == 1) {
                    DataTransportObject.mid_range = 0;
                    DataTransportObject.mid_loc_x = BeaconPosition.get_beacon_posX(DataTransportObject.beacon_num);
                    DataTransportObject.mid_loc_y = DataTransportObject.beacon_loc_y;
                    DataTransportObject.beacon_loc_x = BeaconPosition.get_beacon_posX(DataTransportObject.beacon_num);
                    DataTransportObject.beacon_loc_y = BeaconPosition.get_beacon_posY(DataTransportObject.beacon_num);
				} 
				
				if(DataTransportObject.state_previous == 13 && DataTransportObject.beacon_num == 16){
                    DataTransportObject.beacon_loc_x = BeaconPosition.get_beacon_posX(DataTransportObject.state_previous);
                    DataTransportObject.beacon_loc_y = BeaconPosition.get_beacon_posY(DataTransportObject.state_previous);
				}
				
				if(DataTransportObject.state_previous == 14 && DataTransportObject.beacon_num == 17){
                    DataTransportObject.beacon_loc_x = BeaconPosition.get_beacon_posX(DataTransportObject.state_previous);
                    DataTransportObject.beacon_loc_y = BeaconPosition.get_beacon_posY(DataTransportObject.state_previous);
				}
				
				else {
                    DataTransportObject.beacon_loc_x = BeaconPosition.get_beacon_posX(DataTransportObject.beacon_num);
                    DataTransportObject.beacon_loc_y = BeaconPosition.get_beacon_posY(DataTransportObject.beacon_num);
				}
                DataTransportObject.state_previous = DataTransportObject.beacon_num;
                DataTransportObject.min_variance = 1;
                DataTransportObject.min_variance_beacon = 1;
                DataTransportObject.previous_min_variance = 1;
                DataTransportObject.previous_min_variance_beacon = 1;
                DataTransportObject.plot = true;

/*
                System.out.println("System.currentTimeMillis(),BluetoothService.x_and_y" + System.currentTimeMillis() + BluetoothService.x_and_y);
                BluetoothService.manager.addEventToQueue("coordinates",new TimeEventPair(System.currentTimeMillis(),BluetoothService.parser.parse(BluetoothService.x_and_y)));
*/

            }

            /*BluetoothLEService.currentTime = Calendar.getInstance().getTimeInMillis();
            Log.e("Http ", ""+BluetoothLEService.currentTime);
            if(BluetoothLEService.currentTime  - BluetoothLEService.startTime >= (1 * 1000 * 10)){ // 8 hours = 1 * 1000 * 60 * 60 * 8
                Log.e("Http ", ""+(BluetoothLEService.currentTime- BluetoothLEService.startTime));
                Reporter.reportViolation();} */
		}

        if(DataTransportObject.Name.contains("RBCCPS_3_")){
        System.out.println("System.currentTimeMillis(),BluetoothService.x_and_y" + System.currentTimeMillis() + " : "+ DataTransportObject.x_and_y);
        BluetoothService.manager.addEventToQueue("coordinates",new TimeEventPair(System.currentTimeMillis(),BluetoothService.parser.parse(DataTransportObject.x_and_y)));

            if(!CPDPSwitch.TEDSList.contains(callLDAPAsyncTask.uuid)){ // !TEDSFetchHandler.TEDSList.contains(callLDAPAsyncTask.uuid)


               //System.out.print(!TEDSFetchHandler.TEDSList.contains(callLDAPAsyncTask.uuid));



              //  new callLDAPAsyncTask().execute();
            }


            // TEDSDataStore datastore = new TEDSDataStore(ApplicationContext.context);
        }
    }
}
