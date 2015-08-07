/*
 *
 * $RCSfile: StateTransferDistribution.java $
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

public class StateTransferDistribution {

	public static int find_distrbution_stateTransfer(int beacon_num) {
		
		
		BeaconFilter.mid_range=0;
		// min_variance_beacon=state_previous;
		
		for(BeaconFilter.local_counter1=0;BeaconFilter.local_counter1<BeaconFilter.max_no_of_beacons;BeaconFilter.local_counter1++)
		{
			BeaconFilter.beacon[BeaconFilter.local_counter1]=0;
			 
		} //allocating all beacons count to zero
		
		for(BeaconFilter.local_counter1=BeaconFilter.window_start;BeaconFilter.local_counter1!=BeaconFilter.window_end;BeaconFilter.local_counter1++)
		{
			BeaconFilter.local_counter1=BeaconFilter.local_counter1%BeaconFilter.window_length;
			if(BeaconFilter.local_counter1 == BeaconFilter.window_end)break;
    		BeaconFilter.beacon[BeaconFilter.window[BeaconFilter.local_counter1]]++;
			//  Log.i("Beacon Counts", ""+beacon[window[local_counter1]] + ":" +window[local_counter1]);
     	} //counting beacons count
		
		for(BeaconFilter.local_counter1=1;BeaconFilter.local_counter1<BeaconFilter.max_no_of_beacons;BeaconFilter.local_counter1++)
		{
			 if(BeaconFilter.beacon[BeaconFilter.max_beacon]<BeaconFilter.beacon[BeaconFilter.local_counter1])
				 BeaconFilter.max_beacon=BeaconFilter.local_counter1;
		}// finding maximum beaconed beacon
		// Log.i("Maximum beaconed beacon", ""+max_beacon);
		
		//finding second max beaconed beacon and its variance
		for(BeaconFilter.local_counter1=1;BeaconFilter.local_counter1<BeaconFilter.max_no_of_beacons;BeaconFilter.local_counter1++)
		{
			float maxBeaconValue = BeaconFilter.beacon[BeaconFilter.max_beacon]; float localValue = BeaconFilter.beacon[BeaconFilter.local_counter1];
			float variance = (maxBeaconValue-localValue)/maxBeaconValue;
		 //  Log.i("Inner For", "MB : "+max_beacon + " : "+maxBeaconValue + " CB : "+local_counter1 + " : "+localValue+ " V : "+variance +"CMV : "+min_variance);	
		   if(BeaconFilter.max_beacon != BeaconFilter.local_counter1)
			 if(variance < BeaconFilter.min_variance)
				 {
				 BeaconFilter.previous_min_variance = BeaconFilter.min_variance;
				 BeaconFilter.previous_min_variance_beacon =BeaconFilter.min_variance_beacon;
				 BeaconFilter.min_variance=variance;
				 BeaconFilter.min_variance_beacon = BeaconFilter.local_counter1;
				    //  Log.i("Inner For If Case", "MB : "+BeaconFilter.max_beacon + " : "+maxBeaconValue + " MVB : "+BeaconFilter.local_counter1 + " : "+localValue+ " V : "+variance + " PMV : " +BeaconFilter.previous_min_variance + " PMVB : "+BeaconFilter.previous_min_variance_beacon+ " MV : " +BeaconFilter.min_variance + " MVB : "+BeaconFilter.min_variance_beacon  );
				    // Log.i("min_variance & min_variance_beacon", ""+min_variance + ":"+min_variance_beacon);
				    // Log.i("previous_min_variance & previous_min_variance_beacon", ""+previous_min_variance + ":"+previous_min_variance_beacon);
				 } 
		}
		
		 // Log.i("After For : min_variance & min_variance_beacon", ""+min_variance + ":"+min_variance_beacon);
	     // Log.i("After For : previous_min_variance & previous_min_variance_beacon", ""+previous_min_variance + ":"+previous_min_variance_beacon);
		  	
		// checking whether maximum beaconed count is more than minimum threshold  
		if(BeaconFilter.beacon[BeaconFilter.max_beacon]>=BeaconFilter.min_beacon_samp_state_transfer)
		{
			if(BeaconFilter.state_previous==0)//if previous state is zero
				return BeaconFilter.max_beacon;
			else if(BeaconFilter.state_previous==BeaconFilter.max_beacon)// if previous state equal to present max beaconed state
				return BeaconFilter.max_beacon;
			
			//if variance is less than threshold
			if(BeaconFilter.min_variance <= BeaconFilter.min_varianc_between_max_previousState)
			{
				 //checking whether the second max beaconed beacon is previous state beacon
			   	 if(BeaconFilter.state_previous == BeaconFilter.min_variance_beacon)
				    {
						   //if previous state has a relation with the max beaconed  beacon
						   if(BeaconFilter.max_beacon == StateTransferTree.getState(BeaconFilter.state_previous,BeaconFilter.max_beacon))
							{
							   BeaconFilter.mid_range=1;
							   BeaconFilter.stage=1;
							      return BeaconFilter.max_beacon;
							}
						   
						   // if previous state has no relation with max beaconed beacon
						   else 
						   { BeaconFilter.stage=2;
							  return BeaconFilter.state_previous;
						   }	   
				     }		
			   //if second max beaconed beacon is not previous state beacon
			   	else{
			   		BeaconFilter.stage=3;
			   	      return(StateTransferTree.getState(BeaconFilter.state_previous,BeaconFilter.max_beacon));
        			 }
  			}
			//if variance is more than threshold
			else 
			{
				BeaconFilter.stage=4;
				return(StateTransferTree.getState(BeaconFilter.state_previous,BeaconFilter.max_beacon));
			}
		}
		// else	maximum count is less than minimum count threshold
		else	
			BeaconFilter.stage=5;
			return BeaconFilter.state_previous;
 	}
}
