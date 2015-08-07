package IEEE1451.layer0.datatypes;

/** Int32 datatype
 * @info 4.3 (p.10)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public final class Int32{
    public static final int MIN_VALUE = -2147483648;
    public static final int MAX_VALUE = 2147483647;
    public static final int NUMBER_OF_OCTETS = 4;

    private int value;

    public Int32(){
        value = 0; // default value
    }

    public Int32(int val) throws Exception{
        if (check_range(val)){
            value = val;
        }
        else {
            throw new Exception("Out of range (" + MIN_VALUE + " - " + MAX_VALUE + ")");
        }
    }

    public Int32(String val) throws Exception{
        this(Integer.valueOf(val).intValue());
    }

    public void setValue(int val) throws Exception{
        if (check_range(val)){
            value = val;
        }
        else {
            throw new Exception("Out of range (" + MIN_VALUE + " - " + MAX_VALUE + ")");
        }
    }

    public int getValue(){
        return value;
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
        //System.err.println(MIN_VALUE + " " + MAX_VALUE + " " + val);
        if (val < MIN_VALUE || val > MAX_VALUE)
            return false;
        else
            return true;
    }
}
