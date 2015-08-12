package IEEE1451.layer0.datatypes.teds.cal;

import IEEE1451.layer0.datatypes.UInt16;
import IEEE1451.layer0.datatypes.UInt8;
import IEEE1451.layer0.datatypes.teds.DataBlock;
import IEEE1451.layer0.messages.DecodeOctetStream;
import IEEE1451.layer0.messages.EncodeOctetStream;

public class SensorType extends DataBlock {
	
	private UInt16 value;

    private SensorType(){
        super(DataBlock.SENSOR_TYPE);
        value = new UInt16();        
    }

    public SensorType(int val) throws Exception{
        this();
        this.setSensorType(val);
    }

    public SensorType(DataBlock db, UInt8[] args) throws Exception{
        this();

        DecodeOctetStream dos = new DecodeOctetStream(args);
        this.setSensorType(dos.readUInt16().getValue());
        
    }

    public void setSensorType(int val){
        try {
            value.setValue(val);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public UInt16 getSensorType(){
        return value;
    }

//    public void setSensorType(UInt16 SensorType){
//        value = SensorType;
//    }


    public int getLength(){
        return UInt16.NUMBER_OF_OCTETS;
    }

    public UInt8[] getOctetArray() {
        try {
            EncodeOctetStream stream = new EncodeOctetStream(this);
            stream.addUInt16(value);
//////////////////////
            if (!checkOctetArrayLength(this.getType().getValue(), stream.getSize(), getLength() + 2)){
               return null;
            }
///////////////////////
            return stream.getOctetsArray();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
