package rbccps.iot.ncap.BLDM.Subscriber;

import android.util.Log;

import de.greenrobot.event.EventBus;
import rbccps.iot.ncap.BLDM.CoAP.BasicCoapClient;
import rbccps.iot.ncap.DA.Events.DAJsonData;
import rbccps.iot.ncap.SNaaS.DP.EventBus.Events.SNaaSBeaconData;
import rbccps.iot.ncap.SNaaS.DP.EventBus.Events.SNaaSTimeStampData;

/**
 * Created by vasanth on 10/3/15.
 */
public class DASubscriber {

    public static BasicCoapClient coapclient;

    @SuppressWarnings("deprecation")
    public void register() {
        // TODO Auto-generated method stub


        EventBus.getDefault().registerSticky(this, DAJsonData.class);
        Log.i("DASubscriber", "Registered");
    }

    public void onEvent(DAJsonData DAJsonData){
        Log.e("DASubscriber","onEvent");
        coapclient.sendMessage(DAJsonData.getData());
        }

    public void onEvent(SNaaSTimeStampData SNaaSTimeStampData){}

    public void onEvent(SNaaSBeaconData data){}
}

