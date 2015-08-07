package org.purc.purcforms.client;

public class xform {

	static String xform_2 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ " <xforms xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">"
			+ "  <model>"
			+ "<model><instance id=\"form\">"
			+ "<form name=\"form\" id=\"1\" formKey=\"form\">"
			+ "    <uuid/>"
			+ "    <tedsid/>"
			+ "    <oholdoff/>"
			+ "    <maxchan/>"
			+ "    <desc_metateds/>"
			+ "</form>"
			+ "  </instance>"
			+ "   <bind id=\"uuid\" nodeset=\"/new_form1/uuid\" type=\"xsd:string\"/>"
			+ "  <bind id=\"tedsid\" nodeset=\"/new_form1/tedsid\" type=\"xsd:int\"/>"
			+ "   <bind id=\"oholdoff\" nodeset=\"/new_form1/oholdoff\" type=\"teds:UInt8\"/>"
			+ "  <bind id=\"maxchan\" nodeset=\"/new_form1/maxchan\" type=\"teds:UInt16\"/>"
			+ "   <bind id=\"desc_metateds\" nodeset=\"/new_form1/desc_metateds\" type=\"xsd:string\"/>"
			+ "  </model>" + " <group id=\"1\">" + "   <label>Page1</label>"
			+ "  <input bind=\"tedsid\">" + "  <input bind=\"uuid\">"
			+ "     <label>UUID          - </label>"
			+ "    <label>TEDS ID       - </label>" + "   </input>"
			+ "    </input>" + "   <input bind=\"oholdoff\">"
			+ "    <label>Oholdoff      -</label>" + "   </input>"
			+ "  <input bind=\"maxchan\">"
			+ "    <label>Maxchannel    -</label>" + "  </input>"
			+ "   <input bind=\"desc_metateds\">"
			+ "     <label>Description   -</label>" + "   </input>"
			+ "  </group>" + " </xforms>";
	
