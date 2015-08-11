package IEEE1451.layer0.messages;

import IEEE1451.layer0.datatypes.UInt8;

/**
 * Query TEDS command
 * Command args
 *  0: TEDSAccessCode (UInt8)
 * @info 7.1.1.1 (p.61)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class QueryTEDSCommand extends Command{

    private static final int NUM_OF_COMMAND_ARGS = 1;

    public static final int TEDS_ACCESS_CODE = 0;


    private QueryTEDSCommand() {
        super(COMMON_CMD, QUERY_TEDS);
        commandArgs = new Object[NUM_OF_COMMAND_ARGS];
        commandArgs[TEDS_ACCESS_CODE] = new UInt8();
    }

    /**
     * @param val TEDS access code
     */
    public QueryTEDSCommand(int tedsAccessCode) throws Exception {
        this();
        this.setTEDSAccessCode(tedsAccessCode);
    }

    public QueryTEDSCommand(Command cmd, UInt8[] args) throws Exception{
        this();
        this.setTransducerChannelNumber(cmd.getTransducerChannelNumber());

        DecodeOctetStream dos = new DecodeOctetStream(args);
        commandArgs[TEDS_ACCESS_CODE] = dos.readUInt8();
    }

    public void setTEDSAccessCode(int val) throws Exception{
        ((UInt8)commandArgs[TEDS_ACCESS_CODE]).setValue(val);
    }

    public int getTEDSAccessCode(){
        return ((UInt8)commandArgs[TEDS_ACCESS_CODE]).getValue();
    }

    public UInt8[] getOctetArray() {
        try {
            EncodeOctetStream stream = new EncodeOctetStream(this);
            stream.addUInt8((UInt8)commandArgs[TEDS_ACCESS_CODE]);

            return stream.getOctetsArray();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int getCommandLength(){
        return UInt8.NUMBER_OF_OCTETS;
    }

}
