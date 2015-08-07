package rbccps.iot.ncap.SNaaS.CP.ConfigManagement;

import java.util.ArrayList;

import rbccps.iot.ncap.DTO.DataTransportObject;

/**
 * Created by vasanth on 11/3/15.
 */
public class ConfigManager {

    public void enableConfigList(){
        DataTransportObject.continuousList = new ArrayList<String>();
        DataTransportObject.freeRunningWithTriggerList = new ArrayList<String>();
    }

    public void addContinuous(String name){
        DataTransportObject.continuousList.add(name);
    }

    public void removeContinuous(String Name){
        DataTransportObject.continuousList.remove(Name);
    }

    public void addfreeRunningWithTrigger(String name){
        DataTransportObject.freeRunningWithTriggerList.add(name);
    }

    public void removefreeRunningWithTrigger(String name){
        DataTransportObject.freeRunningWithTriggerList.remove(name);
    }
}
