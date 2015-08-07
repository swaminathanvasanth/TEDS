package rbccps.iot.ncap.SNaaS.CP.TEDS.LocalCache;

/**
 * Created by vasanth on 7/3/15.
 */
public class TEDS {

    public static String COLUMN_ID = "_id";
    public static String COLUMN_KEY = "key";
    public static String COLUMN_VALUE = "value";

    public static String getCOLUMN_ID() {
        return COLUMN_ID;
    }

    public static void setCOLUMN_ID(String COLUMN_ID) {
        TEDS.COLUMN_ID = COLUMN_ID;
    }

    public static String getCOLUMN_KEY() {
        return COLUMN_KEY;
    }

    public static void setCOLUMN_KEY(String COLUMN_KEY) {
        TEDS.COLUMN_KEY = COLUMN_KEY;
    }

    public static String getCOLUMN_VALUE() {
        return COLUMN_VALUE;
    }

    public static void setCOLUMN_VALUE(String COLUMN_VALUE) {
        TEDS.COLUMN_VALUE = COLUMN_VALUE;
    }
}
