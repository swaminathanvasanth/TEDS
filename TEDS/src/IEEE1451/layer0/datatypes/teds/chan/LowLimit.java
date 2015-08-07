package IEEE1451.layer0.datatypes.teds.chan;

import IEEE1451.layer0.messages.EncodeOctetStream;
import IEEE1451.layer0.datatypes.*;
import IEEE1451.layer0.datatypes.teds.*;
import IEEE1451.layer0.messages.DecodeOctetStream;

/**
 * Design operational lower range limit
 * @info 8.5.2.18 (p.101)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class LowLimit extends DataBlock{

    public static final float DISABLED = Float32.NaN;

    private Float32 value;

    private LowLimit(){
        super(DataBlock.LOWLIMIT);
        value = new Float32();
    }

    public LowLimit(float val) throws Exception {
        this();
        value.setValue(val);
    }

    public LowLimit(DataBlock db, UInt8[] args) throws Exception{
        this();

        DecodeOctetStream dos = new DecodeOctetStream(args);
        value = dos.readFloat32();
    }

    public Float32 getLowLimit(){
        return value;
    }

    public UInt8[] getOctetArray() {
        try {
            EncodeOctetStream stream = new EncodeOctetStream(this);
            stream.addFloat32(value);
            return stream.getOctetsArray();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int getLength() {
        return Float32.NUMBER_OF_OCTETS;
    }

}
