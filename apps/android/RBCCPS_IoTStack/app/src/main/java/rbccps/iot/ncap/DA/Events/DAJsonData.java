package rbccps.iot.ncap.DA.Events;

import android.util.Log;

/**
 * Created by vasanth on 10/3/15.
 */
public class DAJsonData {

    public static String data;

    public DAJsonData(String jsonData){

        DAJsonData.data = jsonData;
        Log.e("DAJsonData : ", jsonData);
    }

    public static String getData() {
        return data;
    }

    public static void setData(String _data) {
        data= _data;
    }
}
