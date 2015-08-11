package IEEE1451.layer0.datatypes.teds.chan;

import IEEE1451.layer0.messages.EncodeOctetStream;
import IEEE1451.layer0.datatypes.*;
import IEEE1451.layer0.datatypes.teds.*;
import IEEE1451.layer0.messages.DecodeOctetStream;


/**
 * TransducerChannel type key
 * @info 8.5.2.6 (p.98)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class PhyUnits extends DataBlock{
    
    public static final int PUI_SI_UNITS = 0;
    public static final int PUI_RATIO_SI_UNITS = 1;
    public static final int PUI_LOG10_SI_UNITS = 2;
    public static final int PUI_LOG10_RATIO_SI_UNITS = 3;
    public static final int PUI_DIGITAL_DATA = 4;
    public static final int PUI_ARBITRARY = 5;

    public static final int INTERPRETATION = 50;
    public static final int RADIANS = 51;
    public static final int STERADIANS = 52;
    public static final int METERS = 53;
    public static final int KILOGRAMS = 54;
    public static final int SECONDS = 55;
    public static final int AMPERES = 56;
    public static final int KELVINS = 57;
    public static final int MOLES = 58;
    public static final int CANDELAS = 59;
    public static final int UNITS_EXTENSION_TEDS_ACCESS_CODE = 60;      // gia text-based TEDS mono

    private static final int OFFSET = 50;

    private static final int INTERPRETATION_OFFSET = INTERPRETATION - OFFSET;
    private static final int RADIANS_OFFSET = RADIANS - OFFSET;
    private static final int STERADIANS_OFFSET = STERADIANS - OFFSET;
    private static final int METERS_OFFSET = METERS - OFFSET;
    private static final int KILOGRAMS_OFFSET = KILOGRAMS - OFFSET;
    private static final int SECONDS_OFFSET = SECONDS - OFFSET;
    private static final int AMPERES_OFFSET = AMPERES - OFFSET;
    private static final int KELVINS_OFFSET = KELVINS - OFFSET;
    private static final int MOLES_OFFSET = MOLES - OFFSET;
    private static final int CANDELAS_OFFSET = CANDELAS - OFFSET;

    // length 10...11 analogws an yparxei to UNITS_EXTENSION_TEDS_ACCESS_CODE
    private UInt8[] value = new UInt8[11];

//    private PhyUnits(){
//        super(DataBlock.PHYUNITS);
//        try {
//            // initialize to 128 (default value)
//            value = UInt8.initializeArray(value, 128);
//            // except interpretation
//            value[INTERPRETATION_OFFSET].setValue(0);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

    public PhyUnits() {
        super(DataBlock.PHYUNITS);
        try {
            // initialize to 128 (default value)
            value = UInt8.initializeArray(value, 128);
            // except interpretation
            value[INTERPRETATION_OFFSET].setValue(0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public PhyUnits(DataBlock db, UInt8[] args) throws Exception{
        this();

        DecodeOctetStream dos = new DecodeOctetStream(args);
        for (int i = 0; i < value.length; i++) {
            value[i] = dos.readUInt8();
        }
        
    }

    public UInt8[] getOctetArray() {
        try {
            EncodeOctetStream stream = new EncodeOctetStream(this);

            for(int i = 0; i<value.length; i++){
                stream.addUInt8(value[i]);
            }

            return stream.getOctetsArray();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int getLength() {
        return 11 * UInt8.NUMBER_OF_OCTETS;  // length 10...11 analogws an yparxei to UNITS_EXTENSION_TEDS_ACCESS_CODE
    }

    /**
     *
     * @param exponent
     * @return result (2 * exp + 128)
     */
    private int getResult(int exponent){
        return (2 * exponent + 128);
    }

    /**
     *
     * @param value
     * @return exponent (value - 128) /2
     */
    private int getExponent(int value){
       return (value - 128) / 2;
    }

    public void setInterpretation(int val){
        try {
            value[INTERPRETATION_OFFSET].setValue(val);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getInterpretation(){
        return getExponent(value[INTERPRETATION_OFFSET].getValue());
    }

    public void setRadiansExp(int val){
        try {
            value[RADIANS_OFFSET].setValue(getResult(val));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getRadiansExp(){
        return getExponent(value[RADIANS_OFFSET].getValue());
    }

    public void setSteradiansExp(int val){
        try {
            value[STERADIANS_OFFSET].setValue(getResult(val));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getSteradiansExp(){
        return getExponent(value[STERADIANS_OFFSET].getValue());
    }
    
    public void setMetersExp(int val){
        try {
            value[METERS_OFFSET].setValue(getResult(val));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getMetersExp(){
        return getExponent(value[METERS_OFFSET].getValue());
    }

    public void setKilogramsExp(int val){
        try {
            value[KILOGRAMS_OFFSET].setValue(getResult(val));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getKilogramsExp(){
        return getExponent(value[KILOGRAMS_OFFSET].getValue());
    }

    public void setSecondsExp(int val){
        try {
            value[SECONDS_OFFSET].setValue(getResult(val));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getSecondsExp(){
        return getExponent(value[SECONDS_OFFSET].getValue());
    }

    public void setAmperesExp(int val){
        try {
            value[AMPERES_OFFSET].setValue(getResult(val));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getAmperesExp(){
        return getExponent(value[AMPERES_OFFSET].getValue());
    }

    public void setKelvinsExp(int val){
        try {
            value[KELVINS_OFFSET].setValue(getResult(val));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getKelvinsExp(){
        return getExponent(value[KELVINS_OFFSET].getValue());
    }

    public void setMolesExp(int val){
        try {
            value[MOLES_OFFSET].setValue(getResult(val));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getMolesExp(){
        return getExponent(value[MOLES_OFFSET].getValue());
    }
    
    public void setCandelasExp(int val){
        try {
            value[CANDELAS_OFFSET].setValue(getResult(val));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getCandelasExp(){
        return getExponent(value[CANDELAS_OFFSET].getValue());
    }

    public int getField(int field){
        return getExponent(value[field - OFFSET].getValue());
    }

    public void setField(int field, int val){
        try {
            value[field - OFFSET].setValue(getResult(val));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public UInt8[] getAllPhysicalUnits(){
        return value;
    }
        

}

