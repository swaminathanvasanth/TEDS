package IEEE1451.layer2;

import IEEE1451.layer0.datatypes.UInt8;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Message structure (Command/Reply)
 * start: FF 00  end: FF FF  stuff after every  FF with 11
 * Debug messages are alphanumeric with '\n' at the end
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class Communication {

    // max message length
    public static final int MAX_MESSAGE = 1000;

    // outside message
    static int UNDEFINED = 0;
    // after first FF of message
    static int AFTER_HALF_START = 1;
    // inside message
    static int PAYLOAD = 2;
    // after FF inside message
    static int AFTER_FF = 3;
  

    /**
     * Closes InputStream
     * @param input
     */
    public void close(InputStream input){
        try {
            input.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Closes OutputStream
     * @param output
     */
    public void close(OutputStream output){
        try {
            output.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Closes IOStream
     * @param iostream
     */
    public void close(IOStream iostream){
        iostream.close();
    }

    /**
     *
     * @param input
     * @return next Message received from InputStream
     */
    public Message receive(InputStream input){
        byte b;
        byte[] stream = new byte[MAX_MESSAGE];
        int index = 0, bindex=0;
        int mode = UNDEFINED;

        while(true){
            try {
                // read next byte from stream
                b = (byte) input.read();

                // if outside message
                if (mode == UNDEFINED){
                    // possible start of message
                    if (b == (byte)0xFF){
                        mode = AFTER_HALF_START;
                        stream[index++] = b;
                    }
                    // debug/alphanumeric message
                    else if (b == '\n'){
                        stream[index++] = b;
                        bindex = index - 2;
                        index = 0;
                        return new Message(Message.TEXT, UInt8.getArray(this.rTrimByteArray(stream, bindex)));
                    }
                    else{
                        stream[index++] = b;
                    }
                }
                // if after a FF (outside message)
                else if (mode == AFTER_HALF_START){
                    // if 00 then message start
                    if (b == (byte)0x00){
                        mode = PAYLOAD;
                        index = 0;
                    }
                    // 2nd FF outside message, possible now starts a new one
                    else if (b == (byte)0xFF){
                        mode = AFTER_HALF_START;
                        stream[index++] = b;
                    }
                    else{
                        mode = UNDEFINED;
                        stream[index++] = b;
                    }
                }
                // if inside message
                else if (mode == PAYLOAD){
                    // if FF inside message
                    if (b == (byte)0xFF){
                        mode = AFTER_FF;
                    }
                    stream[index++] = b;
                }
                // if FF inside message
                else if (mode == AFTER_FF){
                    // if stuffed byte
                    if (b == (byte)0x11){
                        mode = PAYLOAD;
                    }
                    // 2nd FF inside message, message end
                    else if (b == (byte)0xFF){
                        mode = UNDEFINED;
                        stream[index - 1] = (byte)0x00;
                        ////////////
                        return new Message(Message.MESSAGE, UInt8.getArray(this.rTrimByteArray(stream, index - 2)));
                    }
                    else{
                        mode = UNDEFINED;
                        stream[index++] = b;
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }



    /**
     *
     * @param bin
     * @param index last array element
     * @return byte array (index is the last)
     */
    byte[] rTrimByteArray(byte[] bin, int index){
        byte[] bout = new byte[index + 1];
        for (int i = 0; i < bout.length; i++) {
            bout[i] = bin[i];
        }
        return bout;
    }

    /**
     * Sends message
     * @param output
     * @param msg
     */
    public void send(OutputStream output, Message msg){
        try {
            byte[] b = UInt8.getArray(msg.getPayload());
            // if it is text
            if (msg.isText()){
                output.write(b);
                output.flush();
            }
            // if it is message
            else if (msg.isMessage()){
                send(output, msg.getPayload());
            }
        } catch (IOException e) {
            System.err.println("Error sending message (IOException)");
            e.printStackTrace();
        } catch (Exception ex) {
            System.err.println("Error sending message (Exception)");
            ex.printStackTrace();
        }
    }

    /**
     * Sends message stream
     * @param output
     * @param stream Message stream
     */
    private void send(OutputStream output, UInt8[] stream){
        byte[] bstream = new byte[MAX_MESSAGE];
        byte[] tosend;
        int index = 0;

        try {
            byte[] b = UInt8.getArray(stream);

            // message start
            bstream[index++] = (byte)0xFF;
            bstream[index++] = (byte)0x00;

            for (int i = 0; i < b.length; i++) {
                // stuffing
                if (b[i] == (byte)0xFF){
                    bstream[index++] = (byte)0xFF;
                    bstream[index++] = (byte)0x11;
                }
                else{
                    bstream[index++] = b[i];
                }
            }

            // message end
            bstream[index++] = (byte)0xFF;
            bstream[index++] = (byte)0xFF;

            tosend = rTrimByteArray(bstream, index - 1);

            output.write(tosend);
            output.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
