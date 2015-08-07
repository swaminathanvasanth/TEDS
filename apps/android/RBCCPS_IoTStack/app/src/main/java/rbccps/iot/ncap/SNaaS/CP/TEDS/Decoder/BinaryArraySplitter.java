/*
 *
 * $RCSfile: BinaryArraySplitter.java $
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

public class BinaryArraySplitter {
	public void split(int[] array) {
		int[] binaryArray = array;
		int length;
		int looplength;
		int j;
		boolean isDatatype = false;
		boolean isValue = false;
		boolean isUUID = false; 
		boolean isTedsid = false;
		boolean isOholdOff = false;
		boolean ismaxChannel = false;

        for (int i = 0; i < binaryArray.length; i++) {

			if (binaryArray[i] == 4 && !isUUID) {
				isUUID = true;
				System.out.print("Type : " + binaryArray[i] + " - ");
				i++;
				System.out.print("UUID" + " - ");
				System.out.print("Length : " + binaryArray[i]+ " - ");
				length = binaryArray[i];
				i++;
				looplength = i + length;
				isValue = false;
				for (j = i; j < looplength; j++) {
					if (!isValue) {
						System.out.print("Value : ");
						isValue = true;
					}
				/*	System.out.print(" Binary is : " + binaryArray[j] + " - ");
					System.out.println(" Character is : "
							+ (char) binaryArray[j]);*/
					
					System.out.print((char) binaryArray[j]);
					isDatatype = true;
				}
				System.out.println();
				if (isDatatype) {
					i = j;
					isDatatype = false;
				}
			}

			if (binaryArray[i] == 3 && !isTedsid) {
				isTedsid = true;
				System.out.print("Type : " + binaryArray[i] + " - ");
				i++;
				System.out.print("TEDS ID" + " - ");
				
				System.out.println("Length : " + binaryArray[i]);
				length = binaryArray[i];
				i++;
				looplength = i + length;
				isValue = false;
				for (j = i; j < looplength; j++) {
					if (!isValue) {
						System.out.println("Value : ");
						isValue = true;
					}
					System.out.print(" Binary is : " + binaryArray[j] + " - ");
					System.out.println(" Character is : "
							+ (char) binaryArray[j]);
					isDatatype = true;
				}
				if (isDatatype) {
					i = j;
					isDatatype = false;
				}
			}

			if (binaryArray[i] == 10 && !isOholdOff) {
				isOholdOff = true;
				System.out.print("Type : " + binaryArray[i] + " - ");
				i++;
				System.out.print("OholdOFF" + " - ");
				
				System.out.println("Length : " + binaryArray[i]);
				length = binaryArray[i];
				i++;
				looplength = i + length;
				isValue = false;
				for (j = i; j < looplength; j++) {
					if (!isValue) {
						System.out.println("Value : ");
						isValue = true;
					}
					System.out.print(" Binary is : " + binaryArray[j] + " - ");
					System.out.println(" Character is : "
							+ (char) binaryArray[j]);
					isDatatype = true;
				}
				if (isDatatype) {
					i = j;
					isDatatype = false;
				}
			}

			if (binaryArray[i] == 13 && !ismaxChannel) {
				ismaxChannel = true;
				System.out.print("Type : " + binaryArray[i] + " - ");
				i++;
				System.out.print("Max Channel" + " - ");
				
				System.out.println("Length : " + binaryArray[i]);
				length = binaryArray[i];
				i++;
				looplength = i + length;
				isValue = false;
				for (j = i; j < looplength; j++) {
					if (!isValue) {
						System.out.println("Value : ");
						isValue = true;
					}
					System.out.print(" Binary is : " + binaryArray[j] + " - ");
					System.out.println(" Character is : "
							+ (char) binaryArray[j]);
					isDatatype = true;
				}
				if (isDatatype) {
					i = j;
					isDatatype = false;
				}
			}

			
			
		}
	}
}

// binaryArray[i] == 3 || binaryArray[i] == 4 || binaryArray[i] == 10 ||
// binaryArray[i] == 12 || binaryArray[i] == 13 || 