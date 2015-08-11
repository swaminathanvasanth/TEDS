package IEEE1451.layer2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Input/Output Stream
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class IOStream {

    private InputStream input;
    private OutputStream output;

    public IOStream() {
    }


    public IOStream(InputStream input, OutputStream output) {
        this();
        this.input = input;
        this.output = output;
    }

    public InputStream getInputStream(){
        return input;
    }

    public OutputStream getOutputStream(){
        return output;
    }

    public void close(){
        try {
            input.close();
            output.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
