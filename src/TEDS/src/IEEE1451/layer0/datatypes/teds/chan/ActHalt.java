package IEEE1451.layer0.datatypes.teds.chan;

import IEEE1451.layer0.messages.EncodeOctetStream;
import IEEE1451.layer0.datatypes.*;
import IEEE1451.layer0.datatypes.teds.*;
import IEEE1451.layer0.messages.DecodeOctetStream;

/**
 * Actuator-halt attribute
 * @info 8.5.2.51 (p.117)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class ActHalt extends DataBlock{

    public static final int NOT_APPLICABLE = 0;
    public static final int HALT_IMMEDIATE = 1;
    public static final int HALT_END_DATA_SET = 2;
    public static final int RAMP_STATE = 3;

    private UInt8 value;

    private ActHalt(){
        super(DataBlock.ACTHALT);
        value = new UInt8();
    }

    public ActHalt(int val) throws Exception {
        this();
        value.setValue(val);
    }

    public ActHalt(DataBlock db, UInt8[] args) throws Exception{
        this();

        DecodeOctetStream dos = new DecodeOctetStream(args);
        value = dos.readUInt8();
    }

    public UInt8 getActHalt(){
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
