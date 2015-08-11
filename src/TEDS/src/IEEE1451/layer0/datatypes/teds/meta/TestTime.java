package IEEE1451.layer0.datatypes.teds.meta;

import IEEE1451.layer0.messages.EncodeOctetStream;
import IEEE1451.layer0.datatypes.*;
import IEEE1451.layer0.datatypes.teds.*;
import IEEE1451.layer0.messages.DecodeOctetStream;


/**
 * Self-test time
 * @info 8.4.2.6 (p.87)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class TestTime extends DataBlock {

    // NO_SELF_TEST = 0
    public static final float NO_SELF_TEST = Float32.NaN;

    private Float32 value;

    private TestTime(){
        super(DataBlock.TESTTIME);
        value = new Float32();
    }
    
    public TestTime(float val) throws Exception{
        this();
        this.setTestTime(val);
    }

    public TestTime(DataBlock db, UInt8[] args) throws Exception{
        this();

        DecodeOctetStream dos = new DecodeOctetStream(args);
        this.setTestTime(dos.readFloat32().getValue());
    }

    public void setTestTime(float val){
        try {
            value.setValue(val);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Float32 getTestTime(){
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
