package IEEE1451.layer0.datatypes.teds;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import IEEE.TEDS.DATABASE.TEDSData;
import IEEE1451.layer0.datatypes.UInt16;
import IEEE1451.layer0.datatypes.UInt32;
import IEEE1451.layer0.datatypes.UInt8;
import IEEE1451.layer0.messages.EncodeOctetStream;

/**
 * TEDS (Transducer Electronic Datasheet)
 * Structure: length-dataBlock-checksum
 * Length: number of octets of dataBlock and checksum
 * Checksum: 1's complement of sum of octets of dataBlock and length
 * @info 8.1 (p.81)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */

public class TEDS {

	private UInt32 length = new UInt32();
    private Hashtable<Integer, DataBlock> datablock = new Hashtable<Integer, DataBlock>();

    public TEDS() {
        // checksum length
        addLength(UInt16.NUMBER_OF_OCTETS);
    }

    /**
     * adds datablock to TEDS
     * @todo check if TEDS from different class (TEDSID first???)
     * @param db
     */
    public void addDataBlock(DataBlock db){
        // add datablock to hashtable (with key its field type)
        datablock.put(new Integer(db.getType().getValue()), db);
        
        // datablock sum length = value length + 2 octets (type+length length)
        addLength(db.getLength() + 2);
    }

    /**
     *
     * @param type field
     * @return DataBlock of this type
     */
    public DataBlock getDataBlock(UInt8 type){
        return (DataBlock) datablock.get(new Integer(type.getValue()));
    }

    /**
     *
     * @return DataBlock count of TEDS
     */
    public int getDataBlockCount(){
        return datablock.size();
    }

    public DataBlock[] getAllDataBlocks(){
        int i = 0;
        DataBlock[] db = new DataBlock[getDataBlockCount()];
        Enumeration<DataBlock> e = datablock.elements();

        while (e.hasMoreElements()){
            db[i++] = (DataBlock) e.nextElement();
        }

        return db;
    }

    /**
     * increase length of TEDS
     * @param val length that will be added
     */
    private void addLength(int val){
        try {
            length.setIntValue(length.getIntValue() + val);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     * @return TEDS octet count
     */
    public int getLength(){
        return length.getIntValue();
    }

    /**
     * sum of length octets
     * @return length octet sum or null
     */
    private int getLengthSum(){
        EncodeOctetStream stream = new EncodeOctetStream();
        UInt8[] octets;
        int i, sum = 0;

        try {
            stream.addUInt32(length);
            octets = stream.getOctetsArray();
            for (i=0; i<octets.length; i++){
                sum += octets[i].getValue();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return sum;
    }

    /**
     *
     * @return TEDS checksum or null
     */
    public UInt16 getCheckSum(){
        DataBlock[] db = getAllDataBlocks();
        int i, sum;

        try {
            sum = getLengthSum();

            for (i=0; i<db.length; i++){
                // sum of each datablock
                sum += db[i].getSum();
            }

            // checksum 0xFFFF - sum
            return new UInt16(UInt16.MAX_VALUE - sum);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public UInt8[] getOctetArray(){
        try {
            UInt8[] res;
            int i;
            EncodeOctetStream lengthStream = new EncodeOctetStream();
            EncodeOctetStream checksumStream = new EncodeOctetStream();

            // add length octets
            lengthStream.addUInt32(new UInt32(getLength()));
            res = lengthStream.getOctetsArray();

            // add datablocks octets
            DataBlock[] db = getAllDataBlocks();
            for (i=0; i<getDataBlockCount(); i++){
                res = UInt8.mergeUInt8arrays(res, db[i].getOctetArray());
            }

            // add checksum octets
            checksumStream.addUInt16(getCheckSum());
            res = UInt8.mergeUInt8arrays(res, checksumStream.getOctetsArray());

            return res;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    /**
     * Decodes file contains TEDS to octet array
     * @param s comma delimited octet values
     * @return octet array
     * @throws java.lang.Exception
     */
    public static UInt8[] decodeTEDS(String s) throws Exception{
        int start = -1, end, i, j = 0;
        Vector vec = new Vector();

        for (i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ','){
                end = i;
                // add to vector the octet
                vec.addElement(s.substring(start + 1, end));
                start = end;
            }
        }

        vec.addElement(s.substring(start + 1, i));
        UInt8[] octets = new UInt8[vec.size()];
        Enumeration e = vec.elements();

        // store octets to UInt8 array
        while(e.hasMoreElements()){
            //octets[j++] = new UInt8(Byte.parseByte((String)e.nextElement()));
            octets[j++] = new UInt8(Integer.parseInt((String)e.nextElement()));
        }
        
        return octets;
    }


    /**
     * Encodes TEDS to string for file storing
     * @param octets
     * @return comma delimited octet values
     */
    public static String encodeTEDS(UInt8[] octets){
        String value = new String();
        
        for (int i = 0; i < octets.length; i++) {
             	
        	value += String.valueOf(octets[i].getByteValue()) + ",";
        	
        	// System.out.println("After Encoding : " +octets[i].getValue());
        	 
        	 
        }
        value = value.substring(0, value.length()-1);

        TEDSData.setBinaryTEDS(value);
        return value;
    }

}
