package IEEE1451.layer0.datatypes;

/** UInt8 datatype
 * @info 4.1 (p.10)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public final class UInt8 {
    public static final byte MIN_VALUE = 0;
    public static final short MAX_VALUE = 255;
    public static final int NUMBER_OF_OCTETS = 1;

    public static final byte BIT_0 = 0;
    public static final byte BIT_1 = 1;
    public static final byte BIT_2 = 2;
    public static final byte BIT_3 = 3;
    public static final byte BIT_4 = 4;
    public static final byte BIT_5 = 5;
    public static final byte BIT_6 = 6;
    public static final byte BIT_7 = 7;

    // bit position
    private static final byte[] BIT = { 0x01, 
                                        0x02,
                                        0x04,
                                        0x08,
                                        0x10,
                                        0x20,
                                        0x40,
                                        (byte)0x80};
      
    private byte value;

    public UInt8(){
        value = 0; // default value
    }

    public UInt8(byte val) throws Exception{
        value = val;
    }

    public UInt8(int val) throws Exception{
        if (check_range(val)){
            value = (byte)(val);
            // System.out.print(value);
        }
        else {
            throw new Exception("Out of range (" + MIN_VALUE + " - " + MAX_VALUE + ")");
        }
    }

    /**
     *
     * @param val 10-base or 16-base (0x) value
     * @throws java.lang.Exception
     */
    public UInt8(String val) throws Exception{
        int i;
        if (val.length() > 2 && val.substring(0, 2).equalsIgnoreCase("0x")){
            i = Integer.parseInt(val.substring(2, val.length()), 16); // parse 16-base
            if (check_range(i)){
                value = (byte)i;
            }
            else{
                throw new Exception("Out of range (" + MIN_VALUE + " - " + Integer.toHexString(MAX_VALUE) + ")");
            }
        }
        else{
            i = Integer.valueOf(val).intValue();
            if (check_range(i)){
                value = (byte)i;
            }
            else{
                throw new Exception("Out of range (" + MIN_VALUE + " - " + MAX_VALUE + ")");
            }            
        }
    }

    /**
     * Initialize UInt8 object array
     * @param val
     * @return
     */
    public static UInt8[] initializeArray(UInt8[] val){
        int i;
        for (i=0; i<val.length; i++){
            val[i] = new UInt8();
        }
        return val;
    }

    /**
     * Initialize UInt8 object array with initial_value
     * @param val
     * @param initial_value
     * @return
     * @throws java.lang.Exception
     */
    public static UInt8[] initializeArray(UInt8[] val, int initial_value) throws Exception{
        int i;
        for (i=0; i<val.length; i++){
            val[i] = new UInt8(initial_value);
        }
        return val;
    }

    public void setValue(int val) throws Exception{
        if (check_range(val)){
            value = (byte)val;
        }
        else {
            throw new Exception("Out of range (" + MIN_VALUE + " - " + MAX_VALUE + ")");
        }
    }

    public void setByteValue(byte val) throws Exception{
        value = (byte) ((val < 0) ? (val + MAX_VALUE + 1) : val); 
    }

    public int getValue(){
        return ((value < 0)?(value + MAX_VALUE + 1):value);
    }

    public int getByteValue(){
        return ((value < 0) ? (value + MAX_VALUE + 1) : value);
        
        // return (byte) ((value < 0) ? (value + MAX_VALUE + 1) : value);
        
        /*
         * As you see the result is an int not a byte
		How it works, say we have a byte b = -128;, this is represented as 1000 0000, 
		so what happens when you execute your line? Let's use a temp int for this, say:
	 	int i1 = (int)b; i1 is now -128 and this is actually represented in binary like this:
		
		1111 1111 1111 1111 1111 1111 1000 0000
		So what does i1 & 0xFF look like in binary?

		1111 1111 1111 1111 1111 1111 1000 0000
		&
		0000 0000 0000 0000 0000 0000 1111 1111
		which results in

		0000 0000 0000 0000 0000 0000 1000 0000
		and this is exactly 128, meaning your signed value converted to unsigned.

		Edit
		Convertint byte -128 .. 127 into 0 .. 255

		int unsignedByte = 128 + yourByte;
		You cannot represent the values 128 to 255 by using a byte, you must use something else, like an int or a smallint.
         */
        
    }

    /**
     * Return UInt8[] from byte[]
     * @param val
     * @return
     * @throws java.lang.Exception
     */
    public static UInt8[] getArray(byte[] val) throws Exception{
        UInt8[] value = new UInt8[val.length];
        int i;

        for (i=0; i<val.length; i++){
            value[i] = new UInt8(val[i]);
            // System.out.println(" - " + value[i]);
        }

        return value;
    }

    
    /**
     * Return byte[] from UInt8[]
     * @param val
     * @return
     * @throws java.lang.Exception
     */
    public static byte[] getArray(UInt8[] val) throws Exception{
        byte[] value = new byte[val.length];
        int i;

        for (i=0; i<val.length; i++){
            //value[i] = val[i].getByteValue();
        	value[i] = (byte) val[i].getValue();
        }

        return value;
    }


    /**
     * check if val equals with this UInt8
     * @param val
     * @return
     * @throws java.lang.Exception
     */
    public boolean equals(int val) throws Exception{
        if (check_range(val)){
            if (val == this.getValue()){
                   return true;
            }
            else {
                return false;
            }
        }
        else {
            throw new Exception("Out of range (" + MIN_VALUE + " - " + MAX_VALUE + ")");
        }
    }

    public String toString(){
        return String.valueOf(this.getValue());
    }

    /**
     *
     * @return Hex value of Uint8
     */
    public String toHex(){
        return Integer.toHexString(value).toString();
    }

    /**
     *
     * @return 2-digit hex value
     */
    public String toTwoDigitHex(){
        if (getValue() < 16){
            return "0" + toHex();
        }
        else{
            String s = toHex();
            return s.substring(s.length()-2, s.length());
        }
    }
    
    /**
     * Sets bit=1
     * @param pos 0(lsb)...7(msb)
     * @throws java.lang.Exception
     */
    public void setBit(int pos) throws Exception{
        if (pos < 0 || pos > 7){
            throw new Exception("Position can be [0(lsb)...7(msb)]");
        }
        value |= BIT[pos];
    }

    /**
     * Sets bit=0
     * @param pos 0(lsb)...7(msb)
     * @throws java.lang.Exception
     */
    public void resetBit(int pos) throws Exception{
        if (pos < 0 || pos > 7){
            throw new Exception("Position can be [0(lsb)...7(msb)]");
        }
        value &= ~BIT[pos];
    }    

    /**
     *
     * @param pos 0(lsb)...7(msb)
     * @return
     * @throws java.lang.Exception
     */
    public boolean getBit(int pos) throws Exception{
        if (pos < 0 || pos > 7){
            throw new Exception("Position can be [0(lsb)...7(msb)]");
        }
        return ((value & BIT[pos]) == 0)?false:true;
    }
    
    private boolean check_range(int val){
        //System.err.println(MIN_VALUE + " " + MAX_VALUE + " " + val);
        if (val < MIN_VALUE || val > MAX_VALUE)
            return false;
        else
            return true;
    }

    /**
     * Merges two UInt8 arrays
     * @param a1
     * @param a2
     * @return
     */
    public static UInt8[] mergeUInt8arrays(UInt8[] a1, UInt8[] a2){
        int i;
        int length = a1.length + a2.length;
        UInt8[] res = new UInt8[length];

        for (i=0; i<a1.length; i++){
            res[i] = a1[i];
        }

        for (i=0; i<a2.length; i++){
            res[a1.length + i] = a2[i];
        }

        return res;
    }



}
