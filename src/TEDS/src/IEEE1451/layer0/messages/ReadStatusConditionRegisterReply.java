package IEEE1451.layer0.messages;

import IEEE1451.layer0.datatypes.UInt32;
import IEEE1451.layer0.datatypes.UInt8;

/**
 * Read status event register reply
 * @info 7.1.1.9 (p.65)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class ReadStatusConditionRegisterReply extends Reply {

    private static final int NUM_OF_COMMAND_ARGS = 1;

    public static final int STATUS_CONDITION_REGISTER = 0;

//    TODO prepei na parei kapws ton register!!!!
    public ReadStatusConditionRegisterReply() {
        super();
        commandArgs = new Object[NUM_OF_COMMAND_ARGS];
        commandArgs[STATUS_CONDITION_REGISTER] = new UInt32();
    }

    public ReadStatusConditionRegisterReply(Reply rep, UInt8[] args) throws Exception{
        this();
        this.setSuccessFailFlag(rep.getSuccessFailFlag());
        DecodeOctetStream dos = new DecodeOctetStream(args);
        commandArgs[STATUS_CONDITION_REGISTER] = dos.readUInt32();
    }

    public void setStatusConditionRegister(UInt32 val) throws Exception{
        commandArgs[STATUS_CONDITION_REGISTER] = val;
    }

    public UInt32 getStatusConditionRegister() throws Exception{
        return (UInt32)commandArgs[STATUS_CONDITION_REGISTER];
    }

    public UInt8[] getOctetArray() {
        try {
            EncodeOctetStream stream = new EncodeOctetStream(this);
            stream.addUInt32((UInt32)commandArgs[STATUS_CONDITION_REGISTER]);

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
