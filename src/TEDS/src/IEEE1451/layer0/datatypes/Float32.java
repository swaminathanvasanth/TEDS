package IEEE1451.layer0.datatypes;

/** Float32 datatype
 * @info 4.5 (p.11)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */

/* According to IEEE Std 754-1985, a single precision number with an exponent of 255 and a fractional
portion (mantissa) that is nonzero is NaN regardless of the sign bit. The recommended value for use in a
TEDS field for NaN is 0x7FFFFFFF (hex). */

public final class Float32{
    public static final float MIN_VALUE = Float.MIN_VALUE;
    public static final float MAX_VALUE = Float.MAX_VALUE;
    public static final float NaN = 0x7FFFFFFF; // not a number
    public static final int NUMBER_OF_OCTETS = 4;

    private float value;

    public Float32(){
        value = 0; // default value
    }

    public Float32(float val) throws Exception{
        if (check_range(val)){
            value = val;
        }
        else {
            throw new Exception("Out of range (" + MIN_VALUE + " - " + MAX_VALUE + ")");
        }
    }

    public Float32(String val) throws Exception{
        this(Float.parseFloat(val));
    }

    public void setValue(float val) throws Exception{
        if (check_range(val)){
            value = val;
        }
        else {
            throw new Exception("Out of range (" + MIN_VALUE + " - " + MAX_VALUE + ")");
        }
    }

    public float getValue(){
        return value;
    }

    public boolean equals(float val) throws Exception{
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
        return (this.getValue()==NaN)?"Nan":String.valueOf(this.getValue());
    }

    private boolean check_range(float val){
        if (val < MIN_VALUE || val > MAX_VALUE)
            return false;
        else
            return true;
    }
}
