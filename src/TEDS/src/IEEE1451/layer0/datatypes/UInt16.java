package IEEE1451.layer0.datatypes;

/** UInt16 datatype
 * @info 4.2 (p.10)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public final class UInt16{
    public static final byte MIN_VALUE = 0;
    public static final int MAX_VALUE = 65535;
    public static final int NUMBER_OF_OCTETS = 2;

    private short value;

    public UInt16(){
        value = 0; // default value
    }

    public UInt16(int val) throws Exception{
        if (check_range(val)){
            value = (short)val;
        }
        else {
            throw new Exception("Out of range (" + MIN_VALUE + " - " + MAX_VALUE + ")");
        }
    }

    public UInt16(String val) throws Exception{
        this(Integer.valueOf(val).intValue());
    }

    public void setValue(int val) throws Exception{
        if (check_range(val)){
            value = (short)val;
        }
        else {
            throw new Exception("Out of range (" + MIN_VALUE + " - " + MAX_VALUE + ")");
        }
    }

    public void setShortValue(short val) throws Exception{
        value = (short) ((val < 0) ? (val + MAX_VALUE + 1) : val);
    }

    public int getValue(){
        return (value < 0)?(value + MAX_VALUE + 1):value;
    }

    public short getShortValue(){
        return (short) ((value < 0) ? (value + MAX_VALUE + 1) : value);
    }

    
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

    private boolean check_range(int val){
        if (val < MIN_VALUE || val > MAX_VALUE)
            return false;
        else
            return true;
    }
}
