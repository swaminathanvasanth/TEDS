/*
 *
 * $RCSfile: EventBusFileWriter.java $
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


package rbccps.iot.ncap.SNaaS.DP.FileWriter;

import android.util.Log;

import java.net.UnknownHostException;
import java.util.HashMap;

import rbccps.iot.ncap.DA.CEP.Core.CEPManager;
import rbccps.iot.ncap.DA.CEP.Core.InputHandler;
import rbccps.iot.ncap.DA.CEP.Core.OutputHandler;
import rbccps.iot.ncap.DA.CEP.Core.StreamProcessor;
import rbccps.iot.ncap.DA.CEP.Core.TimeEventPair;
import rbccps.iot.ncap.DA.CEP.Core.differentTypesException;
import rbccps.iot.ncap.DA.CEP.Core.lessArgumentsException;
import rbccps.iot.ncap.DA.CEP.InputParser.StringInputParser;
import rbccps.iot.ncap.SNaaS.DP.EventBus.Events.SNaaSBeaconData;
import de.greenrobot.event.EventBus;

public class EventBusFileWriter {

	CEPManager manager = new CEPManager();
	HashMap<String, String> beaconDataMap = new HashMap<String, String>();

	public void register() {
		// TODO Auto-generated method stub

		EventBus.getDefault().registerSticky(this, SNaaSBeaconData.class);
		Log.e("EventBusRegister : ", SNaaSBeaconData.getData());

		// add data to the beaconDataMap
		// beaconDataMap.put("SensorTag", "517,282"); //
		// beaconDataMap.put("Beacon 8", "517,282");
		beaconDataMap.put("Beacon8", "517,282");
		beaconDataMap.put("Beacon7", "516,451");
		beaconDataMap.put("Beacon6", "619,584");
		try {
			manager.setServerAddress("10.156.14.140", OutputHandler.COAP); // must
																			// statement
																			// for
																			// output
																			// handler
																			// to
																			// start
			manager.addStreamProcessor("Stream1", "int x,int y");
			manager.addStreamProcessor("Stream2", "int x,int y");
			StreamProcessor stream1 = manager.getSourceStream("Stream1");
			StreamProcessor stream2 = manager.getSourceStream("Stream2");
			InputHandler handler = stream1.getInputHandler();
			InputHandler handler2 = stream2.getInputHandler();
			handler.setOutputHandler(stream2, "x,y");
			handler.addQuery("[x>(0) and y>(0)]");
			handler2.addQuery("[x>(0) and y>(0)]");
			stream1.setQueueId(null); // starts the stream........
			stream2.setQueueId(null);
		} catch (lessArgumentsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (differentTypesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onEvent(SNaaSBeaconData data) {

		Log.e("EventBusonEvent : ", SNaaSBeaconData.getData());

		StringInputParser parser = new StringInputParser();
		manager.addEventToQueue(
				"Stream1",
				new TimeEventPair(System.currentTimeMillis(), parser
						.parse(beaconDataMap.get(SNaaSBeaconData.getData()))));
	}
}
