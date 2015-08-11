package IEEE1451.layer2;

import IEEE1451.layer0.datatypes.UInt8;

/**
 * Message returned from receive
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class Message {

    public static int MESSAGE = 0;
    public static int TEXT = 1;

    private int type;
    private UInt8[] payload;


    public Message(int type, UInt8[] payload) {
        this.type = type;
        this.payload = payload;
    }

    public int getType(){
        return type;
    }

    public boolean isMessage(){
        return (type == MESSAGE)?true:false;
    }

    public boolean isText(){
        return (type == TEXT)?true:false;
    }


    public UInt8[] getPayload(){
        return payload;
    }

    public String getText(){
        try {
            return new String(UInt8.getArray(this.getPayload()));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
