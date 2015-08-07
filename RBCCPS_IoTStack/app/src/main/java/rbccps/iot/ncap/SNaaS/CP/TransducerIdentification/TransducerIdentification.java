/*
 *
 * $RCSfile: TransducerIdentification.java $
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

package rbccps.iot.ncap.SNaaS.CP.TransducerIdentification;

import android.util.Log;

import java.lang.reflect.InvocationTargetException;

import rbccps.iot.ncap.SNaaS.CP.TransducerConfig.TransducerChannelTypeConfig;
import rbccps.iot.ncap.StateMachine.StateMachine;
import rbccps.iot.ncap.StateMachine.StateMachine.StateTransfer;


public class TransducerIdentification implements StateTransfer {

	public static int Mode = 1;

	public final static int Mode_Continuous = 1;
	public final static int Mode_TriggerInitiated = 2;
	public final static int Mode_FreeRunningWithPreTriggered = 3;

	
	public static void configureTransducerChannel(int mode) {
		Mode = mode;
		
		StateMachine stateMachine = new StateMachine(TransducerIdentification.class);
		try {
			stateMachine.findStateTransfer(6);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void nextState(String str) {
		// TODO Auto-generated method stub
		Log.i("State Transfer", str);
		TransducerChannelTypeConfig.setOperationalMode(Mode);
		
	}
}
