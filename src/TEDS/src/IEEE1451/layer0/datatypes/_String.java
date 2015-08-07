package IEEE1451.layer0.datatypes;

/** _String datatype
 * @info 4.7 (p.11)
 * @author Silvestros Nick (nsilvestros@yahoo.com)
 */
public final class _String{

    private String value;

    public _String(){
        this(""); // default value
    }

    public _String(String val){
        value = val;
    }

    public void setValue(String val){
        value = val;
    }

    public String getValue(){
        return value;
    }

    public String toString(){
        return this.getValue();
    }
}
