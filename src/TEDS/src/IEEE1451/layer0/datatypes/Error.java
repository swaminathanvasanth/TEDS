package IEEE1451.layer0.datatypes;


/**
 * Error codes
 * @info 9.3.1.2 (p.164)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class Error {

    // error code source (3 MSB (bit 15...13))
    public static final int ERROR_FROM_LOCAL_0_LAYER = 0;
    public static final int ERROR_FROM_LOCAL_X_LAYER = 1;
    public static final int ERROR_FROM_REMOTE_X_LAYER = 2;
    public static final int ERROR_FROM_REMOTE_0_LAYER = 3;
    public static final int ERROR_FROM_REMOTE_APPLICATION_LAYER = 4;

    // error code enumeration (bit 12...0)
    public static final int NO_ERROR = 0;                       //
    public static final int INVALID_COMMID = 1;                 //
    public static final int UNKNOWN_DESTID = 2;                 //
    public static final int TIMEOUT = 3;                        //
    public static final int NETWORK_FAILURE = 4;                //
    public static final int NETWORK_CORRUPTION = 5;
    public static final int MEMORY = 6;
    public static final int QOS_FAILURE = 7;
    public static final int MCAST_NOT_SUPPORTED = 8;
    public static final int UNKNOWN_GROUPID = 9;
    public static final int UNKNOWN_MODULEID = 10;
    public static final int UNKNOWN_MSGID = 11;
    public static final int NOT_GROUP_MEMBER = 12;
    public static final int ILLEGAL_MODE = 13;
    public static final int LOCKED_RESOURCE = 14;
    public static final int FATAL_TEDS_ERROR = 15;
    public static final int NON_FATAL_TEDS_ERROR = 16;
    public static final int CLOSE_ON_LOCKED_RESOURCE = 17;
    public static final int LOCK_BROKEN = 18;
    public static final int NETWORK_RESOURCE_EXCEEDED = 19;
    public static final int MEMORY_RESOURCE_EXCEEDED = 20;


    private static final short ERROR_SOURCE = (short)0xE000; // bit 15...13
    private static final short ERROR_CODE = (short)0x1FFF;   // bit 12...0
    
    private UInt16 error;

    public Error() {
        error = new UInt16();
    }

    public Error(UInt16 error) {
        this.error = error;
    }

    public Error(int source, int code){
        this();
        try {
            setErrorSource(source);
            setErrorCode(code);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setErrorSource(int source) throws Exception{
        source = source<<13;
        error.setShortValue((short) (error.getShortValue() & ~ERROR_SOURCE));
        error.setShortValue((short) (error.getShortValue() | source));
    }

    public int getErrorSource(){
        return ((error.getShortValue() & ERROR_SOURCE)>>13);
    }

    public void setErrorCode(int code) throws Exception{
        code &= ERROR_CODE;
        error.setShortValue((short) (error.getShortValue() & ~ERROR_CODE));
        error.setShortValue((short) (error.getShortValue() | code));
    }

    public int getErrorCode(){
        return (error.getShortValue() & ERROR_CODE);
    }

    public String toString(){
        return error.toString();
    }

}
