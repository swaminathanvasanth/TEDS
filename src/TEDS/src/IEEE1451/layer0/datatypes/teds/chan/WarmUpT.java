package IEEE1451.layer0.datatypes.teds.chan;

import IEEE1451.layer0.messages.EncodeOctetStream;
import IEEE1451.layer0.datatypes.*;
import IEEE1451.layer0.datatypes.teds.*;
import IEEE1451.layer0.messages.DecodeOctetStream;

/**
 * TransducerChannel warm-up time
 * @info 8.5.2.37 (p.110)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class WarmUpT extends DataBlock{

    private Float32 value;

    private WarmUpT(){
        super(DataBlock.WARMUPT);
        value = new Float32();
    }

    public WarmUpT(float val) throws Exception {
        this();
        value.setValue(val);
    }

    public WarmUpT(DataBlock db, UInt8[] args) throws Exception{
        this();

        DecodeOctetStream dos = new DecodeOctetStream(args);
        value = dos.readFloat32();
    }

    public Float32 getWarmUpT(){
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
