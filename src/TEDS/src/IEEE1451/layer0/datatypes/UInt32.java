package IEEE1451.layer0.datatypes;

/** UInt32 datatype
 * @info 4.4 (p.10)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public final class UInt32{
    public static final byte MIN_VALUE = 0;
    public static final long MAX_VALUE = 4294967295L;
    public static final int NUMBER_OF_OCTETS = 4;

    private int value;

    public UInt32() {
        value = 0;
    }

    public UInt32(long val) throws Exception{
        if (check_range(val)){
            value = (int)val;
        }
        else {
            throw new Exception("Out of range (" + MIN_VALUE + " - " + MAX_VALUE + ")");
        }
    }

    public UInt32(String val) throws Exception{
        this(Long.parseLong(val));
    }

    public void setValue(long val) throws Exception{
        if (check_range(val)){
            value = (int)val;
        }
        else {
            throw new Exception("Out of range (" + MIN_VALUE + " - " + MAX_VALUE + ")");
        }
    }

    public void setIntValue(int val) throws Exception{
        value = (int) ((val < 0) ? (val + MAX_VALUE + 1) : val);
    }

    public long getValue(){
        return (value < 0) ? (value + MAX_VALUE + 1) : value;
    }

    public int getIntValue(){
        return (int) ((value < 0) ? (value + MAX_VALUE + 1) : value);
    }

    
    public boolean equals(long val) throws Exception{
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

    private boolean check_range(long val){
        if (val < MIN_VALUE || val > MAX_VALUE)
            return false;
        else
            return true;
    }
}
