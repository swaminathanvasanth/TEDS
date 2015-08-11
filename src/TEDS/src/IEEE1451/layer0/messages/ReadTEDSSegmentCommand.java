package IEEE1451.layer0.messages;

import IEEE1451.layer0.datatypes.UInt32;
import IEEE1451.layer0.datatypes.UInt8;

/**
 * Read TEDS segment command
 * @info 7.1.1.2 (p.63)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class ReadTEDSSegmentCommand extends Command{

    private static final int NUM_OF_COMMAND_ARGS = 2;
    
    public static final int TEDS_ACCESS_CODE = 0;
    public static final int TEDS_OFFSET = 1;


    private ReadTEDSSegmentCommand(){
        super(COMMON_CMD, READ_TEDS_SEGMENT);
        commandArgs = new Object[NUM_OF_COMMAND_ARGS];
        commandArgs[TEDS_ACCESS_CODE] = new UInt8();
        commandArgs[TEDS_OFFSET] = new UInt32();
    }

    public ReadTEDSSegmentCommand(int tedsAccessCode, long tedsOffset) throws Exception {
        this();
        commandArgs[TEDS_ACCESS_CODE] = new UInt8(tedsAccessCode);
        commandArgs[TEDS_OFFSET] = new UInt32(tedsOffset);                
    }

    public ReadTEDSSegmentCommand(Command cmd, UInt8[] args) throws Exception{
        this();
        this.setTransducerChannelNumber(cmd.getTransducerChannelNumber());

        DecodeOctetStream dos = new DecodeOctetStream(args);
        commandArgs[TEDS_ACCESS_CODE] = dos.readUInt8();
        commandArgs[TEDS_OFFSET] = dos.readUInt32();
    }

    public void setTEDSAccessCode(int val) throws Exception{
        ((UInt8)commandArgs[TEDS_ACCESS_CODE]).setValue(val);
    }

    public int getTEDSAccessCode(){
        return ((UInt8)commandArgs[TEDS_ACCESS_CODE]).getValue();
    }
    
    public void setTEDSOffset(long val) throws Exception{
        ((UInt32)commandArgs[TEDS_OFFSET]).setValue(val);
    }

    public long getTEDSOffset(){
        return ((UInt32)commandArgs[TEDS_OFFSET]).getValue();
    }

    public UInt8[] getOctetArray() {
        try {
            EncodeOctetStream stream = new EncodeOctetStream(this);
            stream.addUInt8((UInt8)commandArgs[TEDS_ACCESS_CODE]);
            stream.addUInt32((UInt32)commandArgs[TEDS_OFFSET]);

            return stream.getOctetsArray();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int getCommandLength() {
        return UInt8.NUMBER_OF_OCTETS + UInt32.NUMBER_OF_OCTETS;
    }

}
