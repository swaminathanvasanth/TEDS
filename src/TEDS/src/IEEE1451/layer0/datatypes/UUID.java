package IEEE1451.layer0.datatypes;

/**
 * UUID (Universal Unique Identification Datatype structure)
 * @info 4.12 (p.15)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class UUID {

    private UInt8[] value;

    /**
     *
     * @param val HEX value without leading 0x (20 digit length)
     */
    public UUID(String val) throws Exception{
        int i;

        value = new UInt8[10];

        if (val.length() != 20)
            throw new Exception("Illegal UUID format (20 digit HEX)");
        
        for (i=0; i<20; i+=2){
            value[i/2] = new UInt8("0x" + val.substring(i, i+2));
        }
    }

 /*
    public void setLocation(int val){

    }

    public void setNorthSouth(int val){
        
    }

    public void setNorth(){

    }

    public void setSouth(){

    }

    public void setLatitude(int val){

    }

    public void setEastWest(int val){

    }

    public void setEast(){

    }

    public void setWest(){

    }

    public void setLongitude(int val){
        
    }

    public void setManufacturer(int val){

    }

    public void setYear(int val){

    }

    public void setTime(int val){

    }
*/

    public UInt8[] getOctets(){
        return value;
    }

    public String toString(){
        String s = "";
        int i;

        for (i=0; i<10; i++){
            s += value[i].toTwoDigitHex();
        }
        
        return s;
    }
}
