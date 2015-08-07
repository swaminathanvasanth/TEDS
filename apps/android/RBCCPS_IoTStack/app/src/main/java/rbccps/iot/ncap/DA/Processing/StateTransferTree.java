/*
 *
 * $RCSfile: StateTransferTree.java $
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

public class StateTransferTree {

	public static int getState(int state_previous,
			int beacon_num) {

     switch (state_previous)
     {
    case 1: 
 	    if(beacon_num == 2||beacon_num == 3)
 	       return beacon_num;
 	       break;
 	case 2: 
	    if(beacon_num == 1 || beacon_num == 3 || beacon_num == 4 || beacon_num == 6)
	       return beacon_num;
	       break;
    case 3: 
        if(beacon_num == 2 || beacon_num == 10|| beacon_num == 11)
           return beacon_num;
           break;
    case 4: 
        if(beacon_num == 5 || beacon_num == 6 || beacon_num == 2)
          return beacon_num;
          break;
    case 5: 
        if(beacon_num == 4)
          return beacon_num;
         break;
    case 6: 
        if(beacon_num == 4 || beacon_num == 7 || beacon_num == 8 || beacon_num == 2 || beacon_num == 3)
          return beacon_num;  
        break;
    case 7: 
         if(beacon_num == 6 || beacon_num == 8 || beacon_num == 9)
           return beacon_num;  
         break;
    case 8: 
          if(beacon_num == 9 || beacon_num == 7 || beacon_num == 6 )
    	     return beacon_num;
          break;
    case 9: 
          if(beacon_num == 8 || beacon_num == 6 || beacon_num == 7)
        	 return beacon_num; 
          break;
    case 10: 
           if(beacon_num == 3 || beacon_num == 11)
    	    return beacon_num;  
           break;
	case 11: 
    	  if(beacon_num == 3|| beacon_num == 13|| beacon_num == 18)
    	  return beacon_num; 
    	  break;
	case 12:  
   		if(beacon_num == 11 || beacon_num == 13)
   		  return beacon_num;
		 break;
	case 13: 
		 if(beacon_num == 18 || beacon_num == 14 || beacon_num == 20  || beacon_num == 17 || beacon_num == 11|| beacon_num == 16)
	      return beacon_num;
		 break;
	case 14: 
		 if(beacon_num == 13 || beacon_num == 15|| beacon_num == 16 || beacon_num == 19|| beacon_num == 20 || beacon_num == 17)
	      return beacon_num; 
		 break;
	case 15: 
		if(beacon_num == 14 || beacon_num == 16|| beacon_num == 19)
		return beacon_num;
		 break;
	case 16: 
		if(beacon_num == 14 || beacon_num == 15 || beacon_num == 19)
		return beacon_num;
		 break;
	case 17: 
		if(beacon_num == 13 || beacon_num == 18 || beacon_num == 20)
		return beacon_num;
		 break;
	case 18: 
		if(beacon_num == 13|| beacon_num == 17 || beacon_num == 20)
		return beacon_num;
		 break;
	case 19: 
		if(beacon_num == 15|| beacon_num == 16)
		return beacon_num;
		 break;
	case 20: 
		if(beacon_num == 17|| beacon_num == 18|| beacon_num == 13)
		return beacon_num;
		 break;
	   }
	   return state_previous;
	}
}
