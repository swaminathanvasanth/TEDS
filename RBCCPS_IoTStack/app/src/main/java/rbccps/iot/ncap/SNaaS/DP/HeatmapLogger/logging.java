/*
 *
 * $RCSfile: logging.java $
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

package rbccps.iot.ncap.SNaaS.DP.HeatmapLogger;

import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class logging {

	static int rbccps_3_1 = 0;
	static int rbccps_3_2 = 0;
	static int rbccps_3_3 = 0;
	static int rbccps_3_4 = 0;
	static int rbccps_3_5 = 0;
	static int rbccps_3_6 = 0;
	static int rbccps_3_7 = 0;
	static int rbccps_3_8 = 0;
	static int rbccps_3_9 = 0;
	static int rbccps_3_10 = 0;
	static int rbccps_3_11 = 0;
	static int rbccps_3_12 = 0;
	static int rbccps_3_13 = 0;
	static int rbccps_3_14 = 0;
	static int rbccps_3_15 = 0;
	static int rbccps_3_16 = 0;
	static int rbccps_3_17 = 0;
	static int rbccps_3_18 = 0;
	static int rbccps_3_19 = 0;
	static int rbccps_3_20 = 0;
	static int rbccps_3_21 = 0;
	static int rbccps_3_22 = 0;
	static int rbccps_3_23 = 0;
	static int rbccps_3_24 = 0;
	static int rbccps_3_25 = 0;
	static int rbccps_3_26 = 0;
	static int rbccps_3_27 = 0;
	static int rbccps_3_28 = 0;
	static int rbccps_3_29 = 0;
	static int rbccps_3_30 = 0;
	static int i = 0;
	static boolean isFirst = true;
	
	static int [] beaconCount = new int[30];
	static String [] beaconName = new String[30];
	
	static BufferedWriter bw = null;
	
	public static void log(String name) throws IOException{
		
		for(int j=0;j<30;j++){
			switch(j){
			
			case 0:
				beaconName[j]="RBCCPS_3_1";
				break;

			case 1:
				beaconName[j]="RBCCPS_3_2";
				break;
				
			case 2:
				beaconName[j]="RBCCPS_3_3";
				break;

			case 3:
				beaconName[j]="RBCCPS_3_4";
				break;

			case 4:
				beaconName[j]="RBCCPS_3_5";
				break;

			case 5:
				beaconName[j]="RBCCPS_3_6";
				break;

			case 6:
				beaconName[j]="RBCCPS_3_7";
				break;

			case 7:
				beaconName[j]="RBCCPS_3_8";
				break;

			case 8:
				beaconName[j]="RBCCPS_3_9";
				break;

			case 9:
				beaconName[j]="RBCCPS_3_10";
				break;

			case 10:
				beaconName[j]="RBCCPS_3_11";
				break;

			case 11:
				beaconName[j]="RBCCPS_3_12";
				break;

			case 12:
				beaconName[j]="RBCCPS_3_13";
				break;

			case 13:
				beaconName[j]="RBCCPS_3_14";
				break;

			case 14:
				beaconName[j]="RBCCPS_3_15";
				break;

			case 15:
				beaconName[j]="RBCCPS_3_16";
				break;

			case 16:
				beaconName[j]="RBCCPS_3_17";
				break;

			case 17:
				beaconName[j]="RBCCPS_3_18";
				break;

			case 18:
				beaconName[j]="RBCCPS_3_19";
				break;

			case 19:
				beaconName[j]="RBCCPS_3_20";
				break;

			case 20:
				beaconName[j]="RBCCPS_3_21";
				break;

			case 21:
				beaconName[j]="RBCCPS_3_22";
				break;

			case 22:
				beaconName[j]="RBCCPS_3_23";
				break;

			case 23:
				beaconName[j]="RBCCPS_3_24";
				break;

			case 24:
				beaconName[j]="RBCCPS_3_25";
				break;

			case 25:
				beaconName[j]="RBCCPS_3_26";
				break;

			case 26:
				beaconName[j]="RBCCPS_3_27";
				break;

			case 27:
				beaconName[j]="RBCCPS_3_28";
				break;

			case 28:
				beaconName[j]="RBCCPS_3_29";
				break;

			case 29:
				beaconName[j]="RBCCPS_3_30";
				break;
			}
		}
		
		switch(name){
		
		case "RBCCPS_3_1":
			rbccps_3_1+=1;
			i=0;
			beaconCount[i]=rbccps_3_1;
			break;
		case "RBCCPS_3_2":
			rbccps_3_2+=1;
			i=1;
			beaconCount[i]=rbccps_3_2;
			break;
		case "RBCCPS_3_3":
			rbccps_3_3+=1;
			i=2;
			beaconCount[i]=rbccps_3_3;
			break;
		case "RBCCPS_3_4":
			rbccps_3_4+=1;
			i=3;
			beaconCount[i]=rbccps_3_4;
			break;
		case "RBCCPS_3_5":
			rbccps_3_5+=1;
			i=4;
			beaconCount[i]=rbccps_3_5;
			break;
		case "RBCCPS_3_6":
			rbccps_3_6+=1;
			i=5;
			beaconCount[i]=rbccps_3_6;
			break;
		case "RBCCPS_3_7":
			rbccps_3_7+=1;
			i=6;
			beaconCount[i]=rbccps_3_7;
			break;
		case "RBCCPS_3_8":
			rbccps_3_8+=1;
			i=7;
			beaconCount[i]=rbccps_3_8;
			break;
		case "RBCCPS_3_9":
			rbccps_3_9+=1;
			i=8;
			beaconCount[i]=rbccps_3_9;
			break;
		case "RBCCPS_3_10":
			rbccps_3_10+=1;
			i=9;
			beaconCount[i]=rbccps_3_10;
			break;
		case "RBCCPS_3_11":
			rbccps_3_11+=1;
			i=10;
			beaconCount[i]=rbccps_3_11;
			break;
		case "RBCCPS_3_12":
			rbccps_3_12+=1;
			i=11;
			beaconCount[i]=rbccps_3_12;
			break;
		case "RBCCPS_3_13":
			rbccps_3_13+=1;
			i=12;
			beaconCount[i]=rbccps_3_13;
			break;
		case "RBCCPS_3_14":
			rbccps_3_14+=1;
			i=13;
			beaconCount[i]=rbccps_3_14;
			break;
		case "RBCCPS_3_15":
			rbccps_3_15+=1;
			i=14;
			beaconCount[i]=rbccps_3_15;
			break;
		case "RBCCPS_3_16":
			rbccps_3_16+=1;
			i=15;
			beaconCount[i]=rbccps_3_16;
			break;
		case "RBCCPS_3_17":
			rbccps_3_17+=1;
			i=16;
			beaconCount[i]=rbccps_3_17;
			break;
		case "RBCCPS_3_18":
			rbccps_3_18+=1;
			i=17;
			beaconCount[i]=rbccps_3_18;
			break;
		case "RBCCPS_3_19":
			rbccps_3_19+=1;
			i=18;
			beaconCount[i]=rbccps_3_19;
			break;
		case "RBCCPS_3_20":
			rbccps_3_20+=1;
			i=19;
			beaconCount[i]=rbccps_3_20;
			break;
		case "RBCCPS_3_21":
			rbccps_3_21+=1;
			i=20;
			beaconCount[i]=rbccps_3_21;
			break;
		case "RBCCPS_3_22":
			rbccps_3_22+=1;
			i=21;
			beaconCount[i]=rbccps_3_22;
			break;
		case "RBCCPS_3_23":
			rbccps_3_23+=1;
			i=22;
			beaconCount[i]=rbccps_3_23;
			break;
		case "RBCCPS_3_24":
			rbccps_3_24+=1;
			i=23;
			beaconCount[i]=rbccps_3_24;
			break;
		case "RBCCPS_3_25":
			rbccps_3_25+=1;
			i=24;
			beaconCount[i]=rbccps_3_25;
			break;
		case "RBCCPS_3_26":
			rbccps_3_26+=1;
			i=25;
			beaconCount[i]=rbccps_3_26;
			break;
		case "RBCCPS_3_27":
			rbccps_3_27+=1;
			i=26;
			beaconCount[i]=rbccps_3_27;
			break;
		case "RBCCPS_3_28":
			rbccps_3_28+=1;
			i=27;
			beaconCount[i]=rbccps_3_28;
			break;
		case "RBCCPS_3_29":
			rbccps_3_29+=1;
			i=28;
			beaconCount[i]=rbccps_3_29;
			break;
		case "RBCCPS_3_30":
			rbccps_3_30+=1;
			i=29;
			beaconCount[i]=rbccps_3_30;
			break;
		}
		
		writetofile();
		
	}

	private static void writetofile() throws IOException {
		// TODO Auto-generated method stub
		File root = Environment.getExternalStorageDirectory();
		File file = new File(root, "HeatmapPlotLog.txt"); // Change to Name
	    file.delete();
		for(int j=0;j<30;j++){
			
		/*	if(isFirst){
				bw.newLine();
				isFirst=false;
			}
		*/	
			try {
				
			bw = new BufferedWriter(new FileWriter(file, true));
			bw.write(beaconName[j] +" ; "+beaconCount[j]);
			bw.newLine();
			bw.flush();
			bw.close();} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
  }
}