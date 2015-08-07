package IEEE1451.layer0.datatypes;

/** _Boolean datatype
 * @info 4.8 (p.11)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */

/* For the purposes of this standard, a bit or octet with a nonzero value is considered True. A zero value
represents the False state of the variable. */

public final class _Boolean{

    private boolean value;

    public _Boolean(){
        this(false); // default value
    }

    public _Boolean(boolean val){
        value = val;
    }

    public _Boolean(int val){
        if (val == 0)
            value = false;
        else
            value = true;
    }

    public void setTrue(){
        value = true;
    }

    public void setFalse(){
        value = false;
    }

    public boolean getValue(){
        return value;
    }

    public String toString(){
        if (value)
            return "true";
        else
            return "false";
    }
}
