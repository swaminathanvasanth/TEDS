package IEEE1451.layer0.messages;

import IEEE1451.layer0.datatypes.UInt8;
import IEEE1451.layer0.datatypes.UInt32;

/**
 * Read TEDS segment reply
 * @info 7.1.1.2 (p.63)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class ReadTEDSSegmentReply extends Reply {

    private static final int NUM_OF_COMMAND_ARGS = 2;

    // set to offset if error
    public static final int ERROR_OFFSET = 1111111111;

    public static final int TEDS_OFFSET = 0;
    public static final int RAW_TEDS_BLOCK = 1;

    
    public ReadTEDSSegmentReply() {
        super();
        commandArgs = new Object[NUM_OF_COMMAND_ARGS];
        commandArgs[TEDS_OFFSET] = new UInt32();
        commandArgs[RAW_TEDS_BLOCK] = new UInt8[0];

    }

    public ReadTEDSSegmentReply(Reply rep, UInt8[] args) throws Exception{
        this();
        this.setSuccessFailFlag(rep.getSuccessFailFlag());
        DecodeOctetStream dos = new DecodeOctetStream(args);
        commandArgs[TEDS_OFFSET] = dos.readUInt32();

        commandArgs[RAW_TEDS_BLOCK] = new UInt8[args.length - UInt32.NUMBER_OF_OCTETS]; // arguments length - offset length
        for (int i = 0; i < args.length - 4; i++) {
            ((UInt8[])commandArgs[RAW_TEDS_BLOCK])[i] = dos.readUInt8();

        }
    }

    public void setTEDSOffset(long val) throws Exception{
        ((UInt32)commandArgs[TEDS_OFFSET]).setValue(val);
    }

    public long getTEDSOffset() throws Exception{
        return ((UInt32)commandArgs[TEDS_OFFSET]).getValue();
    }

    public void setRawTEDSBlock(UInt8[] val){
        commandArgs[RAW_TEDS_BLOCK] = val;
    }

    public UInt8[] getRawTEDSBlock(){
        return (UInt8[])commandArgs[RAW_TEDS_BLOCK];
    }

    public UInt8[] getOctetArray() {
        try {
            EncodeOctetStream stream = new EncodeOctetStream(this);
            stream.addUInt32((UInt32)commandArgs[TEDS_OFFSET]);

            for (int i = 0; i < ((UInt8[])commandArgs[RAW_TEDS_BLOCK]).length; i++) {
                stream.addUInt8(((UInt8[])commandArgs[RAW_TEDS_BLOCK])[i]);
            }

            return stream.getOctetsArray();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int getReplyLength() {
        // TODO TEDS BLOCK!!!!
        return UInt32.NUMBER_OF_OCTETS + UInt8.NUMBER_OF_OCTETS * ((UInt8[])commandArgs[RAW_TEDS_BLOCK]).length;

    }

}
