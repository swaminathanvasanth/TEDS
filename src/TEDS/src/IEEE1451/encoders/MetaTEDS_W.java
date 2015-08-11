package IEEE1451.encoders;

import IEEE1451.layer0.datatypes.Float32;
import IEEE1451.layer0.datatypes.UInt8;
import IEEE1451.layer0.datatypes.teds.Description;
import IEEE1451.layer0.datatypes.teds.TEDS;
import IEEE1451.layer0.datatypes.teds.TEDSAccessCodes;
import IEEE1451.layer0.datatypes.teds.TEDSID;
import IEEE1451.layer0.datatypes.teds.meta.MaxChan;
import IEEE1451.layer0.datatypes.teds.meta.OHoldOff;
import IEEE1451.layer0.datatypes.teds.meta.TestTime;
import IEEE1451.layer0.datatypes.teds.meta.UUID;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Create MetaTEDS
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class MetaTEDS_W {

    public static void main(String args[]) throws Exception{
        TEDS metateds = new TEDS();

        TEDSID tedsid = new TEDSID(TEDSAccessCodes.METATEDS, TEDSID.ORIGINAL_RELEASE);
        metateds.addDataBlock(tedsid);

        // 0000 + MAC address
        UUID uuid = new UUID("000000144F0100006242");
        metateds.addDataBlock(uuid);
        OHoldOff oholdoff = new OHoldOff(Float32.NaN);
        metateds.addDataBlock(oholdoff);

        TestTime testtime = new TestTime(TestTime.NO_SELF_TEST);
        metateds.addDataBlock(testtime);

        // 2 channels (temp, volt)
        MaxChan maxchan = new MaxChan(2);
        metateds.addDataBlock(maxchan);

        // description
        Description desc = new Description("Sun spot");
        metateds.addDataBlock(desc);

        UInt8[] a = metateds.getOctetArray();

        String s = new String();

        s = TEDS.encodeTEDS(a);
        

        OutputStream out = new FileOutputStream(new File("MetaTEDS"));
        Properties po = new Properties();
        po.setProperty("teds", s);
        po.store(out, null);
        out.close();
    }
}
