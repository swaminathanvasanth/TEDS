/*
 *
 * $RCSfile: TEDSSplitter.java $
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

package rbccps.iot.ncap.SNaaS.CP.TEDS.Decoder;

public class TEDSSplitter {

	TEDSDecoder decoder = new TEDSDecoder();

	public static String split(String binary) {
		// System.out.println(binary);
		boolean isString = false;
		boolean isValueString = false;
		String value = null;
		String _binary = binary;
		String decodedString = "";
		char decodedValue = ' ';
		String length = null;
		for (int i = 0; i < _binary.length() - 1;) {

			if (_binary.charAt(i + 1) == ',' && !isString) {
				decodedValue = TEDSDecoder.decode(_binary.charAt(i), i);
				System.out.println(decodedValue);
			}
		else if(binary.charAt(i) == ',' && !isString){
			//	System.out.println(" Decoded Value is : " +decodedValue);
				decodedValue = ' ';
				i++;
			}
			else if (binary.charAt(i) != ',' && !isString){
				isString = true;
				value = Character.toString(binary.charAt(i));
				if(i!=binary.length())
					i++;
			}
			else if (isString && binary.charAt(i) != ',' && i != binary.length()){
				value += Character.toString(binary.charAt(i));
				if(i!=binary.length())
					i++;
			} else if(isString && binary.charAt(i) == ','){
				if(value.contains("128")){
					System.out.println("Description : ");
				}
			
				else if(value.contains("10")){
					System.out.println("OholdOff : ");
				}
				else if(value.contains("12")){
					System.out.println("Self-Test Time : ");
				}
				else if(value.contains("13")){
					System.out.println("MaxChan : ");
				}
				decodedString = TEDSDecoder.decode(value, i);
		//		System.out.println(" Decoded Value is : " +decodedString);
				decodedString = " ";
				if(i != binary.length())
					i++; 
				isString = false;
			} else if(isString && i+1 == binary.length()){
				value += Character.toString(binary.charAt(i));
				decodedString = TEDSDecoder.decode(value, i);
				System.out.println(decodedString);
				System.out.println("HIT");
				/*if(i!=binary.length() && i < binary.length())
					i++; 
				isString = false;*/
			}
		}
		
		return decodedString;
	}
}