	static String xform = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ " <xforms xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">"
			+ "<model><instance id=\"form\">"
			+ "<form name=\"form\" id=\"1\" formKey=\"form\">"
			+ "<tedsid/>"
			+ "<uuid/>"
			+ "<oholdoff/>"
			+ "<maxchan/>"
			+ "<desc_metateds/>"
			+ "<int8/>"
			+ "<int16/>"
			+ "<int32/>"
			+ "<float64/>"
			+ "<float32/>"
			+ "<uint8array/>"
			+ "<uint16array/>"
			+ "<uint32array/>"
			+ "<int8array/>"
			+ "<int16array/>"
			+ "<int32array/>"
			+ "<float32array/>"
			+ "<float64array/>"
			+ "<stringarray/>"
			+ "<booleanarray/>"
			+ "</form>"
			+ "</instance>"
			+ "<bind id=\"tedsid\" nodeset=\"/form/tedsid\" type=\"xsd:string\"/>"
			+ "<bind id=\"uuid\" nodeset=\"/form/uuid\" type=\"xsd:string\"/>"
			+ "<bind id=\"uint8\" nodeset=\"/form/oholdoff\" type=\"teds:UInt8\"/>"
			+ "<bind id=\"uint16\" nodeset=\"/form/maxchan\" type=\"teds:UInt16\"/>"
			+ "<bind id=\"uint32\" nodeset=\"/form/desc_metateds\" type=\"teds:StringArray\"/>"
			+ "<bind id=\"int8\" nodeset=\"/form/int8\" type=\"teds:Int8\"/>"
			+ "<bind id=\"int16\" nodeset=\"/form/int16\" type=\"teds:Int16\"/>"
			+ "<bind id=\"int32\" nodeset=\"/form/int32\" type=\"teds:Int32\"/>"
			+ "<bind id=\"float64\" nodeset=\"/form/float64\" type=\"teds:Float64\"/>"
			+ "<bind id=\"float32\" nodeset=\"/form/float32\" type=\"teds:Float32\"/>"
			+ "<bind id=\"uint8array\" nodeset=\"/form/uint8array\" type=\"teds:UInt8Array\"/>"
			+ "<bind id=\"uint16array\" nodeset=\"/form/uint16array\" type=\"teds:UInt16Array\"/>"
			+ "<bind id=\"uint32array\" nodeset=\"/form/uint32array\" type=\"teds:UInt32Array\"/>"
			+ "<bind id=\"int8array\" nodeset=\"/form/int8array\" type=\"teds:Int8Array\"/>"
			+ "<bind id=\"int16array\" nodeset=\"/form/int16array\" type=\"teds:Int16Array\"/>"
			+ "<bind id=\"int32array\" nodeset=\"/form/int32array\" type=\"teds:Int32Array\"/>"
			+ "<bind id=\"float32array\" nodeset=\"/form/float32array\" type=\"teds:Float32Array\"/>"
			+ "<bind id=\"float64array\" nodeset=\"/form/float64array\" type=\"teds:Float64Array\"/>"
			+ "<bind id=\"stringarray\" nodeset=\"/form/stringarray\" type=\"teds:StringArray\"/>"
			+ "<bind id=\"booleanarray\" nodeset=\"/form/booleanarray\" type=\"teds:BooleanArray\"/>"
			+ "</model><group id=\"1\">"
			+ "<label>Page1</label>"
			+ "<input bind=\"tedsid\"><label>TEDSID          - </label></input>"
			+ "<input bind=\"uuid\"><label>UUID          - </label></input>"
			+ "<input bind=\"uint8\"><label>oholdoff      - </label></input>"
			+ "<input bind=\"uint16\"><label>maxchan       - </label></input>"
			+ "<input bind=\"uint32\"><label>desc_metateds - </label></input>"
			+ "<input bind=\"int8\"><label>Int8          - </label></input>"
			+ "<input bind=\"int16\"><label>Int16         - </label></input>"
			+ "<input bind=\"int32\"><label>Int32         - </label></input>"
			+ "<input bind=\"float64\"><label>Float64       - </label></input>"
			+ "<input bind=\"float32\"><label>Float32       - </label></input>"
			+ "<input bind=\"uint8array\"><label>UInt8Array    - </label></input>"
			+ "<input bind=\"uint16array\"><label>UInt16Array   - </label></input>"
			+ "<input bind=\"uint32array\"><label>UInt32Array   - </label></input>"
			+ "<input bind=\"int8array\"><label>Int8Array     - </label></input>"
			+ "<input bind=\"int16array\"><label>Int16Array    - </label></input>"
			+ "<input bind=\"int32array\"><label>Int32Array    - </label></input>"
			+ "<input bind=\"float32array\"><label>Float32Array  - </label></input>"
			+ "<input bind=\"float64array\"><label>Float64Array  - </label></input>"
			+ "<input bind=\"stringarray\"><label>StringArray   - </label></input>"
			+ "<input bind=\"booleanarray\"><label>BooleanArray  - </label></input>"
			+ "</group></xforms>";
	
