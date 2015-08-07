package IEEE1451.layer0.messages;

import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import IEEE1451.layer0.datatypes.*;
import IEEE1451.layer0.datatypes.teds.DataBlock;
import IEEE1451.layer0.datatypes.teds.TEDS;
import java.util.Hashtable;


/**
 * Decode Octet Stream (converts UInt8/byte array into TEDS/command/reply)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class DecodeOctetStream {

    private ByteArrayInputStream byte_in;
    private DataInputStream data_in;

    private int length;
    private UInt8[] args;
    private UInt8[] octetArray;
    private int[] dbType;
    private int[] dbLength;
    private UInt8[] dbValue;
    private int k = -1;
    private Hashtable octetArrays;

    public DecodeOctetStream(UInt8[] bytes) throws Exception{
        byte_in = new ByteArrayInputStream(UInt8.getArray(bytes));
        data_in = new DataInputStream(byte_in);
        octetArray = bytes;
    }

    public DecodeOctetStream(byte[] bytes) throws Exception{
        byte_in = new ByteArrayInputStream(bytes);
        data_in = new DataInputStream(byte_in);
        octetArray = UInt8.getArray(bytes);
    }

    /**
     *
     * @return decoded Command
     * @throws java.io.IOException
     * @throws java.lang.Exception
     */
    public Command getCommand() throws IOException, Exception{
        Command cmd = new Command() {

            public UInt8[] getOctetArray() {
                return octetArray;
            }

            public int getCommandLength() {
                return length;
            }
        };

        // construct command
        cmd.setTransducerChannelNumber(readUInt16().getValue());
        cmd.setCommandClass(readUInt8().getValue());
        cmd.setCommandFunction(readUInt8().getValue());
        length = readUInt16().getValue();

        if (length != octetArray.length - 6){   // 6 commmon cmd octets
            throw new Exception("Command length mismatch (" + length + " - " + (octetArray.length - 6) + ")");
        }

        // if command has arguments
        if (length > 0){
            args = new UInt8[length];
            for (int i = 0; i < length; i++) {
                args[i] = new UInt8(readUInt8().getByteValue());
            }
        }

        return cmd;
    }

    /**
     *
     * @return decoded Reply
     * @throws java.io.IOException
     * @throws java.lang.Exception
     */
    public Reply getReply() throws IOException, Exception{
        Reply rep = new Reply() {

            public UInt8[] getOctetArray() {
                return octetArray;
            }

            public int getReplyLength() {
                return length;
            }
        };

        // construct reply
        rep.setSuccessFailFlag(readUInt8().getValue());
        length = readUInt16().getValue();

        if (length != octetArray.length - 3){   // 3 commmon rep octets
            throw new Exception("Reply length mismatch (" + length + " - " + (octetArray.length - 3) + ")");
        }

        // if reply has arguments
        if (length > 0){
            args = new UInt8[length];
            for (int i = 0; i < length; i++) {
                args[i] = new UInt8(readUInt8().getByteValue());
            }
        }

        return rep;
    }

    /**
     *
     * @return decoded TEDS
     * @throws java.io.IOException
     * @throws java.lang.Exception
     */
    public TEDS getTeds() throws IOException, Exception{
        TEDS teds = new TEDS();
        int counter = 0;
        octetArrays = new Hashtable();

        dbType = new int[UInt8.MAX_VALUE];
        dbLength = new int[UInt8.MAX_VALUE];

        length = readUInt32().getIntValue();

        if (length != (octetArray.length - 4)){ // length's length
            throw new Exception("TEDS length mismatch (" + length + " - " + octetArray.length + ")");
        }

        // construct TEDS
        UInt8[] chsum = new UInt8[2];

        chsum[0] = octetArray[octetArray.length - 2];
        chsum[1] = octetArray[octetArray.length - 1];

        DecodeOctetStream d = new DecodeOctetStream(chsum);

        int checkSum = d.readUInt16().getValue();

        int sum = 0;
        for (int i = 0; i < octetArray.length - 2; i++) {
            sum += octetArray[i].getValue();
        }
        
        int check_sum = UInt16.MAX_VALUE - sum;

        // check checksum
        if (checkSum != check_sum){
            throw new Exception("Checksum mismatch (" + checkSum + " - " + check_sum + ")");
        }

        DataBlock db;

        while (counter < (length - 2)){ // length - checksum length

            // construct DataBlock (TLV)
            // type
            dbType[++k] = readUInt8().getValue();
            counter++;
            // length
            dbLength[dbType[k]] = readUInt8().getValue();
            counter++;
            dbValue = new UInt8[dbLength[dbType[k]] + 2];
            dbValue[0] = new UInt8(dbType[k]);
            dbValue[1] = new UInt8(dbLength[dbType[k]]);

            for (int i = 2; i < dbLength[dbType[k]] + 2; i++) {
                dbValue[i] = readUInt8();
                counter++;
            }

            // store DataBlock octets
            octetArrays.put(new Integer(dbType[k]), dbValue);

            // stote DataBlock
            db = new DataBlock(dbType[k]) {

                public UInt8[] getOctetArray() {
                    try {
                        return (UInt8[]) octetArrays.get(new Integer(this.getType().getValue()));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        return null;
                    }
                }

                public int getLength() {
                    return dbLength[this.getType().getValue()];
                }
            };
            teds.addDataBlock(db);
        }

        return teds;
    }

    /**
     *
     * @param type
     * @return DataBlock arguments of decoded TEDS
     */
    public UInt8[] getDataBlockArgs(int type){
        try {
            UInt8[] a = (UInt8[]) octetArrays.get(new Integer(type));
            UInt8[] arg = new UInt8[a.length - 2];
            // TL is 2 octets, V is after them
            System.arraycopy(a, 2, arg, 0, arg.length);
            return arg;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    /**
     *
     * @return Command/Reply arguments
     */
    public UInt8[] getArgs(){
        if (length > 0)
            return args;
        else
            return null;
    }

    public UInt8 readUInt8() throws IOException, Exception{
        return new UInt8(data_in.readUnsignedByte());
    }

    public UInt16 readUInt16() throws IOException, Exception{
        return new UInt16(data_in.readUnsignedShort());
    }

    public UInt32 readUInt32() throws IOException, Exception{
        return new UInt32(data_in.readInt());
    }

    public Float32 readFloat32() throws IOException, Exception{
        return new Float32(data_in.readFloat());
    }

    public Float64 readFloat64() throws IOException, Exception{
        return new Float64(data_in.readDouble());
    }

}
