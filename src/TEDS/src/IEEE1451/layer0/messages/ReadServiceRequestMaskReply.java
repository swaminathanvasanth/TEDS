package IEEE1451.layer0.messages;

import IEEE1451.layer0.datatypes.UInt32;
import IEEE1451.layer0.datatypes.UInt8;

/**
 * Read status event register reply
 * @info 7.1.1.9 (p.65)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class ReadServiceRequestMaskReply extends Reply {

    private static final int NUM_OF_COMMAND_ARGS = 1;

    public static final int SERVICE_REQUEST_MASK = 0;

//    TODO prepei na parei kapws ton register!!!!
    public ReadServiceRequestMaskReply() {
        super();
        commandArgs = new Object[NUM_OF_COMMAND_ARGS];
        commandArgs[SERVICE_REQUEST_MASK] = new UInt32();
    }

    public ReadServiceRequestMaskReply(Reply rep, UInt8[] args) throws Exception{
        this();
        this.setSuccessFailFlag(rep.getSuccessFailFlag());
        DecodeOctetStream dos = new DecodeOctetStream(args);
        commandArgs[SERVICE_REQUEST_MASK] = dos.readUInt32();
    }

    public void setStatusConditionRegister(UInt32 val) throws Exception{
        commandArgs[SERVICE_REQUEST_MASK] = val;
    }

    public UInt32 getStatusConditionRegister() throws Exception{
        return (UInt32)commandArgs[SERVICE_REQUEST_MASK];
    }

    public UInt8[] getOctetArray() {
        try {
            EncodeOctetStream stream = new EncodeOctetStream(this);
            stream.addUInt32((UInt32)commandArgs[SERVICE_REQUEST_MASK]);

            return stream.getOctetsArray();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int getReplyLength() {
        return UInt32.NUMBER_OF_OCTETS;
    }

}
