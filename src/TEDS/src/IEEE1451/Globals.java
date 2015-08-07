package IEEE1451;

/**
 * Global info
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public class Globals {

    public static int NCAP = 0;
    public static int TIM = 1;

    private int type;

    public Globals(int type) {
        this.type = type;
    }

    // NCAP or TIM
    public int getType(){
        return type;
    }

    public boolean isNCAP(){
        return (type==NCAP)?true:false;
    }

    public boolean isTIM(){
        return (type==TIM)?true:false;
    }

}
