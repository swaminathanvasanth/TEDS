package IEEE1451.layer0.datatypes.teds.chan;

import IEEE1451.layer0.messages.EncodeOctetStream;
import IEEE1451.layer0.datatypes.*;
import IEEE1451.layer0.datatypes.teds.*;
import IEEE1451.layer0.messages.DecodeOctetStream;

/**
 * Event sensor options
 * @info 8.5.2.54 (p.118)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class ESOption extends DataBlock{

    public static final int NOT_APPLICABLE = 0;
    public static final int NOT_CHANGEABLE = 1;
    public static final int CHANGEABLE_INCONSISTENCIES_DETECTED = 2;
    public static final int CHANGEABLE_INCONSISTENCIES_NOT_DETECTED = 3;

    private UInt8 value;

    private ESOption(){
        super(DataBlock.ESOPTION);
        value = new UInt8();
    }

    public ESOption(int val) throws Exception {
        this();
        value.setValue(val);
    }

    public ESOption(DataBlock db, UInt8[] args) throws Exception{
        this();

        DecodeOctetStream dos = new DecodeOctetStream(args);
        value = dos.readUInt8();
    }

    public UInt8 getESOption(){
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
