package IEEE1451.layer0.datatypes.teds.meta;

import IEEE1451.layer0.messages.EncodeOctetStream;
import IEEE1451.layer0.datatypes.*;
import IEEE1451.layer0.datatypes.teds.*;
import IEEE1451.layer0.messages.DecodeOctetStream;


/**
 * Operational time-out
 * @info 8.4.2.4 (p.87)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class OHoldOff extends DataBlock {

    private Float32 value;

    private OHoldOff(){
        super(DataBlock.OHOLDOFF);
        value = new Float32();
    }

    public OHoldOff(float val) throws Exception{
        this();
        this.setOHoldOff(val);
    }

    public OHoldOff(DataBlock db, UInt8[] args) throws Exception{
        this();

        DecodeOctetStream dos = new DecodeOctetStream(args);
        this.setOHoldOff(dos.readFloat32().getValue());
    }

    public void setOHoldOff(float val){
        try {
            value.setValue(val);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Float32 getOHolfOff(){
        return value;
    }

    public int getLength(){
        return Float32.NUMBER_OF_OCTETS;
    }

    public UInt8[] getOctetArray() {
        try {
            EncodeOctetStream stream = new EncodeOctetStream(this);
            stream.addFloat32(value);
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
