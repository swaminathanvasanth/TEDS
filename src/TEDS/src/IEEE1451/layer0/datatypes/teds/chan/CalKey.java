package IEEE1451.layer0.datatypes.teds.chan;

import IEEE1451.layer0.messages.EncodeOctetStream;
import IEEE1451.layer0.datatypes.*;
import IEEE1451.layer0.datatypes.teds.*;
import IEEE1451.layer0.messages.DecodeOctetStream;

/**
 * Calibration key
 * @info 8.5.2.2 (p.95)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class CalKey extends DataBlock{

    public static final int CAL_NONE = 0;
    public static final int CAL_SUPPLIED = 1;
    public static final int CAL_CUSTOM = 3;
    public static final int TIM_CAL_SUPPLIED = 4;
    public static final int TIM_CAL_SELF = 5;
    public static final int TIM_CAL_CUSTOM = 6;


    private UInt8 value;

    private CalKey(){
        super(DataBlock.CALKEY);
        value = new UInt8();
    }

    public CalKey(int val) throws Exception {
        this();
        value.setValue(val);
    }

    public CalKey(DataBlock db, UInt8[] args) throws Exception{
        this();

        DecodeOctetStream dos = new DecodeOctetStream(args);
        value = dos.readUInt8();
    }

    public UInt8 getCalKey(){
        return value;
    }

    public UInt8[] getOctetArray() {
        try {
            EncodeOctetStream stream = new EncodeOctetStream(this);
            stream.addUInt8(value);
            return stream.getOctetsArray();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int getLength() {
        return UInt8.NUMBER_OF_OCTETS;
    }

}
