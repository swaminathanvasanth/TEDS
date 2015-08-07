package rbccps.iot.ncap.DA.CEP.Core;

import android.util.Log;

import org.json.JSONObject;

import java.net.UnknownHostException;
import java.util.concurrent.LinkedBlockingQueue;

import rbccps.iot.ncap.BLDM.CoAP.BasicCoapClient;
import rbccps.iot.ncap.BLDM.CoAP.SetCOAPAddress;
import rbccps.iot.ncap.DA.Events.DAJsonData;
import rbccps.iot.ncap.DA.Publisher.DAPublisher;

public class OutputHandler extends Thread{
	LinkedBlockingQueue<JSONObject> outputQueues;
	public BasicCoapClient coapclient;
	public BasicHttpClient httpclient;
	private int config=0;
	private static final String HTTP_PORT="8532";
	public static final String HTTP="HTTP";
	public static final String COAP="COAP";
    public static String _serverAddress;
	public OutputHandler(LinkedBlockingQueue<JSONObject> in_outputQueues,String serverAddress) throws UnknownHostException
	{
		outputQueues=in_outputQueues;
		this.setName("outputThread");
		if(serverAddress!=null){
			config=1;
			httpclient=new BasicHttpClient("http://"+serverAddress+":"+HTTP_PORT);}					                //sets the address of the server if its not null
		else if(serverAddress==null)
			config=2;
		this.start();
	}
	public OutputHandler(LinkedBlockingQueue<JSONObject> in_outputQueues,String serverAddress,String in_config) throws UnknownHostException
	{
        _serverAddress = serverAddress;

		outputQueues=in_outputQueues;
		this.setName("outputThread");
		if(in_config.contains("COAP"))	{
		    SetCOAPAddress.setServerAddress(serverAddress);
            //coapclient=new BasicCoapClient(serverAddress);
        }                //sets the address of the server if its not null
		else if(in_config.contains("HTTP"))
		{
			config=1;
			httpclient=new BasicHttpClient("http://"+serverAddress+":"+HTTP_PORT);
		}
		else if(serverAddress==null)
			config=2;
		this.start();
	}
	@Override
	public void run()
	{	
		JSONObject temp;
		if(config==1)
		{
			while(true)
			{		
					temp=outputQueues.poll();
					if(temp!=null){
						 
						if(temp.has("Termination")){System.out.println("Stopping this thread");break;}
						
						httpclient.sendMessage(temp.toString());
						Log.d("Message",temp.toString());
					}	
			}
		}
		else if(config==0){
			
			while(true)
			{		
					temp=outputQueues.poll();
					if(temp!=null){
						 
						if(temp.has("Termination")){System.out.println("Stopping this thread");break;}
						
						// coapclient.sendMessage(temp.toString());

                        DAJsonData.setData(temp.toString());
                        DAPublisher.publishEvents();


					}	
			}
		}
		
		else if(config==2){
			while(true)
			{		
					temp=outputQueues.poll();
					if(temp!=null){
						 
						if(temp.has("Termination")){break;}
						
						System.out.println(temp.toString());
					}	
			}
		}
	}
}
