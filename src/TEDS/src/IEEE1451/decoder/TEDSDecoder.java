package IEEE1451.decoder;

public class TEDSDecoder {

	public static String data = "";
	public static char decode(char c, int i) {
		// System.out.print("Binary is " +c);
		// System.out.print(" Char is " +c);
		 data+= "," +c;
		if(i<=8)
			return ' ';
		else
			return '#';
	}

	public static String decode(String s, int i){
		//System.out.print("Binary is " +s);
		//System.out.print(" String is " +s);
		
		if(i<=8)
			return " ";
		else{
			int value = Integer.parseInt(s);
			 data+= "," +(char)value;
			return (""+(char)value);}
	}
}
