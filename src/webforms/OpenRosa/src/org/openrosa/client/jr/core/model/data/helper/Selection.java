/*
 * Copyright (C) 2009 JavaRosa
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.openrosa.client.jr.core.model.data.helper;

import java.io.IOException;

import org.openrosa.client.java.io.DataInputStream;
import org.openrosa.client.java.io.DataOutputStream;
import org.openrosa.client.jr.core.model.QuestionDef;
import org.openrosa.client.jr.core.model.SelectChoice;
import org.openrosa.client.jr.core.util.externalizable.DeserializationException;
import org.openrosa.client.jr.core.util.externalizable.ExtUtil;
import org.openrosa.client.jr.core.util.externalizable.Externalizable;
import org.openrosa.client.jr.core.util.externalizable.PrototypeFactory;

/**
 * A response to a question requesting a selection
 * from a list. 
 * 
 * This class may exist in 3 states:
 *
 * 1) only index has a value
 * 2) only xmlValue has a value
 * 3) index, xmlValue, and choice have values, where index and xmlValue are simply cached copies of the values in 'choice'
 * 
 * the 3rd form is the most full-featured, and is required for situations where you want to recover the captions for the
 * choices, such as form entry. the choice objects used in the form entry model will receive localization updates,
 * allowing you to retrieve the appropriate caption.
 * 
 * the 2nd form is useful when dealing with FormInstances without having to worry about the FormDef or the captions
 * from the <select> or <select1> controls. this form contains enough information to convert to an XML instance
 * 
 * the 1st form is used when serializing instances in an ultra-compact manner, but requires linking to a FormDef before
 * you can do anything useful with the instance (insufficient info to convert to XML instance).
 * 
 * @author Drew Roos
 *
 */
public class Selection implements Externalizable {
	public String xmlValue = null;
	public int index = -1;
	
	/* in order to get localizable captions for this selection, the choice object must be the
	 * same object in the form model, or else it won't receive localization updates from form
	 * entry session
	 */
	public SelectChoice choice;
	
	/**
	 * for deserialization only
	 */
	public Selection() {
		
	}
	
	public Selection (SelectChoice choice) {
		attachChoice(choice);	
	}
	
	public Selection (String xmlValue) {
		this.xmlValue = xmlValue;		
	}
	
	public Selection (int index) {
		this.index = index;
	}
	
	public Selection clone () {
		Selection s = new Selection();
		s.choice = choice;
		s.xmlValue = xmlValue;
		s.index = index;

		return s;
	}
	
	public void attachChoice (SelectChoice choice) {
		this.choice = choice;
		this.xmlValue = choice.getValue();
		this.index = choice.getIndex();
	}
		
	public void attachChoice (QuestionDef q) {
		if (q.getDynamicChoices() != null) //can't attach dynamic choices because they aren't guaranteed to exist yet
			return;
		
		SelectChoice choice = null;
		
		if (index != -1 && index < q.getNumChoices()) {
			choice = q.getChoice(index);
		} else if (xmlValue != null && xmlValue.length() > 0) {
			choice = q.getChoiceForValue(xmlValue);
		}
		
		if (choice != null) {
			attachChoice(choice);
		} else {
			throw new RuntimeException("insufficient data in selection to reconstruct");
		}
	}
	

//	@Deprecated
//	public String getText () {
//		if (choice != null) {
//			return choice.getCaption();
//		} else {
//			System.err.println("Warning!! Calling Selection.getText() when Choice object not linked!");
//			return "[cannot access choice caption]";
//		}
//	}
	
	public String getValue () {
		if (xmlValue != null && xmlValue.length() > 0) {
			return xmlValue;
		} else {
			throw new RuntimeException("don't know xml value! perhaps selection was stored as index only and has not yet been linked up to a formdef?");
		}
	}

	/* (non-Javadoc)
	 * @see org.javarosa.core.services.storage.utilities.Externalizable#readExternal(java.io.DataInputStream)
	 */
	public void readExternal(DataInputStream in, PrototypeFactory pf) throws IOException, DeserializationException {
		xmlValue = ExtUtil.readString(in);
		index = ExtUtil.readInt(in);
	}
 
	/* (non-Javadoc)
	 * @see org.javarosa.core.services.storage.utilities.Externalizable#writeExternal(java.io.DataOutputStream)
	 */
	public void writeExternal(DataOutputStream out) throws IOException {
		ExtUtil.writeString(out, getValue());
		ExtUtil.writeNumeric(out, index);
	}
}
