package IEEE1451;

import IEEE1451.XMLParser.MetaTEDS_Data;
import IEEE1451.layer0.datatypes.Float32;

public class Teds_Options {
	// MetaTeds
	public String TEDSid = MetaTEDS_Data.TEDSid;
	public String uuid = MetaTEDS_Data.uuid;
	public String oholdoff = MetaTEDS_Data.oholdoff;
	public String maxchan = MetaTEDS_Data.maxchan;
	public String desc_metateds = MetaTEDS_Data.desc_metateds;

	// Channel TEDS
	public String calkey = "";
	public String chantype = "";
	public String phyunits = "";
	public String lowlimit = "";
	public String hilimit = "";
	public String oerror = "";
	public String selftest = "";
	public String sample = "";
	public String updatet = "";
	public String rsetupt = "";
	public String warmupt = "";
	public String rdelayt = "";
	public String sampling = "";
	public String desc_channelteds = "";
	
	// Calibration TEDS
	public String channel_no = "";
	public String sensor_type = "";
	public String units = "";
	public String min_val = "";
	public String max_val = "";
	public String zero_error = "";
	
	
	public static final float NO_SELF_TEST = Float32.NaN;
}
