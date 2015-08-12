package IEEE1451.layer0.datatypes.teds.meta;

import IEEE1451.layer0.messages.EncodeOctetStream;
import java.io.IOException;
import IEEE1451.layer0.datatypes.teds.*;
import IEEE1451.layer0.datatypes.UInt8;
import IEEE1451.layer0.messages.DecodeOctetStream;

/**
 * UUID
 * @info 8.4.2.2 (p.85)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class UUID extends DataBlock {

    private IEEE1451.layer0.datatypes.UUID uuid;


    private UUID(){
        super(DataBlock.UUID);
    }

    
    /**
     *
     * @param val HEX value without leading 0x (20 digits)
     */
    public UUID(String val) throws Exception{
        this();
        uuid = new IEEE1451.layer0.datatypes.UUID(val);
    }

    public UUID(DataBlock db, UInt8[] args) throws Exception{
        this();

        DecodeOctetStream dos = new DecodeOctetStream(args);
        String uuidString = new String();

        // convert UUID UInt8 array to String
        for (int i = 0; i < 10; i++) {
            uuidString += dos.readUInt8().toTwoDigitHex();
        }

        uuid = new IEEE1451.layer0.datatypes.UUID(uuidString);
    }

    public String getUUID(){
        return uuid.toString();
    }

    public int getLength(){
        return 10*UInt8.NUMBER_OF_OCTETS;
    }

    public UInt8[] getOctetArray(){
        UInt8[] octets;
        int i;

        try {
            EncodeOctetStream stream = new EncodeOctetStream(this);
            octets = uuid.getOctets();
            for (i=0; i<this.getLength(); i++){
                stream.addUInt8(octets[i]);
            }
//////////////////////
            if (!checkOctetArrayLength(this.getType().getValue(), stream.getSize(), getLength() + 2)){
               return null;
            }
///////////////////////
            return stream.getOctetsArray();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
