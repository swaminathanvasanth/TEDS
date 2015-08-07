/*
 *
 * $RCSfile: RSSIThreshold.java $
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

public class RSSIThreshold {

	public static int one = -86;
	public static int two = -86;
	public static int three = -90;
	public static int four = -86;
	public static int five = -86;
	public static int six = -86;

	public static int seven = -86;
	public static int eight = -90;
	public static int nine = -86;
	public static int ten = -86;
	public static int eleven = -86;
	public static int twelve = -86;
	

	public static int thirteen = -90;
	public static int fourteen = -86;
	public static int fifteen = -86;
	public static int sixteen = -86;
	public static int seventeen = -86;
	public static int eighteen = -90;
	public static int nineteen = -86;
	public static int twenty = -90;
	
	public static float get_rssi_threshold(int beacon_num) {

		switch(beacon_num)
		{
		    case 1:
		         return one;
	 		
	 	    case 2:
			     return two;
			  
	 	    case 3:
			     return three;
			
		    case 4:
			     return four;
		  
		    case 5:
			     return five;
			
		    case 6:
			     return six;
			  
		    case 7:
			     return seven;
			
		    case 8:
		         return eight;
		  
		    case 9:
		         return nine;
		    
		    case 10:
		         return ten;
		    
		    case 11:
		         return eleven;
		         
		    case 12:
		         return twelve;
		    
		    case 13:
		         return thirteen;
		         
		    case 14:
		         return fourteen;
		  
		    case 15:
		         return fifteen;
		    
		    case 16:
		         return sixteen;
		    
		    case 17:
		         return seventeen;
		         
		    case 18:
		         return eighteen;
		    
		    case 19:
		         return nineteen;
		    case 20:
		         return twenty;
		  
		    case 21:
		         return -82;
		    
		    case 22:
		         return -82;
		    
		    case 23:
		         return -82;
		         
		    case 24:
		         return -82;
		    
		    case 25:
		         return -82;
		         
		    case 26:
		         return -82;
		}	  
		
		return 0;
	}
	
}
