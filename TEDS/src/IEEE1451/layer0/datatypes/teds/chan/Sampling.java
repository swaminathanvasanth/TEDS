package IEEE1451.layer0.datatypes.teds.chan;

import IEEE1451.layer0.messages.EncodeOctetStream;
import IEEE1451.layer0.datatypes.*;
import IEEE1451.layer0.datatypes.teds.*;
import IEEE1451.layer0.messages.DecodeOctetStream;

/**
 * Sample
 * @info 8.5.2.44 (p.113)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class Sampling extends DataBlock{

    public static final int SAMPMODE = 48;
    public static final int SDEFAULT = 49;

    // sampling mode
    public static final byte TRIGGER = UInt8.BIT_0;
    public static final byte FREE_RUNNING_WITHOUT_PRE_TRIGGER = UInt8.BIT_1;
    public static final byte FREE_RUNNING_WITH_PRE_TRIGGER = UInt8.BIT_2;
    public static final byte CONTINUOUS_SAMPLING = UInt8.BIT_3;
    public static final byte IMMEDIATE_SAMPLING = UInt8.BIT_4;

    private UInt8 sampMode, defSampMode;

    public Sampling(){
        super(DataBlock.SAMPLING);
        sampMode = new UInt8();
        defSampMode = new UInt8();
    }

    public Sampling(DataBlock db, UInt8[] args) throws Exception{
        this();

        DecodeOctetStream dos = new DecodeOctetStream(args);
        sampMode = dos.readUInt8();
        defSampMode = dos.readUInt8();
    }

    public boolean getSamplingMode(int mode) throws Exception{
        return sampMode.getBit(mode);
    }

    public UInt8 getSamplingMode(){
        return sampMode;
    }

    public void setSamplingMode(int mode) throws Exception{
        sampMode.setBit(mode);
    }

    public boolean getDefaultSamplingMode(int mode) throws Exception{
        return defSampMode.getBit(mode);
    }

    public UInt8 getDefaultSamplingMode(){
        return defSampMode;
    }

    public void setDefaultSamplingMode(int mode) throws Exception{
        defSampMode.setBit(mode);
    }

    public UInt8[] getOctetArray() {
        try {
            EncodeOctetStream stream = new EncodeOctetStream(this);
            stream.addUInt8(sampMode);
            stream.addUInt8(defSampMode);
            return stream.getOctetsArray();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int getLength() {
        return 2 * UInt8.NUMBER_OF_OCTETS;
    }

}
