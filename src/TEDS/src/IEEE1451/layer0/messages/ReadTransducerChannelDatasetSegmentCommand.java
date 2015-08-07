package IEEE1451.layer0.messages;

import IEEE1451.layer0.datatypes.UInt8;
import IEEE1451.layer0.datatypes.UInt32;

/**
 * Read TransducerChannel data-set segment command
 * Command args
 *  0: DataSetOffset (UInt32)
 * @info 7.1.3.1 (p.72)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class ReadTransducerChannelDatasetSegmentCommand extends Command{

    private static final int NUM_OF_COMMAND_ARGS = 1;

    public static final int DATA_SET_OFFSET = 0;


    private ReadTransducerChannelDatasetSegmentCommand() {
        super(XDCR_OPERATE, READ_TRANSDUCER_CHANNEL_DATA_SET_SEGMENT);
        commandArgs = new Object[NUM_OF_COMMAND_ARGS];
        commandArgs[DATA_SET_OFFSET] = new UInt32();
    }

    public ReadTransducerChannelDatasetSegmentCommand(int datasetOffset) throws Exception {
        this();
        this.setDatasetOffset(datasetOffset);
    }

    public ReadTransducerChannelDatasetSegmentCommand(Command cmd, UInt8[] args) throws Exception{
        this();
        this.setTransducerChannelNumber(cmd.getTransducerChannelNumber());

        DecodeOctetStream dos = new DecodeOctetStream(args);
        commandArgs[DATA_SET_OFFSET] = dos.readUInt32();
    }

    public void setDatasetOffset(long val) throws Exception{
        ((UInt32)commandArgs[DATA_SET_OFFSET]).setValue(val);
    }

    public long getDatasetOffset(){
        return ((UInt32)commandArgs[DATA_SET_OFFSET]).getValue();
    }

    public UInt8[] getOctetArray() {
        try {
            EncodeOctetStream stream = new EncodeOctetStream(this);
            stream.addUInt32((UInt32)commandArgs[DATA_SET_OFFSET]);

            return stream.getOctetsArray();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int getCommandLength(){
        return UInt32.NUMBER_OF_OCTETS;
    }

}
