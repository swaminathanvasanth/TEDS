/*
 *
 * $RCSfile: BeaconPosition.java $
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

public class BeaconPosition {
    public static int get_beacon_posX(int beacon_num) {
        int beacon_loc_x = 0;
        switch (beacon_num) {
            case 0:
                beacon_loc_x = 500;
                return beacon_loc_x;

            case 1:
                beacon_loc_x = 887;
                break;
            case 2:
                beacon_loc_x = 860; // 887
                break;
            case 3:
                beacon_loc_x = 766;
                break;
            case 4:
                beacon_loc_x = 766;
                break;
            case 5:
                beacon_loc_x = 380;
                break;
            case 6:
                beacon_loc_x = 887;
                break;
            case 7:
                beacon_loc_x = 766;
                break;
            case 8:
                beacon_loc_x = 878;
                break;
            case 9:
                beacon_loc_x = 966;
                break;
            case 10:
                beacon_loc_x = 680;
                break;
            case 11:
                beacon_loc_x = 680;
                break;

            case 12:
                beacon_loc_x = 680;
                break;

            case 13:
                beacon_loc_x = 430;
                break;

            case 14:
                beacon_loc_x = 130;
                break;

            case 15:
                beacon_loc_x = 130;
                break;

            case 16:
                beacon_loc_x = 130;
                break;

            case 17:
                beacon_loc_x = 430;
                break;

            case 18:
                beacon_loc_x = 430;
                break;

            case 19:
                beacon_loc_x = 130;
                break;

            case 20:
                beacon_loc_x = 430;
                break;

        }
        return beacon_loc_x;
    }

    public static int get_beacon_posY(int beacon_num) {
        int beacon_loc_y = 0;
        switch (beacon_num) {
            case 0:
                beacon_loc_y = 700;
                break;
            case 1:
                beacon_loc_y = 1380;
                break;
            case 2:
                beacon_loc_y = 570; // 783
                break;
            case 3:
                beacon_loc_y = 662;
                break;
            case 4:
                beacon_loc_y = 410;
                break;
            case 5:
                beacon_loc_y = 320;
                break;
            case 6:
                beacon_loc_y = 450;
                break;
            case 7:
                beacon_loc_y = 170;
                break;
            case 8:
                beacon_loc_y = 41;
                break;
            case 9:
                beacon_loc_y = 94;
                break;
            case 10:
                beacon_loc_y = 900;
                break;
            case 11:
                beacon_loc_y = 1350;
                break;
            case 12:
                beacon_loc_y = 1400;
                break;
            case 13:
                beacon_loc_y = 1400;
                break;
            case 14:
                beacon_loc_y = 1400;
                break;
            case 15:
                beacon_loc_y = 885;
                break;
            case 16:
                beacon_loc_y = 885;
                break;
            case 17:
                beacon_loc_y = 885;
                break;
            case 18:
                beacon_loc_y = 885;
                break;
            case 19:
                beacon_loc_y = 705;
                break;
            case 20:
                beacon_loc_y = 705;
                break;
        }
        return beacon_loc_y;
    }

}