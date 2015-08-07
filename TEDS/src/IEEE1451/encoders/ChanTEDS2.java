package IEEE1451.encoders;

import IEEE1451.layer0.datatypes.Float32;
import IEEE1451.layer0.datatypes.UInt8;
import IEEE1451.layer0.datatypes.teds.DataBlock;
import IEEE1451.layer0.datatypes.teds.Description;
import IEEE1451.layer0.datatypes.teds.TEDS;
import IEEE1451.layer0.datatypes.teds.TEDSAccessCodes;
import IEEE1451.layer0.datatypes.teds.TEDSID;
import IEEE1451.layer0.datatypes.teds.chan.ActHalt;
import IEEE1451.layer0.datatypes.teds.chan.CalKey;
import IEEE1451.layer0.datatypes.teds.chan.ChanType;
import IEEE1451.layer0.datatypes.teds.chan.ESOption;
import IEEE1451.layer0.datatypes.teds.chan.EdgeRpt;
import IEEE1451.layer0.datatypes.teds.chan.HiLimit;
import IEEE1451.layer0.datatypes.teds.chan.LowLimit;
import IEEE1451.layer0.datatypes.teds.chan.OError;
import IEEE1451.layer0.datatypes.teds.chan.PhyUnits;
import IEEE1451.layer0.datatypes.teds.chan.RDelayT;
import IEEE1451.layer0.datatypes.teds.chan.RSetupT;
import IEEE1451.layer0.datatypes.teds.chan.SPeriod;
import IEEE1451.layer0.datatypes.teds.chan.Sample;
import IEEE1451.layer0.datatypes.teds.chan.Sampling;
import IEEE1451.layer0.datatypes.teds.chan.SelfTest;
import IEEE1451.layer0.datatypes.teds.chan.TestTime;
import IEEE1451.layer0.datatypes.teds.chan.UpdateT;
import IEEE1451.layer0.datatypes.teds.chan.WSetupT;
import IEEE1451.layer0.datatypes.teds.chan.WarmUpT;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Create ChanTEDS
 * Battery Voltage (Volts = m²kg/(sec³A))
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class ChanTEDS2 {

    public static void main(String args[]) throws Exception{
        TEDS chanteds = new TEDS();

        TEDSID tedsid = new TEDSID(TEDSAccessCodes.CHANTEDS, TEDSID.ORIGINAL_RELEASE);
        chanteds.addDataBlock(tedsid);

        // no callibration
        CalKey calkey = new CalKey(CalKey.CAL_NONE);
        chanteds.addDataBlock(calkey);

        // sensor
        ChanType chantype = new ChanType(ChanType.SENSOR);
        chanteds.addDataBlock(chantype);

        // volts
        PhyUnits phyunits = new PhyUnits();
        phyunits.setInterpretation(PhyUnits.PUI_SI_UNITS);
        // Volts = m²kg/(sec³A)
        phyunits.setMetersExp(2);
        phyunits.setKilogramsExp(1);
        phyunits.setSecondsExp(-3);
        phyunits.setAmperesExp(-1);
        chanteds.addDataBlock(phyunits);

        // no low limit
        LowLimit lowlimit = new LowLimit(Float32.NaN);
        chanteds.addDataBlock(lowlimit);

        // no hi limit
        HiLimit hilimit = new HiLimit(Float32.NaN);
        chanteds.addDataBlock(hilimit);

        // no worst-case uncertainty
        OError oerror = new OError(Float32.NaN);
        chanteds.addDataBlock(oerror);

        // no self test
        SelfTest selftest = new SelfTest(SelfTest.NO_SELF_TEST);
        chanteds.addDataBlock(selftest);

        // Float32
        Sample sample = new Sample(Sample.SINGLE_PRECISION_REAL, Float32.NUMBER_OF_OCTETS, 8*Float32.NUMBER_OF_OCTETS);
        chanteds.addDataBlock(sample);

        // max time between trigger-sample
        UpdateT updatet = new UpdateT(Float32.NaN);
        chanteds.addDataBlock(updatet);

        // max time between trigger-available data
        RSetupT rsetupt = new RSetupT(Float32.NaN);
        chanteds.addDataBlock(rsetupt);

        // time for channel stability
        WarmUpT warmupt = new WarmUpT(Float32.NaN);
        chanteds.addDataBlock(warmupt);

        // time between read data command-begin of transmission
        RDelayT rdelayt = new RDelayT(Float32.NaN);
        chanteds.addDataBlock(rdelayt);

        // trigger initiated sampling mode
        Sampling sampling = new Sampling();
        sampling.setSamplingMode(Sampling.TRIGGER);
        sampling.setDefaultSamplingMode(Sampling.TRIGGER);
        chanteds.addDataBlock(sampling);

        // description
        Description desc = new Description("Battery_Voltage_(Volts)");
        chanteds.addDataBlock(desc);


        UInt8[] a = chanteds.getOctetArray();
        System.out.println("Length: " + chanteds.getLength());


        OutputStream out = new FileOutputStream(new File("ChanTEDS_2"));
        Properties po = new Properties();
        po.setProperty("teds", TEDS.encodeTEDS(a));
        po.store(out, null);
        out.close();
    }
}
