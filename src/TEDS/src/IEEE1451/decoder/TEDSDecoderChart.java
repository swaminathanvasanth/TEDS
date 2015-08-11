package IEEE1451.decoder;

public class TEDSDecoderChart {

	public static char decodeStringfromchart(String s){
		int value = Integer.parseInt(s);
		
	/*	if(value == 3 || value == 4 ||value == 10 ||value == 12 ||value == 13 ||value == 128){
			if(value == 3){
			return ('3');
			}
			else{
				return (' ');}
		}
		else
	*/		return ((char)value);
	}
}
