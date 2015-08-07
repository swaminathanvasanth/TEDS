package IEEE1451.layer0.messages;

import IEEE1451.layer0.datatypes.UInt8;

/**
 * Read status condition register command
 * @info 7.1.1.9 (p.65)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class ReadStatusConditionRegisterCommand extends Command {

    private static final int NUM_OF_COMMAND_ARGS = 0;

    public ReadStatusConditionRegisterCommand() {
        super(COMMON_CMD, READ_STATUS_CONDITION_REGISTER);
        commandArgs = null;
    }

    public UInt8[] getOctetArray() {
        try {
            EncodeOctetStream stream = new EncodeOctetStream(this);

            return stream.getOctetsArray();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int getCommandLength() {
        return 0;
    }
    
}
