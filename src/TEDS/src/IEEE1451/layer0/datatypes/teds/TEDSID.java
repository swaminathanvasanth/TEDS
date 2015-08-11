package IEEE1451.layer0.datatypes.teds;

import IEEE1451.layer0.messages.EncodeOctetStream;

import java.io.IOException;

import IEEE1451.layer0.datatypes.UInt8;
import IEEE1451.layer0.messages.DecodeOctetStream;

/**
 * TEDS identification
 * @info 8.3 (p.83)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class TEDSID extends DataBlock {

    // version
    public static final int PROTOTYPE = 0;
    public static final int ORIGINAL_RELEASE = 1;
    
    // fields indexes
    private static final int FAMILY_INDEX = 0;
    private static final int CLASS_INDEX = 1;
    private static final int VERSION_INDEX = 2;
    private static final int TUPLE_LENGTH_INDEX = 3;


    private UInt8[] value;

    public TEDSID() {
        super(DataBlock.TEDSID);
        try {
            // 4 fields
            value = new UInt8[4];
            value = UInt8.initializeArray(value);
            
            // set Family = 0
            value[FAMILY_INDEX].setValue(0);

            // set Tuple Length = 1 (assumption)
            value[TUPLE_LENGTH_INDEX].setValue(1);

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public TEDSID(DataBlock db, UInt8[] args) throws Exception{
        this();

        DecodeOctetStream dos = new DecodeOctetStream(args);
        int val;

        // family
        val = dos.readUInt8().getValue();
        if (val != 0){
            throw new Exception("TEDSID: Family must be 0 (" + val + ")");
        }

        // class
        this.setClass(dos.readUInt8().getValue());

        // version
        this.setVersion(dos.readUInt8().getValue());

        // tuple length
        val = dos.readUInt8().getValue();
        if (val != 1){
            throw new Exception("TEDSID: Tuple Length must be 1 (" + val + ")");
        }       
    }

    public TEDSID (int _class, int version){
        this();
        try {
            value[CLASS_INDEX].setValue(_class);
            value[VERSION_INDEX].setValue(version);
            // value[VERSION_INDEX+1].setValue(5);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
 
	public int getFamily(){
        return value[FAMILY_INDEX].getValue();
    }

    public void setClass(int val){
        try {
            value[CLASS_INDEX].setValue(val);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public UInt8 getClassValue(){
        return value[CLASS_INDEX];
    }

    public void setVersion(int val){
        try {
            value[VERSION_INDEX].setValue(val);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public UInt8 getVersion(){
        return value[VERSION_INDEX];
    }

    public int getTupleLength(){
        return this.value[TUPLE_LENGTH_INDEX].getValue();
    }

    public int getLength(){
        return 4 * UInt8.NUMBER_OF_OCTETS;
    }

    /**
     *
     * @return byte octet array or null if error
     * @throws java.io.IOException
     */
    public UInt8[] getOctetArray(){       
        int i;

        try {
            EncodeOctetStream stream = new EncodeOctetStream(this);
            for (i=0; i<value.length; i++){
                stream.addUInt8(value[i]);
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
