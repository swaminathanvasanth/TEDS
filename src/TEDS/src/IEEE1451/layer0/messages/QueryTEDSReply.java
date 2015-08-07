package IEEE1451.layer0.messages;

import IEEE1451.layer0.datatypes.UInt8;
import IEEE1451.layer0.datatypes.UInt16;
import IEEE1451.layer0.datatypes.UInt32;

/**
 * Query TEDS reply
 * Command args
 *  0: TEDSAttrib (UInt8)
 *  1: TEDSStatus (UInt8)
 *  2: TEDSSize (UInt32)
 *  3: TEDSCkSum (UInt16)
 *  4: MaxTEDSSize (UInt32)
 * @info table 18 (p.62)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class QueryTEDSReply extends Reply {

    private static final int NUM_OF_COMMAND_ARGS = 5;
    
    public static final int TEDS_ATTRIB = 0;
    public static final int TEDS_STATUS = 1;
    public static final int TEDS_SIZE = 2;
    public static final int TEDS_CK_SUM = 3;
    public static final int TEDS_MAX_SIZE = 4;

    // TEDS attributes
    public static final int ATTRIB_READ_ONLY = UInt8.BIT_0;
    public static final int ATTRIB_NOT_AVAIL = UInt8.BIT_1;
    public static final int ATTRIB_INVALID = UInt8.BIT_2;
    public static final int ATTRIB_VIRTUAL = UInt8.BIT_3;
    public static final int ATTRIB_TEXT_TEDS = UInt8.BIT_4;
    public static final int ATTRIB_ADAPTIVE = UInt8.BIT_5;
    public static final int ATTRIB_MFGR_DEFINE = UInt8.BIT_6;
    
    // TEDS status
    public static final int STATUS_TOO_LARGE = UInt8.BIT_0;

    public QueryTEDSReply() {
        super();
        commandArgs = new Object[NUM_OF_COMMAND_ARGS];
        commandArgs[TEDS_ATTRIB] = new UInt8();
        commandArgs[TEDS_STATUS] = new UInt8();
        commandArgs[TEDS_SIZE] = new UInt32();
        commandArgs[TEDS_CK_SUM] = new UInt16();
        commandArgs[TEDS_MAX_SIZE] = new UInt32();
    }

    public QueryTEDSReply(Reply rep, UInt8[] args) throws Exception{
        this();
        this.setSuccessFailFlag(rep.getSuccessFailFlag());

        DecodeOctetStream dos = new DecodeOctetStream(args);
        commandArgs[TEDS_ATTRIB] = dos.readUInt8();
        commandArgs[TEDS_STATUS] = dos.readUInt8();
        commandArgs[TEDS_SIZE] = dos.readUInt32();
        commandArgs[TEDS_CK_SUM] = dos.readUInt16();
        commandArgs[TEDS_MAX_SIZE] = dos.readUInt32();
    }

    public void setTEDSAttribute(int attrib) throws Exception{
        ((UInt8)commandArgs[TEDS_ATTRIB]).setBit(attrib);
    }

    public void clearTEDSAttribute(int attrib) throws Exception{
        ((UInt8)commandArgs[TEDS_ATTRIB]).resetBit(attrib);
    }

    public boolean getTEDSAttribute(int attrib) throws Exception{
        return ((UInt8)commandArgs[TEDS_ATTRIB]).getBit(attrib);
    }

    public void setTEDSStatus(int attrib) throws Exception{
        ((UInt8)commandArgs[TEDS_STATUS]).setBit(attrib);
    }

    public void clearTEDSStatus(int attrib) throws Exception{
        ((UInt8)commandArgs[TEDS_STATUS]).resetBit(attrib);
    }

    public boolean getTEDSStatus(int attrib) throws Exception{
        return ((UInt8)commandArgs[TEDS_STATUS]).getBit(attrib);
    }

    public void setTEDSSize(long val) throws Exception{
        ((UInt32)commandArgs[TEDS_SIZE]).setValue(val);
    }

    public long getTEDSSize() throws Exception{
        return ((UInt32)commandArgs[TEDS_SIZE]).getValue();
    }

    public void setTEDSCkSum(int val) throws Exception{
        ((UInt16)commandArgs[TEDS_CK_SUM]).setValue(val);
    }

    public int getTEDSCkSum() throws Exception{
        return ((UInt16)commandArgs[TEDS_CK_SUM]).getValue();
    }

    public void setTEDSMaxSize(long val) throws Exception{
        ((UInt32)commandArgs[TEDS_MAX_SIZE]).setValue(val);
    }

    public long getTEDSMaxSize() throws Exception{
        return ((UInt32)commandArgs[TEDS_MAX_SIZE]).getValue();
    }

    public UInt8[] getOctetArray() {
        try {
            EncodeOctetStream stream = new EncodeOctetStream(this);
            stream.addUInt8((UInt8)commandArgs[TEDS_ATTRIB]);
            stream.addUInt8((UInt8)commandArgs[TEDS_STATUS]);
            stream.addUInt32((UInt32)commandArgs[TEDS_SIZE]);
            stream.addUInt16((UInt16)commandArgs[TEDS_CK_SUM]);
            stream.addUInt32((UInt32)commandArgs[TEDS_MAX_SIZE]);

            return stream.getOctetsArray();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int getReplyLength() {
        return 2 * UInt8.NUMBER_OF_OCTETS + UInt16.NUMBER_OF_OCTETS + 2 * UInt32.NUMBER_OF_OCTETS;
    }

}

