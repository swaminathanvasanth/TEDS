/*
 * 
 * $RCSfile: MetaTEDS_Parser_Handler.java $	
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

/**
 * MetaTEDS_Parser_Handler
 * @author Swaminathan Vasanth Rajaraman (swaminathanvasanth.r@gmail.com)
 */


package IEEE1451.XMLParser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MetaTEDS_Parser_Handler extends DefaultHandler {

	private MetaTEDS_Data metateds_data = null;

	boolean bTEDSid = false;
	boolean buuid = false;
	boolean boholdoff = false;
	boolean bmaxchan = false;
	boolean bdesc_metateds = false;

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub

		if (qName.equalsIgnoreCase("MetaTEDS_Data")) {
			metateds_data = new MetaTEDS_Data();
		} else if (qName.equalsIgnoreCase("TEDSid")) {
			metateds_data = new MetaTEDS_Data();
			bTEDSid = true;
		} else if (qName.equalsIgnoreCase("uuid")) {
			buuid = true;
		} else if (qName.equalsIgnoreCase("oholdoff")) {
			boholdoff = true;
		} else if (qName.equalsIgnoreCase("maxchan")) {
			bmaxchan = true;
		} else if (qName.equalsIgnoreCase("desc_metateds")) {
			bdesc_metateds = true;
		}
		super.startElement(uri, localName, qName, attributes);
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub


		if (bTEDSid) {
			metateds_data.setid(new String(ch, start, length));
			bTEDSid = false;
		} else if (buuid) {
			metateds_data.setUuid(new String(ch, start, length));
			buuid = false;
		} else if (boholdoff) {
			metateds_data.setOholdoff(new String(ch, start, length));
			boholdoff = false;
		} else if (bmaxchan) {
			metateds_data.setMaxchan(new String(ch, start, length));
			bmaxchan = false;
		} else if (bdesc_metateds) {
			metateds_data.setDesc_metateds(new String(ch, start, length));
			bdesc_metateds = false;
		}

		super.characters(ch, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
	}

}
