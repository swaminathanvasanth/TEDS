package IEEE1451.layer0.datatypes.teds.meta;

import IEEE1451.layer0.messages.EncodeOctetStream;
import IEEE1451.layer0.datatypes.*;
import IEEE1451.layer0.datatypes.teds.*;
import IEEE1451.layer0.messages.DecodeOctetStream;


/**
 * Number of implemented transducerchannels
 * @info 8.4.2.7 (p.88)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class MaxChan extends DataBlock {

    private UInt16 value;

    private MaxChan(){
        super(DataBlock.MAXCHAN);
        value = new UInt16();        
    }

    public MaxChan(int val) throws Exception{
        this();
        this.setMaxChan(val);
    }

    public MaxChan(DataBlock db, UInt8[] args) throws Exception{
        this();

        DecodeOctetStream dos = new DecodeOctetStream(args);
        this.setMaxChan(dos.readUInt16().getValue());
        
    }

    public void setMaxChan(int val){
        try {
            value.setValue(val);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public UInt16 getMaxChan(){
        return value;
    }

//    public void setMaxChan(UInt16 maxChan){
//        value = maxChan;
//    }


    public int getLength(){
        return UInt16.NUMBER_OF_OCTETS;
    }

    public UInt8[] getOctetArray() {
        try {
            EncodeOctetStream stream = new EncodeOctetStream(this);
            stream.addUInt16(value);
//////////////////////
            if (!checkOctetArrayLength(this.getType().getValue(), stream.getSize(), getLength() + 2)){
               return null;
            }
///////////////////////
            return stream.getOctetsArray();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    

}
