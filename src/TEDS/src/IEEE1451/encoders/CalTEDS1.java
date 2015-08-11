package IEEE1451.encoders;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import IEEE1451.Teds_Options;
import IEEE1451.decoder.BinarySplitter;
import IEEE1451.layer0.datatypes.UInt8;
import IEEE1451.layer0.datatypes.teds.DChannel;
import IEEE1451.layer0.datatypes.teds.DMaxVal;
import IEEE1451.layer0.datatypes.teds.DMinVal;
import IEEE1451.layer0.datatypes.teds.DSensor;
import IEEE1451.layer0.datatypes.teds.DTEDSID;
import IEEE1451.layer0.datatypes.teds.DUnits;
import IEEE1451.layer0.datatypes.teds.DZeroerror;
import IEEE1451.layer0.datatypes.teds.TEDS;
import IEEE1451.layer0.messages.DecodeOctetStream;


public class CalTEDS1 {
	
	DChannel channel_no;
	DSensor sensor_type;
	DUnits units;
	DMinVal min_val;
	DMaxVal max_val;
	DZeroerror zero_error;
	
	public BinarySplitter splitter = new BinarySplitter();
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	DecodeOctetStream decodeOctetStream;
	
	public void main(Teds_Options teds_options) throws Exception {
		TEDS calteds = new TEDS();
		
		channel_no = new DChannel(teds_options.channel_no);
		calteds.addDataBlock(channel_no);
		
		sensor_type = new DSensor(teds_options.sensor_type);
		calteds.addDataBlock(sensor_type);
		
		units = new DUnits(teds_options.units);
		calteds.addDataBlock(units);
		
		min_val = new DMinVal(teds_options.min_val);
		calteds.addDataBlock(min_val);
		
		max_val = new DMaxVal(teds_options.max_val);
		calteds.addDataBlock(max_val);
		
		zero_error = new DZeroerror(teds_options.zero_error);
		calteds.addDataBlock(zero_error);
		
		UInt8[] a = calteds.getOctetArray();
		
		System.out
		.println("\n-------------------- BINARY TEDS --------------------");
		System.out.println("Binary TEDS Structure : [TYPE, LENGTH, VALUE]");
		System.out.print("Cahnnel No. : ");
		System.out.println(Arrays.toString(channel_no.getOctetArray()));
		
		System.out.print("Units : ");
		System.out.println(Arrays.toString(units.getOctetArray()));
		
		System.out.print("Minimum Value : ");
		System.out.println(Arrays.toString(min_val.getOctetArray()));
		
		System.out.print("Maximum Value : ");
		System.out.println(Arrays.toString(max_val.getOctetArray()));
		
		System.out.print("Zero Error : ");
		System.out.println(Arrays.toString(zero_error.getOctetArray()));
		System.out
		.println("-------------------- BINARY TEDS --------------------");
		
		String s = new String();
		s = TEDS.encodeTEDS(a);

		System.out.println(s);
		System.out
		.println("-------------------- BINARY TEDS --------------------");

		
	}
	
	public UInt8[] getChannelno() {
		return channel_no.getOctetArray();
	}
	public UInt8[] getSensortype() {
		return sensor_type.getOctetArray();
	}
	public UInt8[] getUnits() {
		return units.getOctetArray();
	}
	public UInt8[] getMinval() {
		return min_val.getOctetArray();
	}
	public UInt8[] getMaxval() {
		return max_val.getOctetArray();
	}
	public UInt8[] getZeroerror() {
		return zero_error.getOctetArray();
	}

}
