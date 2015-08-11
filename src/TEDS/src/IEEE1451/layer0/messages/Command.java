package IEEE1451.layer0.messages;

import IEEE1451.layer0.datatypes.UInt8;
import IEEE1451.layer0.datatypes.UInt16;

/**
 * Command message
 * @info 6.2 (p.57)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public abstract class Command {

    // command classes
    public static final int COMMON_CMD = 1;
    public static final int XDCR_IDLE = 2;
    public static final int XDCR_OPERATE = 3;
    public static final int XDCR_EITHER = 4;
    public static final int TIM_SLEEP = 5;
    public static final int TIM_ACTIVE = 6;
    public static final int ANY_STATE = 7;

    // common command functions
    public static final int QUERY_TEDS = 1;
    public static final int READ_TEDS_SEGMENT = 2;
    public static final int WRITE_SERVICE_REQUEST_MASK = 6;
    public static final int READ_SERVICE_REQUEST_MASK = 7;
    public static final int READ_STATUS_EVENT_REGISTER = 8;
    public static final int READ_STATUS_CONDITION_REGISTER = 9;
    public static final int CLEAR_STATUS_EVENT_REGISTER = 10;
    public static final int WRITE_STATUS_EVENT_PROTOCOL_STATE = 11;
    public static final int READ_STATUS_EVENT_PROTOCOL_STATE = 12;

    // transducer operating state functions
    public static final int READ_TRANSDUCER_CHANNEL_DATA_SET_SEGMENT = 1;


    // transducer channel number (5.3, p.23)
    public static final int TIM = 0;
    // TIM and all transducer channels
    public static final int GLOBAL = UInt16.MAX_VALUE;  // 0xFFFF

    private UInt16 transducerChannelNumber;
    private UInt8 commandClass;
    private UInt8 commandFunction;
    protected Object[] commandArgs;

    public Command() {
        transducerChannelNumber = new UInt16();
        commandClass = new UInt8();
        commandFunction = new UInt8();
    }

    public Command(int commandClass, int commandFunction) {
        this();
        setCommandClass(commandClass);
        setCommandFunction(commandFunction);
    }

    public void setTransducerChannelNumber(int val){
        try {
            transducerChannelNumber.setValue(val);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getTransducerChannelNumber(){
        return transducerChannelNumber.getValue();
    }

    public void setCommandClass(int val){
        try {
            commandClass.setValue(val);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public int getCommandClass(){
        return commandClass.getValue();
    }

    public void setCommandFunction(int val){
        try {
            commandFunction.setValue(val);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getCommandFunction(){
        return commandFunction.getValue();
    }

    public int getLength(){
        // 6 default octets + cmd_args
        return getCommandLength() + 6;
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
     * @return the number of octets of command-dependent octets
     */
    abstract public int getCommandLength();

}
