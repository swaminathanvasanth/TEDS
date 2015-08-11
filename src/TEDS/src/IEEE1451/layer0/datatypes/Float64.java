package IEEE1451.layer0.datatypes;

/** Float64 datatype
 * @info 4.6 (p.11)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */

/* According to IEEE Std 754-1985, a single precision number with an exponent of 255 and a fractional
portion (mantissa) that is nonzero is NaN regardless of the sign bit. The recommended value for use in a
TEDS field for NaN is 0x7FFFFFFF (hex). */

public final class Float64{
    public static final double MIN_VALUE = Double.MIN_VALUE;
    public static final double MAX_VALUE = Double.MAX_VALUE;
    public static final int NUMBER_OF_OCTETS = 8;

    private double value;

    public Float64(){
        value = 0; // default value
    }

    public Float64(double val) throws Exception{
        if (check_range(val)){
            value = val;
        }
        else {
            throw new Exception("Out of range (" + MIN_VALUE + " - " + MAX_VALUE + ")");
        }
    }

    public Float64(String val) throws Exception{
        this(Double.parseDouble(val));
    }

    public void setValue(double val) throws Exception{
        if (check_range(val)){
            value = val;
        }
        else {
            throw new Exception("Out of range (" + MIN_VALUE + " - " + MAX_VALUE + ")");
        }
    }

    public double getValue(){
        return value;
    }

    public boolean equals(double val) throws Exception{
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

    private boolean check_range(double val){
        if (val < MIN_VALUE || val > MAX_VALUE)
            return false;
        else
            return true;
    }
}
