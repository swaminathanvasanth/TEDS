package rbccps.iot.ncap.BLDM.CoAP;

import java.net.UnknownHostException;

import rbccps.iot.ncap.BLDM.Subscriber.DASubscriber;
import rbccps.iot.ncap.DA.CEP.Core.OutputHandler;

/**
 * Created by vasanth on 10/3/15.
 */
public class SetCOAPAddress {

    public static void setServerAddress(String ServerAddress){

        try {
            DASubscriber.coapclient=new BasicCoapClient(OutputHandler._serverAddress);                //sets the address of the server if its not null
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
