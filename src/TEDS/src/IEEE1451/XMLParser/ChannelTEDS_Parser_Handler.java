package IEEE1451.XMLParser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ChannelTEDS_Parser_Handler extends DefaultHandler {
	
	private ChannelTEDS_Data channelTEDS_Data = null;

	boolean calkey = false;
	boolean chantype = false;
	boolean phyunits = false;
	boolean lowlimit = false;
	boolean hilimit = false;
	boolean oerror = false;
	boolean selftest = false;
	boolean sample = false;
	boolean updatet = false;
	boolean rsetupt = false;
	boolean warmupt = false;
	boolean rdelayt = false;
	boolean sampling = false;
	boolean desc_channelteds = false;
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		if (qName.equalsIgnoreCase("ChannelTEDS_Data")) {
			channelTEDS_Data = new ChannelTEDS_Data();
		} else if (qName.equalsIgnoreCase("calkey")) {
			channelTEDS_Data = new ChannelTEDS_Data();
			calkey = true;
		} else if (qName.equalsIgnoreCase("chantype")) {
			chantype = true;
		} else if (qName.equalsIgnoreCase("phyunits")) {
			phyunits = true;
		} else if (qName.equalsIgnoreCase("lowlimit")) {
			lowlimit = true;
		} else if (qName.equalsIgnoreCase("hilimit")) {
			hilimit = true;
		} else if (qName.equalsIgnoreCase("oerror")) {
			oerror = true;
		} else if (qName.equalsIgnoreCase("selftest")) {
			selftest = true;
		} else if (qName.equalsIgnoreCase("sample")) {
			sample = true;
		} else if (qName.equalsIgnoreCase("updatet")) {
			updatet = true;
		} else if (qName.equalsIgnoreCase("rsetupt")) {
			rsetupt = true;
		} else if (qName.equalsIgnoreCase("warmupt")) {
			warmupt = true;
		} else if (qName.equalsIgnoreCase("rdelayt")) {
			rdelayt = true;
		} else if (qName.equalsIgnoreCase("sampling")) {
			sampling = true;
		} else if (qName.equalsIgnoreCase("desc_channelteds")) {
			desc_channelteds = true;
		}
		super.startElement(uri, localName, qName, attributes);
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub


		if (calkey) {
			channelTEDS_Data.setcalkey(new String(ch, start, length));
			calkey = false;
		} else if (chantype) {
			channelTEDS_Data.setchantype(new String(ch, start, length));
			chantype = false;
		} else if (phyunits) {
			channelTEDS_Data.setphyunits(new String(ch, start, length));
			phyunits = false;
		} else if (lowlimit) {
			channelTEDS_Data.setlowlimit(new String(ch, start, length));
			lowlimit = false;
		} else if (hilimit) {
			channelTEDS_Data.sethilimit(new String(ch, start, length));
			hilimit = false;
		} else if (oerror) {
			channelTEDS_Data.setoerror(new String(ch, start, length));
			oerror = false;
		} else if (selftest) {
			channelTEDS_Data.setselftest(new String(ch, start, length));
			selftest = false;
		} else if (sample) {
			channelTEDS_Data.setsample(new String(ch, start, length));
			sample = false;
		} else if (updatet) {
			channelTEDS_Data.setupdatet(new String(ch, start, length));
			updatet = false;
		} else if (rsetupt) {
			channelTEDS_Data.setrsetupt(new String(ch, start, length));
			rsetupt = false;
		} else if (warmupt) {
			channelTEDS_Data.setwarmupt(new String(ch, start, length));
			warmupt = false;
		} else if (rdelayt) {
			channelTEDS_Data.setrdelayt(new String(ch, start, length));
			rdelayt = false;
		} else if (sampling) {
			channelTEDS_Data.setsampling(new String(ch, start, length));
			sampling = false;
		} else if (desc_channelteds) {
			channelTEDS_Data.setdesc_channelteds(new String(ch, start, length));
			desc_channelteds = false;
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





