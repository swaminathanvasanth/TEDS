package IEEE1451.layer0.datatypes.teds.cal;

import IEEE1451.layer0.datatypes.UInt16;
import IEEE1451.layer0.datatypes.UInt8;
import IEEE1451.layer0.datatypes.teds.DataBlock;
import IEEE1451.layer0.messages.DecodeOctetStream;
import IEEE1451.layer0.messages.EncodeOctetStream;

public class Units extends DataBlock {
	
	private UInt16 value;

    private Units(){
        super(DataBlock.UNITS);
        value = new UInt16();        
    }

    public Units(int val) throws Exception{
        this();
        this.setUnits(val);
    }

    public Units(DataBlock db, UInt8[] args) throws Exception{
        this();

        DecodeOctetStream dos = new DecodeOctetStream(args);
        this.setUnits(dos.readUInt16().getValue());
        
    }

    public void setUnits(int val){
        try {
            value.setValue(val);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public UInt16 getUnits(){
        return value;
    }

//    public void setUnits(UInt16 Units){
//        value = Units;
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
