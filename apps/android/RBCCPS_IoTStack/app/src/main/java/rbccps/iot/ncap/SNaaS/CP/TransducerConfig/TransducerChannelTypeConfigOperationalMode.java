/*
 *
 * $RCSfile: TransducerChannelTypeConfigOperationalMode.java $
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

package rbccps.iot.ncap.SNaaS.CP.TransducerConfig;

import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;

import rbccps.iot.ncap.StateMachine.StateMachine;
import rbccps.iot.ncap.StateMachine.StateMachine.StateTransfer;

public class TransducerChannelTypeConfigOperationalMode implements
		StateTransfer {

	public static int TriggerInterval = 500;

	public static void ConfigOperationalMode(int _operationalMode) {
		// TODO Auto-generated method stub
		int operationalMode = _operationalMode;
		switch (operationalMode) {
		case 1: {
			Log.e("Operational Mode", "Type : 1, Mode_Continuous");

			StateMachine stateMachine = new StateMachine(
					TransducerChannelTypeConfigOperationalMode.class);
			try {
				stateMachine.findStateTransfer(8);
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}

		case 2: {

			Log.i("Operational Mode", "Type : 2, Mode_TriggerInitiated");
			Calendar cal = Calendar.getInstance();
			if (!TransducerChannelTypeConfigStateCheckVariable.timer) {
				TransducerChannelTypeConfigStateCheckVariable.startTime = cal
						.getTimeInMillis();
				TransducerChannelTypeConfigStateCheckVariable.timer = true;
			}
			TransducerChannelTypeConfigStateCheckVariable.currentTime = cal
					.getTimeInMillis();
			if ((TransducerChannelTypeConfigStateCheckVariable.currentTime - TransducerChannelTypeConfigStateCheckVariable.startTime) >= TransducerChannelTypeConfigOperationalMode.TriggerInterval) {
				TransducerChannelTypeConfigStateCheckVariable.timer = false;

				Log.e("Mode_TriggerInitiated",
						TransducerChannelTypeConfigOperationalMode.TriggerInterval
								+ " msec Trigger Initiated. Send data to Process.");

			}

			StateMachine stateMachine = new StateMachine(
					TransducerChannelTypeConfigOperationalMode.class);
			try {
				stateMachine.findStateTransfer(8);
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		}
	}

	@Override
	public void nextState(String str) {
		// TODO Auto-generated method stub
		Log.i("State Transfer", str);
	}

}
