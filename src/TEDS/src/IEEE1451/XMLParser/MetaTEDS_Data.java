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
