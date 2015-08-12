/*
 * 
 * $RCSfile: TEDS.java $	
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
 * @author Swaminathan Vasanth Rajaraman (swaminathanvasanth.r@gmail.com)
 */


package IEEE1451.TEDS;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import IEEE1451.Teds_Options;
import IEEE1451.XMLParser.CaliberationTEDS_Data;
import IEEE1451.XMLParser.CaliberationTEDS_Parser_Handler;
import IEEE1451.XMLParser.ChannelTEDS_Data;
import IEEE1451.XMLParser.ChannelTEDS_Parser_Handler;
import IEEE1451.XMLParser.MetaTEDS_Data;
import IEEE1451.XMLParser.MetaTEDS_Parser_Handler;
import IEEE1451.decoder.TEDSDecoder;
import IEEE1451.encoders.CalTEDS1;
import IEEE1451.encoders.ChanTEDS1;
import IEEE1451.encoders.MetaTEDS_B;


public class TEDS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		
		//TEDS_Data initialisation
		MetaTEDS_Data meta_data = new MetaTEDS_Data();
		ChannelTEDS_Data channel_data = new ChannelTEDS_Data();
		CaliberationTEDS_Data caliberation_data = new CaliberationTEDS_Data();
		
		try {
			SAXParser saxParser = saxParserFactory.newSAXParser();
			
			//MetaTEDS Handler
			MetaTEDS_Parser_Handler handler = new MetaTEDS_Parser_Handler();
			saxParser.parse(new File("/home/vasanth/IEEE1451/TEDS/000000144F01000061DB.xml"), 
					handler); ///home/vasanth/Desktop/MetaTEDS.xml
			//ChannelTEDS Handler
			ChannelTEDS_Parser_Handler handler1 = new ChannelTEDS_Parser_Handler();
			saxParser.parse(new File("/home/vasanth/IEEE1451/TEDS/000000144F01000061DB.xml"), 
					handler1); ///home/vasanth/Desktop/MetaTEDS.xml
			//CaliberationTEDS Handler
			CaliberationTEDS_Parser_Handler handler2 = new CaliberationTEDS_Parser_Handler();
			saxParser.parse(new File("/home/vasanth/IEEE1451/TEDS/000000144F01000061DB.xml"), 
					handler2); ///home/vasanth/Desktop/MetaTEDS.xml
			
			System.out.println("Parsing TEDS XML File.");
			System.out.println("...");
			System.out.println("...");
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("\nFile Parsed !");
		
		Teds_Options teds_options = new Teds_Options();
		teds_options.uuid = meta_data.getUuid();
		teds_options.maxchan = meta_data.getMaxchan();
		teds_options.desc_metateds = meta_data.getDesc_metateds();
		teds_options.oholdoff = meta_data.getOholdoff();
		
		teds_options.calkey = channel_data.getcalkey();
		teds_options.chantype = channel_data.getchantype();
		teds_options.phyunits = channel_data.getphyunits();
		teds_options.lowlimit = channel_data.getlowlimit();
		teds_options.hilimit = channel_data.gethilimit();
		teds_options.oerror = channel_data.getoerror();
		teds_options.selftest = channel_data.getselftest();
		teds_options.sample = channel_data.getsample();
		teds_options.updatet = channel_data.getupdatet();
		teds_options.rsetupt = channel_data.getrsetupt();
		teds_options.warmupt = channel_data.getwarmupt();
		teds_options.rdelayt = channel_data.getrdelayt();
		teds_options.sampling = channel_data.getsampling();
		teds_options.desc_channelteds = channel_data.getdesc_channelteds();
		
		teds_options.channel_no = caliberation_data.getchannel_no();
		teds_options.sensor_type = caliberation_data.getsensor_type();
		teds_options.units = caliberation_data.getunits();
		teds_options.min_val = caliberation_data.getmin_val();
		teds_options.max_val = caliberation_data.getmax_val();
		teds_options.zero_error = caliberation_data.getzero_error();

		System.out.println("\nGenerating Binary TEDS..");
		System.out.println("...");
		System.out.println("...");

		MetaTEDS_B metateds = new MetaTEDS_B();
		ChanTEDS1 chanteds = new ChanTEDS1();
		CalTEDS1 calteds = new CalTEDS1();
		//PhyTEDS1 phyteds = new PhyTEDS1();

		try {
			//TEDS Main function
			metateds.main(teds_options);
			chanteds.main(teds_options);
			calteds.main(teds_options);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\nDone !");
	}
}
