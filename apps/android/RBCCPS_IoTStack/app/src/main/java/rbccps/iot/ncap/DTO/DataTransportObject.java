package rbccps.iot.ncap.DTO;

/*
 *
 * $RCSfile: CPDPController.java $
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


import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class DataTransportObject {

    // Have a list of Configured Devices

    // Check if the device is configured

    // Call CP or DP


    // Have all variables static

    public static int rssi;
    public static String Address;
    public static String Name;
    public static boolean processed = true;
    public static String x_and_y;
    public static long time_stamp;
    public static boolean registered_EventDictionary = true;

    // ----------------------------------------------------------------------
    public static int window_time_length = 1500;
    public static int max_no_of_beacons = 40;
    public static float min_varianc_between_max_previousState = (float) 0.20;
    public static int min_beacon_samp_state_transfer = 2; // 2
    public static float beacon_loc_x = 500, beacon_loc_y = 700;
    public static int window_length = 100; // 100
    public static float beacon_rssi_threshold = -110;
    public static int mid_range = 0;
    public static float mid_loc_x = 0, mid_loc_y = 0;
    public static int stage = 0;
    // ------------------------------------------------------------------------
    public static int[] window = new int[window_length];
    public static long[] window_time = new long[window_length];
    public static int window_start = 0;
    public static int window_end = 0;
    public static long timeStamp_prev = 0;
    public static int state_previous = 0;
    // ----------------------------------------------------------------------
    public static boolean flag = false;
    // ----------------------------------------------------------------------
    public static int[] beacon = new int[max_no_of_beacons];
    public static int local_counter1 = 0;
    public static int max_beacon = 1;
    public static float min_variance = 1, previous_min_variance = 1;
    public static int min_variance_beacon = state_previous;
    public static int previous_min_variance_beacon = 1;
    public static boolean first_min_variance = false;
    public static boolean plot = false;
    public static boolean cleanHands = false;
    public static boolean entrance = false;
    public static boolean enable_textView = false;
    public static int beacon_num = 0;

    // ----------------------------------------------------------------------

    public static ArrayList<String> continuousList;
    public static ArrayList<String> freeRunningWithTriggerList;

    public static ConcurrentHashMap<String,Double> FreeRunningWithTriggerTimeInterval;
    public static ConcurrentHashMap<String,Double> FreeRunningWithTriggerStartTime;
    public static boolean processData = true;

    public static void initializeVariables(){

        continuousList = new ArrayList<String>();
        freeRunningWithTriggerList = new ArrayList<String>();
        FreeRunningWithTriggerTimeInterval = new ConcurrentHashMap<String,Double>();
        FreeRunningWithTriggerStartTime = new ConcurrentHashMap<String,Double>();

    }


}
