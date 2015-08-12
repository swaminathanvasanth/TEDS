package IEEE1451.XMLParser;

public class CaliberationTEDS_Data {
	
	public static String channel_no = "";
	public static String sensor_type = "";
	public static String units = "";
	public static String min_val = "";
	public static String max_val = "";
	public static String zero_error = "";
	
	public String getchannel_no() {
		return channel_no;
	}

	public void setchannel_no(String channel_no) {
		// TODO Auto-generated method stub
		CaliberationTEDS_Data.channel_no  = channel_no;
	}
	
	public String getsensor_type() {
		return sensor_type;
	}
	
	public void setsensor_type(String sensor_type) {
		// TODO Auto-generated method stub
		CaliberationTEDS_Data.sensor_type  = sensor_type;
	}
	
	public String getunits() {
		return units;
	}
	
	public void setunits(String units) {
		// TODO Auto-generated method stub
		CaliberationTEDS_Data.units  = units;
	}
	
	public String getmin_val() {
		return min_val;
	}
	
	public void setmin_val(String min_val) {
		// TODO Auto-generated method stub
		CaliberationTEDS_Data.min_val  = min_val;
	}
	
	public String getmax_val() {
		return max_val;
	}
	
	public void setmax_val(String max_val) {
		// TODO Auto-generated method stub
		CaliberationTEDS_Data.max_val  = max_val;
	}
	
	public String getzero_error() {
		return zero_error;
	}
	
	public void setzero_error(String zero_error) {
		// TODO Auto-generated method stub
		CaliberationTEDS_Data.zero_error  = zero_error;
	}
	
	@Override
	public String toString() {
		return "CaliberationTEDS_Data [channel_no = " + channel_no + ", sensor_type = " + sensor_type + ", units = " + units
				+ ", min_val = " + min_val + ", max_val = " + max_val + ", zero_error = " + zero_error
				+ "]";
	}

}