	static String xform_ = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ " <xforms xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">"
			+ "<model><instance id=\"form\">"
			+ "<form name=\"form\" id=\"1\" formKey=\"form\">"
			+ "<tedsid/>"
			+ "<uuid/>"
			+ "<oholdoff/>"
			+ "<maxchan/>"
			+ "<desc_metateds/>"
			+ "</form>"
			+ "</instance>"
			+ "<bind id=\"tedsid\" nodeset=\"/form/tedsid\" type=\"xsd:string\"/>"
			+ "<bind id=\"uuid\" nodeset=\"/form/uuid\" type=\"xsd:string\"/>"
			+ "<bind id=\"uint8\" nodeset=\"/form/oholdoff\" type=\"teds:UInt8\"/>"
			+ "<bind id=\"uint16\" nodeset=\"/form/maxchan\" type=\"teds:UInt16\"/>"
			+ "<bind id=\"uint32\" nodeset=\"/form/desc_metateds\" type=\"teds:StringArray\"/>"
			+ "</model><group id=\"1\">"
			+ "<label>Page1</label>"
			+ "<input bind=\"tedsid\"><label>TEDSID        - </label></input>"
			+ "<input bind=\"uuid\"><label>UUID          - </label></input>"
			+ "<input bind=\"uint8\"><label>Oholdoff      - </label></input>"
			+ "<input bind=\"uint16\"><label>Maxchan       - </label></input>"
			+ "<input bind=\"uint32\"><label>Description   - </label></input>"
			+ "</group></xforms>";

	
	static String layoutxml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + 
	"<Form id=\"1\"><Page Text=\"Page1\" " +  "fontWeight=\"normal\" fontSize=\"16px\"" 
	+ " fontFamily=\"Verdana, 'Lucida Grande', 'Trebuchet MS', Arial, Sans-Serif\" "	
	+ "Binding=\"Page1\" Width=\"900px\" Height=\"231px\" backgroundColor=\"\"> +"
	+ "<Item WidgetType=\"TextBox\" HelpText=\"Question1\" Binding=\"question1\" Left=\"98px\" Top=\"20px\" Width=\"200px\" Height=\"25px\" TabIndex=\"1\" "
    + "fontSize=\"16px\" fontFamily=\"Verdana,'Lucida Grande','Trebuchet MS',Arial,Sans-Serif\"/>"
    + "<Item WidgetType=\"Button\" Text=\"Cancel\" HelpText=\"cancel\" Binding=\"cancel\" Left=\"220px\" Top=\"70px\" Width=\"70px\" Height=\"30px\" TabIndex=\"0\""
    + " fontSize=\"16px\" fontFamily=\"Verdana,'Lucida Grande','Trebuchet MS',Arial,Sans-Serif\"/>"
    + "<Item WidgetType=\"Label\" Text=\"Question1\" HelpText=\"Question1\" Binding=\"question1\" Left=\"20px\" Top=\"20px\" TabIndex=\"0\" fontSize=\"16px\""
    + "fontFamily=\"Verdana,'Lucida Grande','Trebuchet MS',Arial,Sans-Serif\"/>"
    + "<Item WidgetType=\"Button\" Text=\"Submit\" HelpText=\"submit\" Binding=\"submit\" Left=\"20px\" Top=\"70px\" Width=\"70px\" Height=\"30px\" TabIndex=\"0\""
    + "fontSize=\"16px\" fontFamily=\"Verdana,'Lucida Grande','Trebuchet MS',Arial,Sans-Serif\"/>"
    + "</Page>"
    + "</Form>";


	
	static String _xform_ = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
							"<xforms xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">"+
							"<model>"+
							"<instance id=\"temperature_sensor\">"+
							"<temperature_sensor name=\"Temperature Sensor\" id=\"1\" temperature_sensorKey=\"temperature_sensor\" formKey=\"form\">"+
							"<tedsid/>"+
	        				"<uuid/>"+
	        				"<oholdoff/>"+
	        				"<maxchan/>"+
	        				"<desc_metateds/>"+
	        				"<temperaturemin/>"+
	        		        "<temperaturemax/>"+
	        		     	"<calibration/>"+
	        				"</temperature_sensor>"+
	        				"</instance>"+
	        				"<bind id=\"tedsid\" nodeset=\"/temperature_sensor/tedsid\" type=\"xsd:string\"/>"+
	        				"<bind id=\"uuid\" nodeset=\"/temperature_sensor/uuid\" type=\"xsd:string\"/>"+
	        				"<bind id=\"oholdoff\" nodeset=\"/temperature_sensor/oholdoff\" type=\"teds:UInt8\"/>"+
	        				"<bind id=\"maxchan\" nodeset=\"/temperature_sensor/maxchan\" type=\"teds:UInt16\"/>"+
	        				"<bind id=\"desc\" nodeset=\"/temperature_sensor/desc_metateds\" type=\"teds:StringArray\"/>"+
	        				"<bind id=\"temperaturemin\" nodeset=\"/temperature_sensor/temperaturemin\" type=\"teds:Int32\"/>"+
	        			    "<bind id=\"temperaturemax\" nodeset=\"/temperature_sensor/temperaturemax\" type=\"teds:Int8\"/>"+
	        			    "<bind id=\"calibration\" nodeset=\"/temperature_sensor/calibration\" type=\"teds:Int16\"/>"+
	        				"</model>"+
	        				"<group id=\"1\">"+
	        				"<label>TEDS Data Structure</label>"+
	        				"<input bind=\"tedsid\">"+
	        				"<label>TEDSID        -</label>"+
	        				"</input>"+
	        				"<input bind=\"uuid\">"+
	        				"<label>UUID          -</label>"+
	        				"</input>"+
	        				"<input bind=\"oholdoff\">"+
	        				"<label>Oholdoff      -</label>"+
	        				"</input>"+
	        				"<input bind=\"maxchan\">"+
	        				"<label>Maxchan       -</label>"+
	        				"</input>"+
	        				"<input bind=\"desc\">"+
	        				"<label>Description   -</label>"+
	        				"</input>"+
	        			    "<input bind=\"temperaturemin\">"+
	        			    "<label>Temperature Unit (MIN)-</label>"+
	        			    "</input>"+
	        			    "<input bind=\"temperaturemax\">"+
	        			    "<label>Temperature Unit (MAX)-</label>"+
	        			    "</input>"+
	        				"<input bind=\"calibration\">"+
	        				"<label>Calibration Data -</label>"+
	        				"</input>"+
	        				"</group>"+
	        				"</xforms>";
	
	
	
