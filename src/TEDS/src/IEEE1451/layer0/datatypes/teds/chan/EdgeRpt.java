package IEEE1451.layer0.datatypes.teds.chan;

import IEEE1451.layer0.messages.EncodeOctetStream;
import IEEE1451.layer0.datatypes.*;
import IEEE1451.layer0.datatypes.teds.*;
import IEEE1451.layer0.messages.DecodeOctetStream;

/**
 * Edge-to-report attribute
 * @info 8.5.2.50 (p.116)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class EdgeRpt extends DataBlock{

    public static final int NOT_APPLICABLE = 0;
    public static final int RISING_EDGES = 1;
    public static final int FALLING_EDGES = 2;
    public static final int BOTH_EDGES = 3;
    public static final int BOTH_EDGES_DEFAULT_RISING = 5;
    public static final int BOTH_EDGES_DEFAULT_FALLING = 6;
    public static final int BOTH_EDGES_DEFAULT_BOTH = 6;


    private UInt8 value;

    private EdgeRpt(){
        super(DataBlock.EDGERPT);
        value = new UInt8();
    }

    public EdgeRpt(int val) throws Exception {
        this();
        value.setValue(val);
    }

    public EdgeRpt(DataBlock db, UInt8[] args) throws Exception{
        this();

        DecodeOctetStream dos = new DecodeOctetStream(args);
        value = dos.readUInt8();
    }

    public UInt8 getEdgeRpt(){
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
