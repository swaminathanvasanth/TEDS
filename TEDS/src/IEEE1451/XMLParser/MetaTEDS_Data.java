/*
 * 
 * $RCSfile: MetaTEDS_Data.java $	
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
 * MetaTEDS_Data
 * @author Swaminathan Vasanth Rajaraman (swaminathanvasanth.r@gmail.com)
 */


package IEEE1451.XMLParser;

public class MetaTEDS_Data {

	public static String uuid = ""; // uint8
	public static String oholdoff = ""; // uint16
	public static String maxchan = ""; // uint32
	public static String desc_metateds = ""; // booleanarray
	public static String TEDSid = ""; // uint32

	public String getUuid() {
		return uuid;
	}


	public void setid(String TEDSid) {
		// TODO Auto-generated method stub
		MetaTEDS_Data.TEDSid  = TEDSid;
	}
	
	public void setUuid(String uuid) {
		MetaTEDS_Data.uuid = uuid;
	}

	public String getOholdoff() {
		return oholdoff;
	}

	public void setOholdoff(String oholdoff) {
		MetaTEDS_Data.oholdoff = oholdoff;
	}

	public String getMaxchan() {
		return maxchan;
	}

	public void setMaxchan(String maxchan) {
		MetaTEDS_Data.maxchan = maxchan;
	}

	public String getDesc_metateds() {
		return desc_metateds;
	}

	public void setDesc_metateds(String desc_metateds) {
		MetaTEDS_Data.desc_metateds = desc_metateds;
	}

	@Override
	public String toString() {
		return "MetaTEDS_Data [id = " + TEDSid + ", uuid=" + uuid + ", oholdoff=" + oholdoff
				+ ", maxchan=" + maxchan + ", desc_metateds=" + desc_metateds
				+ "]";
	}

}
