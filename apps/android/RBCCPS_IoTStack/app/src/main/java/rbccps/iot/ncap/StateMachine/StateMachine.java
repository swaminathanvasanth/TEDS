/*
 *
 * $RCSfile: StateMachine.java $
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

package rbccps.iot.ncap.StateMachine;

import android.app.Activity;
import android.app.Service;

import java.lang.reflect.InvocationTargetException;

public class StateMachine {

	public interface StateTransfer {
		public void nextState(String str);
	}

	private StateTransfer callerClass;

	public StateMachine(Activity activity) {
		callerClass = (StateTransfer) activity;
	}

	public StateMachine(Service service) {
		// TODO Auto-generated constructor stub
		callerClass = (StateTransfer) service;
	}

	public StateMachine(Class _class) {
		// TODO Auto-generated constructor stub
		try {
			callerClass = (StateTransfer) _class.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void findStateTransfer(int currentState)
			throws InvocationTargetException {
		if (currentState == 1)
			callerClass.nextState("Current State : Initialize, Next State : SCAN");
		else if (currentState == 2)
			callerClass.nextState("Current State : SCAN, Next State : Process");
		else if (currentState == 3)
			callerClass.nextState("Current State : Process, Next State : UUID Processor");
		else if (currentState == 4)
			callerClass.nextState("Current State : UUID Processor, Next State : TEDS Decoder");
		else if (currentState == 5)
			callerClass.nextState("Current State : TEDS Decoder, Next State : Transducer Identification");
		else if (currentState == 6)
			callerClass.nextState("Current State : Transducer Identification, Next State : Transducer Channel Type Config");
		else if (currentState == 7)
			callerClass.nextState("Current State : Transducer Channel Type Config, Next State : Transducer Channel Type Config Op Mode");
		else if (currentState == 8)
			callerClass.nextState("Current State :  Transducer Channel Type Config Op Mode, Next State : Call to Data Plane");
	}
}
