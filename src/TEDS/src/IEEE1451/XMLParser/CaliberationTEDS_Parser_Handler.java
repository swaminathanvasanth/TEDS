package IEEE1451.XMLParser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CaliberationTEDS_Parser_Handler extends DefaultHandler {
	
	private CaliberationTEDS_Data caliberationteds_data = null;

	boolean channel_no = false;
	boolean sensor_type = false;
	boolean units = false;
	boolean min_val = false;
	boolean max_val = false;
	boolean zero_error = false;

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		if (qName.equalsIgnoreCase("ChannelTEDS_Data")) {
			caliberationteds_data = new CaliberationTEDS_Data();
		} else if (qName.equalsIgnoreCase("channel_no")) {
			caliberationteds_data = new CaliberationTEDS_Data();
			channel_no = true;
		} else if (qName.equalsIgnoreCase("sensor_type")) {
			sensor_type = true;
		} else if (qName.equalsIgnoreCase("units")) {
			units = true;
		} else if (qName.equalsIgnoreCase("min_val")) {
			min_val = true;
		} else if (qName.equalsIgnoreCase("max_val")) {
			max_val = true;
		} else if (qName.equalsIgnoreCase("zero_error")) {
			zero_error = true;
		}
		super.startElement(uri, localName, qName, attributes);
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub


		if (channel_no) {
			caliberationteds_data.setchannel_no(new String(ch, start, length));
			channel_no = false;
		} else if (sensor_type) {
			caliberationteds_data.setsensor_type(new String(ch, start, length));
			sensor_type = false;
		} else if (units) {
			caliberationteds_data.setmin_val(new String(ch, start, length));
			min_val = false;
		} else if (max_val) {
			caliberationteds_data.setmax_val(new String(ch, start, length));
			max_val = false;
		} else if (zero_error) {
			caliberationteds_data.setzero_error(new String(ch, start, length));
			zero_error = false;
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
