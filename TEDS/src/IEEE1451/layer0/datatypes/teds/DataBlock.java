package IEEE1451.layer0.datatypes.teds;

import IEEE1451.layer0.datatypes.UInt8;

/**
 * TEDS Data block
 * TLV structure: Type-Length-Value
 * Length: number of value octets (assumption: octet_length=1 (it is reguralry defined in TEDSID))
 * @info 8.1.2 (p.81)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public abstract class DataBlock {

    // data block types
    public static final int TEDSID = 3;
    public static final int DESCRIPTION = 128;  // user-defined

    // meta fields types
    public static final int UUID = 4;
    public static final int OHOLDOFF = 10;
    public static final int TESTTIME = 12;
    public static final int MAXCHAN = 13;

    // chan fields types
    public static final int CALKEY = 10;
    public static final int CHANTYPE = 11;
    public static final int PHYUNITS = 12;
    public static final int LOWLIMIT = 13;
    public static final int HILIMIT = 14;
    public static final int OERROR = 15;
    public static final int SELFTEST = 16;
    public static final int SAMPLE = 18;
    public static final int UPDATET = 20;
    public static final int WSETUPT = 21;
    public static final int RSETUPT = 22;
    public static final int SPERIOD = 23;
    public static final int WARMUPT = 24;
    public static final int RDELAYT = 25;
    public static final int TESTTIME_TRANS = 26;
    public static final int SAMPLING = 31;
    public static final int EDGERPT = 35;
    public static final int ACTHALT = 36;
    public static final int ESOPTION = 39;


    protected UInt8 type;


    private DataBlock() {
        type = new UInt8();
    }

    public DataBlock(int type){
        this();
        setType(type);
    }

    public void setType(int val){
        try {
            type.setValue(val);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setType(UInt8 val){
        type = val;
    }

    public UInt8 getType(){
        return type;
    }

    /**
     *
     * @return sum of all bata block octets
     */
    public int getSum(){
        UInt8[] octets;
        int i, sum = 0;
        try {
            octets = getOctetArray();
            for (i=0; i<octets.length; i++){
                sum += octets[i].getValue();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sum;
    }

    /**
     *
     * @return UInt8 array of all fields (type, length, value) or null
     */
    abstract public UInt8[] getOctetArray();

    /**
     *
     * @return the number of octets of value
     */
    abstract public int getLength();


    protected boolean checkOctetArrayLength(int type, int arrayLength, int length){
        if (arrayLength != length){
            System.err.println("Type: " + type + " octet array length wrong!!!");
            return false;
        }
        return true;
    }

}
