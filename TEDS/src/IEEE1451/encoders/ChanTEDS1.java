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
import IEEE1451.layer0.messages.DecodeOctetStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Create ChanTEDS
 * Temperature (Kelvins)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */



public class ChanTEDS1 {
	TEDSID tedsid;
	CalKey calkey;
	ChanType chantype;
	PhyUnits phyunits;
	LowLimit lowlimit;
	HiLimit hilimit;
	OError oerror;
	SelfTest selftest;
	Sample sample;
	UpdateT updatet;
	RSetupT rsetupt;
	WarmUpT warmupt;
	RDelayT rdelayt;
	Sampling sampling;
	Description desc;
    public UInt8[] getTedsid() {
		return tedsid.getOctetArray();
	}
	public UInt8[] getCalkey() {
		return calkey.getOctetArray();
	}
	public UInt8[] getChantype() {
		return chantype.getOctetArray();
	}
	public UInt8[] getPhyunits() {
		return phyunits.getOctetArray();
	}
	public UInt8[] getLowlimit() {
		return lowlimit.getOctetArray();
	}
	public UInt8[] getHilimit() {
		return hilimit.getOctetArray();
	}
	public UInt8[] getOerror() {
		return oerror.getOctetArray();
	}
	public UInt8[] getSelftest() {
		return selftest.getOctetArray();
	}
	public UInt8[] getSample() {
		return sample.getOctetArray();
	}
	public UInt8[] getUpdatet() {
		return updatet.getOctetArray();
	}
	public UInt8[] getRsetupt() {
		return rsetupt.getOctetArray();
	}
	public UInt8[] getWarmupt() {
		return warmupt.getOctetArray();
	}
	public UInt8[] getRdelayt() {
		return rdelayt.getOctetArray();
	}
	public UInt8[] getSampling() {
		return sampling.getOctetArray();
	}
	public UInt8[] getDesc() {
		return desc.getOctetArray();
	}
	public void main() throws Exception{
        TEDS chanteds = new TEDS();

        tedsid = new TEDSID(TEDSAccessCodes.CHANTEDS, TEDSID.ORIGINAL_RELEASE);
        chanteds.addDataBlock(tedsid);

        // no callibration
        calkey = new CalKey(CalKey.CAL_NONE);
        chanteds.addDataBlock(calkey);

        // sensor
        chantype = new ChanType(ChanType.SENSOR);
        chanteds.addDataBlock(chantype);

        // kelvins
        phyunits = new PhyUnits();
        phyunits.setInterpretation(PhyUnits.PUI_SI_UNITS);
        phyunits.setKelvinsExp(1);
        chanteds.addDataBlock(phyunits);

        // no low limit
        lowlimit = new LowLimit(Float32.NaN);
        chanteds.addDataBlock(lowlimit);

        // no hi limit
        hilimit = new HiLimit(Float32.NaN);
        chanteds.addDataBlock(hilimit);

        // no worst-case uncertainty
        oerror = new OError(Float32.NaN);
        chanteds.addDataBlock(oerror);

        // no self test
        selftest = new SelfTest(SelfTest.NO_SELF_TEST);
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
        Description desc = new Description("Temperature_(Kelvins)");
        chanteds.addDataBlock(desc);
        
        UInt8[] a = chanteds.getOctetArray();
        System.out.println("Length: " + chanteds.getLength());

        //OutputStream out = new FileOutputStream(new File("ChanTEDS_1"));
        //Properties po = new Properties();
        //po.setProperty("teds", TEDS.encodeTEDS(a));
        //po.store(out, null);
        //out.close();
    }
}
