package IEEE1451.XMLParser;

public class ChannelTEDS_Data {

	public static String calkey = "";
	public static String chantype = "";
	public static String phyunits = "";
	public static String lowlimit = "";
	public static String hilimit = "";
	public static String oerror = "";
	public static String selftest = "";
	public static String sample = "";
	public static String updatet = "";
	public static String rsetupt = "";
	public static String warmupt = "";
	public static String rdelayt = "";
	public static String sampling = "";
	public static String desc_channelteds = "";


	public String getcalkey() {
		return calkey;
	}
	
	public void setcalkey(String calkey) {
		// TODO Auto-generated method stub
		ChannelTEDS_Data.calkey  = calkey;
	}
	
	public String getchantype() {
		return chantype;
	}
	
	public void setchantype(String chantype) {
		// TODO Auto-generated method stub
		ChannelTEDS_Data.chantype  = chantype;
	}
	
	public String getphyunits() {
		return phyunits;
	}
	
	public void setphyunits(String phyunits) {
		// TODO Auto-generated method stub
		ChannelTEDS_Data.phyunits  = phyunits;
	}
	
	public String getlowlimit() {
		return lowlimit;
	}
	
	public void setlowlimit(String lowlimit) {
		// TODO Auto-generated method stub
		ChannelTEDS_Data.lowlimit  = lowlimit;
	}
	
	public String gethilimit() {
		return hilimit;
	}
	
	public void sethilimit(String hilimit) {
		// TODO Auto-generated method stub
		ChannelTEDS_Data.hilimit  = hilimit;
	}
	
	public String getoerror() {
		return oerror;
	}
	
	public void setoerror(String oerror) {
		// TODO Auto-generated method stub
		ChannelTEDS_Data.oerror  = oerror;
	}
	
	public String getselftest() {
		return selftest;
	}
	
	public void setselftest(String selftest) {
		// TODO Auto-generated method stub
		ChannelTEDS_Data.selftest  = selftest;
	}
	
	public String getsample() {
		return sample;
	}
	
	public void setsample(String sample) {
		// TODO Auto-generated method stub
		ChannelTEDS_Data.sample  = sample;
	}
	
	public String getupdatet() {
		return updatet;
	}
	
	public void setupdatet(String updatet) {
		// TODO Auto-generated method stub
		ChannelTEDS_Data.updatet  = updatet;
	}
	
	public String getrsetupt() {
		return rsetupt;
	}
	
	public void setrsetupt(String rsetupt) {
		// TODO Auto-generated method stub
		ChannelTEDS_Data.rsetupt  = rsetupt;
	}
	
	public String getwarmupt() {
		return warmupt;
	}
	
	public void setwarmupt(String warmupt) {
		// TODO Auto-generated method stub
		ChannelTEDS_Data.warmupt  = warmupt;
	}
	
	public String getrdelayt() {
		return rdelayt;
	}
	
	public void setrdelayt(String rdelayt) {
		// TODO Auto-generated method stub
		ChannelTEDS_Data.rdelayt  = rdelayt;
	}
	
	public String getsampling() {
		return sampling;
	}
	
	public void setsampling(String sampling) {
		// TODO Auto-generated method stub
		ChannelTEDS_Data.sampling  = sampling;
	}
	
	public String getdesc_channelteds() {
		return desc_channelteds;
	}
	
	public void setdesc_channelteds(String desc_channelteds) {
		// TODO Auto-generated method stub
		ChannelTEDS_Data.desc_channelteds  = desc_channelteds;
	}
	
	@Override
	public String toString() {
		return "ChannelTEDS_Data [Caliberation Key = " + calkey + ", Channel Type = " + chantype + ", Physical Units = " + phyunits
				+ ", Lower Limit = " + lowlimit + ", Higher Limit = " + hilimit +", OError = " + oerror + ", Self Test = " + selftest + ", Sample = " + sample + ", Update Time = " + updatet + ", Read Setup Time = " + rsetupt + ", Warm up Time = " + warmupt + ", Read Delay Time = " + rdelayt + ", Samping = " + sampling + "desc_cdhannelteds=" + desc_channelteds
				+ "]";
	}
}