	static String _layoutxml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
								"<Form id=\"1\">"+
								"<Page Text=\"Page1\" fontWeight=\"normal\" fontSize=\"16px\" fontFamily=\"Verdana, 'Lucida Grande', 'Trebuchet MS', Arial, Sans-Serif\" Binding=\"Page1\" Width=\"900px\" Height=\"529px\" backgroundColor=\"\">"+
								"<Item WidgetType=\"Label\" Text=\"TEDSID        -\" HelpText=\"TEDSID        -\" Binding=\"tedsid\" Left=\"41px\" Top=\"20px\" TabIndex=\"0\" fontSize=\"16px\" fontFamily=\"Verdana, 'Lucida Grande', 'Trebuchet MS', Arial, sans-serif\"/>"+
								"<Item WidgetType=\"Label\" Text=\"UUID          -\" HelpText=\"UUID          -\" Binding=\"uuid\" Left=\"61px\" Top=\"60px\" TabIndex=\"0\" fontSize=\"16px\" fontFamily=\"Verdana, 'Lucida Grande', 'Trebuchet MS', Arial, sans-serif\"/>"+
								"<Item WidgetType=\"Label\" Text=\"Oholdoff      -\" HelpText=\"Oholdoff      -\" Binding=\"oholdoff\" Left=\"40px\" Top=\"100px\" TabIndex=\"0\" fontSize=\"16px\" fontFamily=\"Verdana, 'Lucida Grande', 'Trebuchet MS', Arial, sans-serif\"/>"+
								"<Item WidgetType=\"Label\" Text=\"Maxchan       -\" HelpText=\"Maxchan       -\" Binding=\"maxchan\" Left=\"35px\" Top=\"140px\" TabIndex=\"0\" fontSize=\"16px\" fontFamily=\"Verdana, 'Lucida Grande', 'Trebuchet MS', Arial, sans-serif\"/>"+
								"<Item WidgetType=\"Label\" Text=\"Description   -\" HelpText=\"Description   -\" Binding=\"desc_metateds\" Left=\"20px\" Top=\"180px\" TabIndex=\"0\" fontSize=\"16px\" fontFamily=\"Verdana, 'Lucida Grande', 'Trebuchet MS', Arial, sans-serif\"/>"+
								"<Item WidgetType=\"Label\" Text=\"Temperature Unit -\" HelpText=\"Temperature Unit -\" Binding=\"int32\" Left=\"17px\" Top=\"227px\" TabIndex=\"0\" fontSize=\"16px\" fontFamily=\"Verdana, 'Lucida Grande', 'Trebuchet MS', Arial, sans-serif\"/>"+
								"<Item WidgetType=\"Label\" Text=\"Calibration Data -\" HelpText=\"Calibration Data -\" Binding=\"int16\" Left=\"16px\" Top=\"273px\" TabIndex=\"0\" fontSize=\"16px\" fontFamily=\"Verdana, 'Lucida Grande', 'Trebuchet MS', Arial, sans-serif\"/>"+
								"<Item WidgetType=\"TextBox\" HelpText=\"TEDSID        -\" Binding=\"tedsid\" Left=\"172px\" Top=\"20px\" Width=\"200px\" Height=\"25px\" TabIndex=\"1\" fontSize=\"16px\" fontFamily=\"Verdana, 'Lucida Grande', 'Trebuchet MS', Arial, sans-serif\"/>"+
								"<Item WidgetType=\"TextBox\" HelpText=\"UUID          -\" Binding=\"uuid\" Left=\"172px\" Top=\"60px\" Width=\"200px\" Height=\"25px\" TabIndex=\"2\" fontSize=\"16px\" fontFamily=\"Verdana, 'Lucida Grande', 'Trebuchet MS', Arial, sans-serif\"/>"+
								"<Item WidgetType=\"TextBox\" HelpText=\"Oholdoff      -\" Binding=\"oholdoff\" Left=\"172px\" Top=\"101px\" Width=\"200px\" Height=\"25px\" TabIndex=\"3\" fontSize=\"16px\" fontFamily=\"Verdana, 'Lucida Grande', 'Trebuchet MS', Arial, sans-serif\"/>"+
								"<Item WidgetType=\"TextBox\" HelpText=\"Maxchan       -\" Binding=\"maxchan\" Left=\"173px\" Top=\"140px\" Width=\"200px\" Height=\"25px\" TabIndex=\"4\" fontSize=\"16px\" fontFamily=\"Verdana, 'Lucida Grande', 'Trebuchet MS', Arial, sans-serif\"/>"+
								"<Item WidgetType=\"TextBox\" HelpText=\"Calibration Data -\" Binding=\"calibration\" Left=\"172px\" Top=\"267px\" Width=\"200px\" Height=\"25px\" TabIndex=\"14\" fontSize=\"16px\" fontFamily=\"Verdana, 'Lucida Grande', 'Trebuchet MS', Arial, sans-serif\"/>"+
								"<Item WidgetType=\"TextBox\" HelpText=\"Temperature Unit -\" Binding=\"temperature\" Left=\"174px\" Top=\"224px\" Width=\"200px\" Height=\"28px\" TabIndex=\"13\" fontSize=\"16px\" fontFamily=\"Verdana, 'Lucida Grande', 'Trebuchet MS', Arial, sans-serif\"/>"+
								"<Item WidgetType=\"TextBox\" HelpText=\"Description   -\"Binding=\"desc_metateds\" Left=\"172px\" Top=\"183px\" Width=\"200px\" Height=\"25px\" TabIndex=\"5\" fontSize=\"16px\" fontFamily=\"Verdana, 'Lucida Grande', 'Trebuchet MS', Arial, sans-serif\"/>"+
								"<Item WidgetType=\"Button\" Text=\"Submit\" HelpText=\"submit\" Binding=\"submit\" Left=\"160px\" Top=\"342px\" Width=\"70px\" Height=\"30px\" TabIndex=\"0\" fontSize=\"16px\" fontFamily=\"Verdana, 'Lucida Grande', 'Trebuchet MS', Arial, sans-serif\"/>"+
								"<Item WidgetType=\"Button\" Text=\"Cancel\" HelpText=\"cancel\" Binding=\"cancel\" Left=\"332px\" Top=\"344px\" Width=\"70px\" Height=\"30px\" TabIndex=\"0\" fontSize=\"16px\" fontFamily=\"Verdana, 'Lucida Grande', 'Trebuchet MS', Arial, sans-serif\"/>"+
								"</Page>"+
								"</Form>";
	
	
	
	public String getxml() {
		// TODO Auto-generated method stub
		return _xform_;
	}
	
	public String getlayoutxml() {
		// TODO Auto-generated method stub
		return _layoutxml_;
	}
}
