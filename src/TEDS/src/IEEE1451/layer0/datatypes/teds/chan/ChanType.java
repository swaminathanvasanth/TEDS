package IEEE1451.layer0.datatypes.teds.chan;

import IEEE1451.layer0.messages.EncodeOctetStream;
import IEEE1451.layer0.datatypes.*;
import IEEE1451.layer0.datatypes.teds.*;
import IEEE1451.layer0.messages.DecodeOctetStream;

/**
 * TransducerChannel type key
 * @info 8.5.2.5 (p.95)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class ChanType extends DataBlock{
    
    public static final int SENSOR = 0;
    public static final int ACTUATOR = 1;
    public static final int EVENT_SENSOR = 2;

    private UInt8 value;

    private ChanType(){
        super(DataBlock.CHANTYPE);
        value = new UInt8();
    }

    public ChanType(int val) throws Exception {
        this();
        value.setValue(val);
    }

    public ChanType(DataBlock db, UInt8[] args) throws Exception{
        this();

        DecodeOctetStream dos = new DecodeOctetStream(args);
        value = dos.readUInt8();
    }

    public UInt8 getChanType(){
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
