/*
 *
 * $RCSfile: BeaconHeatmapPosition.java $
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


package rbccps.iot.ncap.Initialize;


import java.util.HashMap;

public class BeaconHeatmapPosition {

  private static HashMap<String,String> BLE_Location_Map;

    public static void setHeatMapPosition(){

        BLE_Location_Map = new HashMap<String,String>();

        BLE_Location_Map.put("RBCCPS_3_1","458,834");
        BLE_Location_Map.put("RBCCPS_3_2","483,774");
        BLE_Location_Map.put("RBCCPS_3_3","357,597");
        BLE_Location_Map.put("RBCCPS_3_4","480,654");
        BLE_Location_Map.put("RBCCPS_3_5","380,514");
        BLE_Location_Map.put("RBCCPS_3_6","183,517");
        BLE_Location_Map.put("RBCCPS_3_7","474,380");
        BLE_Location_Map.put("RBCCPS_3_8","662,527");
        BLE_Location_Map.put("RBCCPS_3_9","369,211");
        BLE_Location_Map.put("RBCCPS_3_10","418,31");
        BLE_Location_Map.put("RBCCPS_3_11","603,110");
        BLE_Location_Map.put("RBCCPS_3_12","1011,27");
        BLE_Location_Map.put("RBCCPS_3_13","646,269");
        BLE_Location_Map.put("RBCCPS_3_14","682,146");
        BLE_Location_Map.put("RBCCPS_3_15","780,313");
        BLE_Location_Map.put("RBCCPS_3_16","1022,268");
        BLE_Location_Map.put("RBCCPS_3_17","929,315");
        BLE_Location_Map.put("RBCCPS_3_18","950,437");
        BLE_Location_Map.put("RBCCPS_3_19","954,549");
        BLE_Location_Map.put("RBCCPS_3_20","1012,604");
        BLE_Location_Map.put("RBCCPS_3_21","1049,751");
        BLE_Location_Map.put("RBCCPS_3_22","867,668");
        BLE_Location_Map.put("RBCCPS_3_23","1135,820");
        BLE_Location_Map.put("RBCCPS_3_24","1169,863");
        BLE_Location_Map.put("RBCCPS_3_25","1302,868");
        BLE_Location_Map.put("RBCCPS_3_26","1423,751");
        BLE_Location_Map.put("RBCCPS_3_27","1378,715");
        BLE_Location_Map.put("RBCCPS_3_28","1466,542");
        BLE_Location_Map.put("RBCCPS_3_29","1473,400");
        BLE_Location_Map.put("RBCCPS_3_30","1354,414");
        BLE_Location_Map.put("RBCCPS_3_31","1172,564");
        BLE_Location_Map.put("RBCCPS_3_32","1393,326");
        BLE_Location_Map.put("RBCCPS_3_33","1475,180");
        BLE_Location_Map.put("RBCCPS_3_34","1234,322");
        BLE_Location_Map.put("RBCCPS_3_35","1240,261");
        BLE_Location_Map.put("RBCCPS_3_36","1236,175");
        BLE_Location_Map.put("RBCCPS_3_37","1165,109");
        BLE_Location_Map.put("RBCCPS_3_38","1058,104");
        BLE_Location_Map.put("RBCCPS_3_39","1243,52");
        BLE_Location_Map.put("RBCCPS_3_40","1288,48");
        BLE_Location_Map.put("RBCCPS_3_41","1355,54");
        BLE_Location_Map.put("RBCCPS_3_42","1350,138");
        BLE_Location_Map.put("RBCCPS_3_43","1353,190");
        BLE_Location_Map.put("RBCCPS_3_44","1336,308");
        BLE_Location_Map.put("RBCCPS_3_45","1075,309");
        BLE_Location_Map.put("RBCCPS_3_46","985,307");
        BLE_Location_Map.put("RBCCPS_3_47","1001,563");
        BLE_Location_Map.put("RBCCPS_3_48","1124,736");
        BLE_Location_Map.put("RBCCPS_3_49","1286,728");
        BLE_Location_Map.put("RBCCPS_3_50","1261,409");


    }

    public static String getHeatmapPosition(String beaconName){


        String x_and_y = BLE_Location_Map.get(beaconName);

        return x_and_y;
    }



}
