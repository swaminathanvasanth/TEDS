package IEEE1451.layer0.messages;

import IEEE1451.layer0.datatypes.UInt8;
import IEEE1451.layer0.datatypes.UInt32;

/**
 * Read TransducerChannel data-set segment reply
 * @info 7.1.3.1 (p.73)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class ReadTransducerChannelDatasetSegmentReply extends Reply {

    private static final int NUM_OF_COMMAND_ARGS = 2;

    public static final long OFFSET_ERROR = 1111111111L;

    public static final int SENSOR_OFFSET = 0;
    public static final int SENSOR_DATA = 1;

    
    public ReadTransducerChannelDatasetSegmentReply() {
        super();
        commandArgs = new Object[NUM_OF_COMMAND_ARGS];
        commandArgs[SENSOR_OFFSET] = new UInt32();
        commandArgs[SENSOR_DATA] = new UInt8[0];

    }

    public ReadTransducerChannelDatasetSegmentReply(Reply rep, UInt8[] args) throws Exception{
        this();
        this.setSuccessFailFlag(rep.getSuccessFailFlag());
        DecodeOctetStream dos = new DecodeOctetStream(args);
        commandArgs[SENSOR_OFFSET] = dos.readUInt32();

        commandArgs[SENSOR_DATA] = new UInt8[args.length - UInt32.NUMBER_OF_OCTETS]; // arguments length - offset length
        for (int i = 0; i < args.length - 4; i++) {
            ((UInt8[])commandArgs[SENSOR_DATA])[i] = dos.readUInt8();

        }
    }

    public void setSensorOffset(long val) throws Exception{
        ((UInt32)commandArgs[SENSOR_OFFSET]).setValue(val);
    }

    public long getSensorOffset() throws Exception{
        return ((UInt32)commandArgs[SENSOR_OFFSET]).getValue();
    }

    public void setSensorData(UInt8[] val){
        commandArgs[SENSOR_DATA] = val;
    }

    public UInt8[] getSensorData(){
        return (UInt8[])commandArgs[SENSOR_DATA];
    }

    public UInt8[] getOctetArray() {
        try {
            EncodeOctetStream stream = new EncodeOctetStream(this);
            stream.addUInt32((UInt32)commandArgs[SENSOR_OFFSET]);

            for (int i = 0; i < ((UInt8[])commandArgs[SENSOR_DATA]).length; i++) {
                stream.addUInt8(((UInt8[])commandArgs[SENSOR_DATA])[i]);
            }

            return stream.getOctetsArray();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int getReplyLength() {
        return UInt32.NUMBER_OF_OCTETS + UInt8.NUMBER_OF_OCTETS * ((UInt8[])commandArgs[SENSOR_DATA]).length;
    }

}
