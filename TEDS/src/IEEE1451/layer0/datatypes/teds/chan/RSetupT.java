package IEEE1451.layer0.datatypes.teds.chan;

import IEEE1451.layer0.messages.EncodeOctetStream;
import IEEE1451.layer0.datatypes.*;
import IEEE1451.layer0.datatypes.teds.*;
import IEEE1451.layer0.messages.DecodeOctetStream;

/**
 * TransducerChannel read setup time (sensor)
 * @info 8.5.2.35 (p.109)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class RSetupT extends DataBlock{

    private Float32 value;

    private RSetupT(){
        super(DataBlock.RSETUPT);
        value = new Float32();
    }

    public RSetupT(float val) throws Exception {
        this();
        value.setValue(val);
    }

    public RSetupT(DataBlock db, UInt8[] args) throws Exception{
        this();

        DecodeOctetStream dos = new DecodeOctetStream(args);
        value = dos.readFloat32();
    }

    public Float32 getRSetupT(){
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
