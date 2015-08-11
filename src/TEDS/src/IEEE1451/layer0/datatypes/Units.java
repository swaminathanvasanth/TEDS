package IEEE1451.layer0.datatypes;

/**
 * Physical units
 * @info 4.11 (p.13)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class Units {
    private UInt8 interpretation = new UInt8();
    private UInt8 radians = new UInt8();
    private UInt8 steradians = new UInt8();
    private UInt8 meters = new UInt8();
    private UInt8 kilograms = new UInt8();
    private UInt8 seconds = new UInt8();
    private UInt8 amperes = new UInt8();
    private UInt8 kelvins = new UInt8();
    private UInt8 moles = new UInt8();
    private UInt8 candelas  = new UInt8();
    private UInt8 UnitsExtensionTEDSAccessCode  = new UInt8();

    public static final int PUI_SI_UNITS = 0;
    public static final int PUI_RATIO_SI_UNITS = 1;
    public static final int PUI_LOG10_SI_UNITS = 2;
    public static final int PUI_LOG10_RATIO_SI_UNITS = 3;
    public static final int PUI_DIGITAL_DATA = 4;
    public static final int PUI_ARBITRARY = 5;

    
}
