package rbccps.iot.ncap.SNaaS.CPDPSwitch;

import java.util.ArrayList;

import rbccps.iot.ncap.DTO.DataTransportObject;
import rbccps.iot.ncap.SNaaS.CP.TEDS.LDAP.callLDAPAsyncTask;
import rbccps.iot.ncap.SNaaS.DP.Publisher.SNaaSPublisher;

/**
 * Created by vasanth on 9/3/15.
 */
public class CPDPSwitch {

    public static ArrayList<String> TEDSList;


    public static void createList(){
        TEDSList = new ArrayList<String>();
    }

    public static void addtoList(String uuid){
        TEDSList.add(uuid);
    }


    public static void execute(){
        if((CPDPSwitch.TEDSList.contains(DataTransportObject.Name)) && DataTransportObject.processData){

            // call Data Plane
         /*   System.out.println("HIT");
            if(DataTransportObject.freeRunningWithTriggerList.contains(DataTransportObject.Name)){// DataTransportObject.FreeRunningWithTriggerTimeInterval.contains(DataTransportObject.Name)

                double currentTime = Calendar.getInstance().getTimeInMillis();
                System.out.println(currentTime);
                System.out.println(DataTransportObject.FreeRunningWithTriggerStartTime.get(DataTransportObject.Name));
                System.out.println(currentTime - DataTransportObject.FreeRunningWithTriggerStartTime.get(DataTransportObject.Name));
                if(currentTime - DataTransportObject.FreeRunningWithTriggerStartTime.get(DataTransportObject.Name) >= DataTransportObject.FreeRunningWithTriggerTimeInterval.get(DataTransportObject.Name)){
                    System.out.println(CPDPSwitch.TEDSList.toString());
                    DataTransportObject.FreeRunningWithTriggerStartTime.put(DataTransportObject.Name, currentTime);
                    SNaaSPublisher.publishEvents();

                }
}
*/

            System.out.println(CPDPSwitch.TEDSList.toString());
            SNaaSPublisher.publishEvents();

        } else if(DataTransportObject.processData){
            CPDPSwitch.addtoList(DataTransportObject.Name);

            // Call LDAP Server
            // Save in local Cache
            System.out.println("-------------------------------------- New TIM --------------------------------------");
            new callLDAPAsyncTask().execute();

            DataTransportObject.continuousList.add(DataTransportObject.Name);

            // Call Control Plane

            System.out.println(CPDPSwitch.TEDSList.toString());
            SNaaSPublisher.publishEvents();

        }
    }
}
