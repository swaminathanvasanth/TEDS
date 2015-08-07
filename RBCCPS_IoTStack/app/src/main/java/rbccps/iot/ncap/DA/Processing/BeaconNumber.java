/*
 *
 * $RCSfile: BeaconNumber.java $
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

public class BeaconNumber {

	public static int get_beacon_num(String beaconID) {
		String beacon_id1="RBCCPS_";
		int beacon_num=0,beacon_floor=0;
		int local_count1=0,is_real_beacon=1;
		int l1,l2;
		l1=beacon_id1.length();
		l2=beaconID.length();
		if(l2< l1) return 0;
			while(local_count1<l1)
			{
				if((beacon_id1.charAt(local_count1) == beaconID.charAt(local_count1)))
				    local_count1++;
				else 
				{
					is_real_beacon=0;
					break;
				}
				
			}
			
			if(is_real_beacon==1)
			{
				while( local_count1<l2)
				{
					if((beaconID.charAt(local_count1)>='0')&& (beaconID.charAt(local_count1)<='9'))
					{
						beacon_floor = beacon_floor*10 + beaconID.charAt(local_count1)-'0';
						local_count1++;
					}
					else if(beaconID.charAt(local_count1)>='_')
					{
						is_real_beacon=1;
						local_count1++;
						break;
					}
					else 
					{
						is_real_beacon=0;
						break;
					}
				}
				
			}
			if(is_real_beacon==1)
			{
				while(local_count1<l2)
				{
					if((beaconID.charAt(local_count1)>='0')&& (beaconID.charAt(local_count1)<='9'))
					{
						beacon_num = beacon_num*10 + beaconID.charAt(local_count1)-'0';
						local_count1++;
					}
				   else 
					{
						is_real_beacon=0;
						break;
					}
				}
				
			}
			
			if(is_real_beacon==0)
			{
				
				return 0;
			}
			
			
			return beacon_num;
		}
	
}
