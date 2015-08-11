package IEEE1451.layer0.datatypes.teds.chan;

import IEEE1451.layer0.messages.EncodeOctetStream;
import IEEE1451.layer0.datatypes.*;
import IEEE1451.layer0.datatypes.teds.*;
import IEEE1451.layer0.messages.DecodeOctetStream;

/**
 * Self-test key
 * @info 8.5.2.21 (p.103)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class SelfTest extends DataBlock{

    public static final int NO_SELF_TEST = 0;
    public static final int SELF_TEST_PROVIDED = 1;


    private UInt8 value;

    private SelfTest(){
        super(DataBlock.SELFTEST);
        value = new UInt8();
    }

    public SelfTest(int val) throws Exception {
        this();
        value.setValue(val);
    }

    public SelfTest(DataBlock db, UInt8[] args) throws Exception{
        this();

        DecodeOctetStream dos = new DecodeOctetStream(args);
        value = dos.readUInt8();
    }

    public UInt8 getSelfTest(){
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
