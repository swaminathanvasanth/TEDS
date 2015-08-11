package IEEE1451.layer0.datatypes;

/**
 * Time representation
 * @info 4.9 (p.12)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class TimeRepresentation{
    public static final int NANO_SECS_MIN = 0;
    public static final int NANO_SECS_MAX = 999999999;
    public static final int PLUS_SIGN = 0;
    public static final int MINUS_SIGN = 1;
    private static final int SIGN_BIT = 0x80000000;
    private static final int SIGN_BIT_NOT = 0x7FFFFFFF;

    private UInt32 secs;
    private UInt32 nsecs; // MSB sign, others value (nsecs)

    public TimeRepresentation(){
        secs = new UInt32();
        nsecs = new UInt32();
    }

    public TimeRepresentation(int sign, int secs, int nsecs) throws Exception{
        this();
        setSign(sign);
        setSecs(secs);
        setNanoSecs(nsecs);
    }

    /**
     * sets plus sign to nsecs field
     * plus is "0"
     */
    public void setPlusSign(){
        try {
            nsecs.setIntValue(nsecs.getIntValue() & SIGN_BIT_NOT);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * sets minus sign to nsecs field
     * minus is "1"
     */
    public void setMinusSign(){
        try {
            nsecs.setIntValue(nsecs.getIntValue() | SIGN_BIT);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /*
     * 0:plus, 1:minus
     */
    public void setSign(int sign){
        if (sign == PLUS_SIGN)
            setPlusSign();
        else if (sign == MINUS_SIGN)
            setMinusSign();
    }

    /**
     * return 0 (+) or 1 (-)
     */
    public int getSign(){
        if ((nsecs.getIntValue() & SIGN_BIT) == 0){
            return PLUS_SIGN;
        }
        else{
            return MINUS_SIGN;
        }
    }

    public void setNanoSecs(int val) throws Exception{
        // check range [0 , 999999999]
        int sign;

        if (val < NANO_SECS_MIN || val > NANO_SECS_MAX){
            throw new Exception("NanoSecs out of range (" + NANO_SECS_MIN + " - " + NANO_SECS_MAX + ")");
        }

        // backup/restore sign bit
        sign = getSign();
        nsecs.setIntValue(val);
        setSign(sign);
    }

    public int getNanoSecs(){
        return (nsecs.getIntValue() & SIGN_BIT_NOT);
    }

    public void setSecs(int val){
        try {
            secs.setIntValue(val);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getSecs(){
        return secs.getIntValue();
    }

    public String toString(){
        return "Sign: " + (getSign()==0?"+":"-") + "  Secs: " + getSecs() + "  NanoSecs: " + getNanoSecs();
    }
}
