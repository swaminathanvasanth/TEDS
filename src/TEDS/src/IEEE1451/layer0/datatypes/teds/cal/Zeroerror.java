package IEEE1451.layer0.datatypes.teds.cal;

import IEEE1451.layer0.datatypes.UInt16;
import IEEE1451.layer0.datatypes.UInt8;
import IEEE1451.layer0.datatypes.teds.DataBlock;
import IEEE1451.layer0.messages.DecodeOctetStream;
import IEEE1451.layer0.messages.EncodeOctetStream;

public class Zeroerror extends DataBlock {
	
	private UInt16 value;

    private Zeroerror(){
        super(DataBlock.ZERO_ERROR);
        value = new UInt16();        
    }

    public Zeroerror(int val) throws Exception{
        this();
        this.setZeroerror(val);
    }

    public Zeroerror(DataBlock db, UInt8[] args) throws Exception{
        this();

        DecodeOctetStream dos = new DecodeOctetStream(args);
        this.setZeroerror(dos.readUInt16().getValue());
        
    }

    public void setZeroerror(int val){
        try {
            value.setValue(val);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public UInt16 getZeroerror(){
        return value;
    }

//    public void setZeroerror(UInt16 Zeroerror){
//        value = Zeroerror;
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
