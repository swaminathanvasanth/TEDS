/*
 *
 * $RCSfile: BeaconWindow.java $
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

public class BeaconWindow {
	
	public static void enter_into_window(int beacon_num,
			long timeStamp) {
      
		BeaconFilter.window[BeaconFilter.window_end]=beacon_num;
		BeaconFilter.window_time[BeaconFilter.window_end]=timeStamp;
		 
			 for(;BeaconFilter.window_start!=BeaconFilter.window_end;BeaconFilter.window_start++)
		 {
			 // Log.i("Window Start", ""+window_start);
				 BeaconFilter.window_start=BeaconFilter.window_start%BeaconFilter.window_length;
                if((timeStamp-BeaconFilter.window_time[BeaconFilter.window_start])<BeaconFilter.window_time_length)
                {
              //  	Log.i("Timer Valid", ""+(timeStamp-window_time[window_start]));
                	break;
                }
		 }
			 BeaconFilter.window_end=(BeaconFilter.window_end+1)%BeaconFilter.window_length;
		// Log.i("Window End", ""+window_end);
	 }
}
