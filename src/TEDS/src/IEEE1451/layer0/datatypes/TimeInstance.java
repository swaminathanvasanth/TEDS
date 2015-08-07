package IEEE1451.layer0.datatypes;

import java.util.Calendar;

/**
 * Time instance
 * @info 4.9.2 (p.13)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class TimeInstance extends TimeRepresentation{

    public TimeInstance(){
        super();
    }

    public TimeInstance(int sign, int secs, int nsecs) throws Exception{
        super(sign, secs, nsecs);
    }

    /**
     *
     * @return now instance (time from epoch (1/1/1970 00:00)) or null
     */
    public static TimeInstance getNow(){
        try {
            TimeInstance now = new TimeInstance();
            Calendar c = Calendar.getInstance();
            
            // we are after epoch
            now.setPlusSign();

            // convert millisecs to secs
            now.setSecs((int) (c.getTime().getTime() / 1000));

            // convert left millisecs to nanosecs
            now.setNanoSecs((int) ((c.getTime().getTime() % 1000) * 1000 * 1000));

            return now;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String toString(){
        return (getSign()==0?"after ":"before ") + getSecs() + " secs and " + getNanoSecs() + " nanosecs";
    }
}
