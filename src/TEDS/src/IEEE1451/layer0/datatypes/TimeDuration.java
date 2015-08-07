package IEEE1451.layer0.datatypes;

/**
 * Time duration
 * @info 4.9.1 (p.12)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class TimeDuration extends TimeRepresentation{

    public TimeDuration(){
        super();
    }

    public TimeDuration(int sign, int secs, int nsecs) throws Exception{
        super(sign, secs, nsecs);
    }
}
