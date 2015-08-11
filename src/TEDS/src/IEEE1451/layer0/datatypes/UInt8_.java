/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package IEEE1451.layer0.datatypes;

//import java.lang.Byte;

/** UInt8 datatype
 *
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public final class UInt8_{
    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = 255;

    private int value;

    public UInt8_(){
        value = 0;  // default value
    }

    public UInt8_(int val) throws Exception, NumberFormatException{  /////////////////  + dw http://web.nps.navy.mil/~brutzman/vrtp/mil/navy/nps/util/UnsignedByte.java
        if (check_range(val)){
            value = val;
        }
        else {
            value = 0;
            throw new Exception("Out of range");
        }
    }

    public UInt8_(String val) throws Exception{
        this(Integer.valueOf(val).intValue());
    }

    public void setValue(int val) throws Exception{
        if (check_range(val)){
            value = val;
        }
        else {
            value = 0;
            throw new Exception("Out of range");
        }
    }

    public int getValue(){
        return value;
    }

    public boolean equals(int val) throws Exception{
        if (check_range(val)){
            if (value == val){
                   return true;
            }
            else {
                return false;
            }
        }
        else {
            throw new Exception("Out of range");
        }
    }

    private boolean check_range(int val){
        if (val < MIN_VALUE || val > MAX_VALUE)
            return false;
        else
            return true;
    }
}
