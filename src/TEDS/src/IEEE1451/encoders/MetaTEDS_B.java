package IEEE1451.encoders;


import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import IEEE1451.Teds_Options;
import IEEE1451.XMLParser.MetaTEDS_Data;
import IEEE1451.decoder.BinarySplitter;
import IEEE1451.decoder.TEDSDecoder;
import IEEE1451.layer0.datatypes.UInt8;
import IEEE1451.layer0.datatypes.teds.DMaxChan;
import IEEE1451.layer0.datatypes.teds.DOholdoff;
import IEEE1451.layer0.datatypes.teds.DTEDSID;
import IEEE1451.layer0.datatypes.teds.DTestTime;
import IEEE1451.layer0.datatypes.teds.DUUID;
import IEEE1451.layer0.datatypes.teds.Description;
import IEEE1451.layer0.datatypes.teds.TEDS;
import IEEE1451.layer0.messages.DecodeOctetStream;
import metaTEDS.sql.db.MetaTEDS_SQLDB;

/**
 * Create MetaTEDS
 * 
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class MetaTEDS_B {
	DTEDSID tedsid;
	DUUID uuid;
	DOholdoff oholdoff;
	DTestTime testtime;
	DMaxChan maxchan;
	Description desc;
	
	// Decoder Part
	public BinarySplitter splitter = new BinarySplitter();
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	DecodeOctetStream decodeOctetStream;

	public void main(Teds_Options teds_options) throws Exception {
		TEDS metateds = new TEDS();

		tedsid = new DTEDSID(teds_options.TEDSid);
		// tedsid = new TEDSID();
		metateds.addDataBlock(tedsid);

		// 0000 + MAC address
		uuid = new DUUID(teds_options.uuid);
		metateds.addDataBlock(uuid);

		// oholdoff = new OHoldOff(Float.parseFloat(teds_options.oholdoff));
		oholdoff = new DOholdoff(teds_options.oholdoff);
		metateds.addDataBlock(oholdoff);

		// testtime = new DTestTime(String.valueOf(Teds_Options.NO_SELF_TEST));
		testtime = new DTestTime("1.23");
		metateds.addDataBlock(testtime);

		// 2 channels (temp, volt)
		maxchan = new DMaxChan(teds_options.maxchan);
		metateds.addDataBlock(maxchan);

		// description
		desc = new Description(teds_options.desc_metateds);
		metateds.addDataBlock(desc);

		UInt8[] a = metateds.getOctetArray();
		
		System.out
				.println("\n-------------------- BINARY TEDS --------------------");
		System.out.println("Binary TEDS Structure : [TYPE, LENGTH, VALUE]");
		System.out.print("tedsid : ");
		System.out.println(Arrays.toString(tedsid.getOctetArray()));

		System.out.print("uuid : ");
		System.out.println(Arrays.toString(uuid.getOctetArray()));

		System.out.print("oholdoff : ");
		System.out.println(Arrays.toString(oholdoff.getOctetArray()));

		System.out.print("testtime : ");
		System.out.println(Arrays.toString(testtime.getOctetArray()));

		System.out.print("maxchan : ");
		System.out.println(Arrays.toString(maxchan.getOctetArray()));

		System.out.print("description : ");
		System.out.println(Arrays.toString(desc.getOctetArray()));
		System.out
		.println("-------------------- BINARY TEDS --------------------");

		String s = new String();
		s = TEDS.encodeTEDS(a);

		System.out.println(s);
		System.out
		.println("-------------------- BINARY TEDS --------------------");
/*		
		System.out.println("\nStoring Binary TEDS in MySQL Database");
		System.out.println("...");
		System.out.println("...");
		
		
		MetaTEDS_SQLDB.main(MetaTEDS_Data.uuid, s);
		
	/*	System.out.println();
		System.out.print("Encoded TEDS : ");
		System.out.println(s);

		
		System.out.println();
		
	*/	
/*		FileWriter file = new FileWriter("/home/vasanth/IEEE1451/TEDS/000000144F01000061DB.xml"); // /home/vasanth/Desktop/MetaTEDS
		file.write(dateFormat.format(date) + " - ");
		file.write("MetaTEDS : ");
		file.write(s);
		file.flush();
		file.close();
*/
	/*	System.out
				.println("Look at /home/vasanth/Desktop/MetaTEDS for the TEDS file.");

		System.out.println();
	*/	
		/*UInt8[] uInt8s = TEDS.decodeTEDS(s);
		decodeOctetStream = new DecodeOctetStream(uInt8s);
		decodeOctetStream.getTeds();
		System.out.println(uInt8s.toString());
		*/
		splitter.split();
		
	/*	System.out.println("----------- Decoded TEDS --------------");
		System.out.print(TEDSDecoder.data);
		System.out.println();
		System.out.println("----------- Decoded TEDS --------------");
	*/	
	}

	public UInt8[] getTedsid() {
		return tedsid.getOctetArray();
	}

	public UInt8[] getUuid() {
		return uuid.getOctetArray();
	}

	public UInt8[] getOholdoff() {
		return oholdoff.getOctetArray();
	}

	public UInt8[] getTesttime() {
		return testtime.getOctetArray();
	}

	public UInt8[] getMaxchan() {
		return maxchan.getOctetArray();
	}

	public UInt8[] getDesc() {
		return desc.getOctetArray();
	}
}
