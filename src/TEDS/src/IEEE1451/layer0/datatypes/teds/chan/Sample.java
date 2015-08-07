package IEEE1451.layer0.datatypes.teds.chan;

import IEEE1451.layer0.messages.EncodeOctetStream;
import IEEE1451.layer0.datatypes.*;
import IEEE1451.layer0.datatypes.teds.*;
import IEEE1451.layer0.messages.DecodeOctetStream;

/**
 * Sample
 * @info 8.5.2.23 (p.104)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class Sample extends DataBlock{

    public static final int DATMODEL = 40;
    public static final int MODLENGTH = 41;
    public static final int SIGBITS = 42;

    public static final int N_OCTET_INT = 0;
    public static final int SINGLE_PRECISION_REAL = 1;
    public static final int DOUBLE_PRECISION_REAL = 2;
    public static final int N_OCTET_FRACTION = 3;
    public static final int BIT_SEQUENCE = 4;
    public static final int LONG_INT = 5;
    public static final int LONG_FRACTION = 6;
    public static final int TIME_OF_DAY = 7;


    private UInt8 dataModel, dataModelLength;
    private UInt16 MSB;

    public Sample(){
        super(DataBlock.SAMPLE);
        dataModel = new UInt8();
        dataModelLength = new UInt8();
        MSB = new UInt16();
    }

    public Sample(int dataModel, int dataModelLength, int MSB) throws Exception {
        this();
        checkDataModelLength(dataModelLength);
        this.dataModel.setValue(dataModel);
        this.dataModelLength.setValue(dataModelLength);
        this.MSB.setValue(MSB);
    }

    public Sample(DataBlock db, UInt8[] args) throws Exception{
        this();

        DecodeOctetStream dos = new DecodeOctetStream(args);
        this.setDataModel(dos.readUInt8().getValue());
        this.setDataModelLength(dos.readUInt8().getValue());
        this.setMSB(dos.readUInt16().getValue());
    }

    public UInt8 getDataModel(){
        return dataModel;
    }

    public void setDataModel(int val){
        try {
            dataModel.setValue(val);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public UInt8 getDataModelLength(){
        return dataModelLength;
    }

    public void setDataModelLength(int val){
        try {
            checkDataModelLength(val);
            dataModelLength.setValue(val);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     * @param dmlength
     */
    private void checkDataModelLength(int dmlength) throws Exception{
        int dm = dataModel.getValue();
        switch (dm){
            case N_OCTET_INT:
            case N_OCTET_FRACTION:
            case BIT_SEQUENCE:
                if (dmlength < 0 || dmlength > 8){
                    throw new Exception("Data model lenght should be [0...8]");
                }
                break;
            case SINGLE_PRECISION_REAL:
                if (dmlength != 4){
                    throw new Exception("Data model lenght should be 4");
                }
                break;
            case DOUBLE_PRECISION_REAL:
            case TIME_OF_DAY:
                if (dmlength != 8){
                    throw new Exception("Data model lenght should be 8");
                }
                break;
            case LONG_INT:
            case LONG_FRACTION:
                if (dmlength < 9 || dmlength > 255){
                    throw new Exception("Data model lenght should be [9...255]");
                }
                break;
        }
    }

    public UInt16 getMSB(){
        return MSB;
    }

    public void setMSB(int val){
        try {
            MSB.setValue(val);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getField(int field){
        switch (field){
            case DATMODEL:  return getDataModel().getValue();
            case MODLENGTH: return getDataModelLength().getValue();
            case SIGBITS:   return getMSB().getValue();
            default:        return -1;
        }
    }

    public void setField(int field, int val){
        switch (field){
            case DATMODEL:  setDataModel(val);
            case MODLENGTH: setDataModelLength(val);
            case SIGBITS:   setMSB(val);
        }
    }

    public UInt8[] getOctetArray() {
        try {
            EncodeOctetStream stream = new EncodeOctetStream(this);
            stream.addUInt8(dataModel);
            stream.addUInt8(dataModelLength);
            stream.addUInt16(MSB);
            return stream.getOctetsArray();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int getLength() {
        return 2 * UInt8.NUMBER_OF_OCTETS + UInt16.NUMBER_OF_OCTETS;
    }

}
