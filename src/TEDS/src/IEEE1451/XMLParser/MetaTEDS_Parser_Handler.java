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
