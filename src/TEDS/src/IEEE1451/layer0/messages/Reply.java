package IEEE1451.layer0.messages;

import IEEE1451.layer0.datatypes.UInt8;

/**
 * Reply message
 * @info 6.3 (p.58)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public abstract class Reply {

    public static final int SUCCESS_FLAG = 1;
    public static final int FAIL_FLAG = 0;

    private UInt8 successFailFlag;
    protected Object[] commandArgs;

    public Reply() {
        successFailFlag = new UInt8();
    }

    public Reply(int successFlag){
        this();
        setSuccessFailFlag(successFlag);
    }

    public void setSuccessFailFlag(int val){
        try {
            successFailFlag.setValue(val);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getSuccessFailFlag(){
        return successFailFlag.getValue();
    }

    public int getLength(){
        // 3 default octets + cmd_args
        return getReplyLength() + 3;
    }

    public void setCommandArgs(Object[] val){
        commandArgs = val;
    }

    public Object[] getCommandArgs(){
        return commandArgs;
    }

    /**
     *
     * @return UInt8 array of all fields or null
     */
    abstract public UInt8[] getOctetArray();

    /**
     *
     * @return the number of octets of reply-dependent octets
     */
    abstract public int getReplyLength();


}
