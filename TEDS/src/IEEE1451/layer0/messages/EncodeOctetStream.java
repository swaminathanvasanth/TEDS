package IEEE1451.layer0.messages;

import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import IEEE1451.layer0.datatypes.*;
import IEEE1451.layer0.datatypes.teds.*;

/**
 * Encode Octet Stream (converts data block/command/reply field values into UInt8 array)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class EncodeOctetStream {

    private ByteArrayOutputStream byte_out;
    private DataOutputStream data_out;

    public EncodeOctetStream() {
        byte_out = new ByteArrayOutputStream();
        data_out = new DataOutputStream(byte_out);
    }

    public EncodeOctetStream(DataBlock db) throws IOException, Exception {
        this();
        
        // add DataBlock type
        addUInt8(db.getType());

        // add DataBlock length
        addUInt8(new UInt8(db.getLength()));
    }

    public EncodeOctetStream(Command cmd) throws IOException, Exception{
        this();

        // add common fields of all commands
        addUInt16(new UInt16(cmd.getTransducerChannelNumber()));
        addUInt8(new UInt8(cmd.getCommandClass()));
        addUInt8(new UInt8(cmd.getCommandFunction()));
        addUInt16(new UInt16(cmd.getCommandLength()));
    }

    public EncodeOctetStream(Reply rpl) throws IOException, Exception{
        this();

        // add common fields of all replies
        addUInt8(new UInt8(rpl.getSuccessFailFlag()));
        addUInt16(new UInt16(rpl.getReplyLength()));

    }    

    public void addUInt8(UInt8 val) throws IOException{
        data_out.writeByte(val.getValue());
    }

    public void addUInt16(UInt16 val) throws IOException{
        data_out.writeShort(val.getValue());
    }

    public void addUInt32(UInt32 val) throws IOException{
        data_out.writeInt(val.getIntValue());
    }

    public void addFloat32(Float32 val) throws IOException{
        data_out.writeFloat(val.getValue());
    }

    public void addFloat64(Float64 val) throws IOException{
        data_out.writeDouble(val.getValue());
    }
    

    /**
     *
     * @return number of octets of stream
     */
    public int getSize(){
        return byte_out.toByteArray().length;
    }

    /**
     *
     * @return octet array of stream
     * @throws java.lang.Exception
     */
    public UInt8[] getOctetsArray() throws Exception{
        byte[] bytes;
        UInt8[] octets;
        bytes = byte_out.toByteArray();

        // all negative octets are converted to positive (we use getValue())
        octets = UInt8.getArray(bytes);

        return octets;
    }
}
