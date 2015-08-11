package IEEE1451.layer0.messages;

import IEEE1451.layer0.datatypes.UInt32;
import IEEE1451.layer0.datatypes.UInt8;

/**
 * Read service request mask command
 * @info 7.1.1.7 (p.65)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class WriteServiceRequestMaskCommand extends Command {

    private static final int NUM_OF_COMMAND_ARGS = 1;

    public static final int SERVICE_MASK = 0;

    private WriteServiceRequestMaskCommand(){
        super(COMMON_CMD, WRITE_SERVICE_REQUEST_MASK);
        commandArgs = new Object[NUM_OF_COMMAND_ARGS];
        commandArgs[SERVICE_MASK] = new UInt32();
    }

    public WriteServiceRequestMaskCommand(UInt32 serviceMask) throws Exception {
        this();
        commandArgs[SERVICE_MASK] = serviceMask;
    }

    /**
     *
     * @param cmd
     * @param args command indepedent arguments
     * @throws java.lang.Exception
     */
    public WriteServiceRequestMaskCommand(Command cmd, UInt8[] args) throws Exception{
        this();
        this.setTransducerChannelNumber(cmd.getTransducerChannelNumber());

        DecodeOctetStream dos = new DecodeOctetStream(args);
        commandArgs[SERVICE_MASK] = dos.readUInt32();
    }

    public void setServiceMask(UInt32 val) throws Exception{
        commandArgs[SERVICE_MASK] = val;
    }

    public UInt32 getServiceMask(){
        return (UInt32)commandArgs[SERVICE_MASK];
    }

    public UInt8[] getOctetArray() {
        try {
            EncodeOctetStream stream = new EncodeOctetStream(this);
            stream.addUInt32((UInt32)commandArgs[SERVICE_MASK]);

            return stream.getOctetsArray();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int getCommandLength() {
        return UInt32.NUMBER_OF_OCTETS;
    }
    
}
