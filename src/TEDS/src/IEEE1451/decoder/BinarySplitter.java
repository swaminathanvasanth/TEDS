package IEEE1451.decoder;

import IEEE.TEDS.DATABASE.TEDSData;

public class BinarySplitter {

	TEDSDecoder decoder = new TEDSDecoder();

	public static void split() {
		// System.out.println(binary);
		boolean isString = false;
		String value = null;
		String binary = TEDSData.getBinaryTEDS();
		char decodedValue = ' ';
		for (int i = 0; i < binary.length() - 1; ) {
		/*	if(binary.charAt(i) != ',')
			System.out.print(binary.charAt(i));
		*/
			if(binary.charAt(i+1) == ',' && !isString){
				decodedValue = TEDSDecoder.decode(binary.charAt(i), i);
				i++;
			}
			else if(binary.charAt(i) == ',' && !isString){
			//	System.out.println(" Decoded Value is : " +decodedValue);
				decodedValue = ' ';
				i++;
			}
			else if (binary.charAt(i) != ',' && !isString){
				isString = true;
				value = Character.toString(binary.charAt(i));
				if(i!=binary.length())
					i++;
			}
			else if (isString && binary.charAt(i) != ',' && i != binary.length()){
				value += Character.toString(binary.charAt(i));
				if(i!=binary.length())
					i++;
			} else if(isString && binary.charAt(i) == ','){
				String decodedString = TEDSDecoder.decode(value, i);
		//		System.out.println(" Decoded Value is : " +decodedString);
				decodedString = " ";
				if(i != binary.length())
					i++; 
				isString = false;
			} else if(isString && i+1 == binary.length()){
				value += Character.toString(binary.charAt(i));
				String decodedString = TEDSDecoder.decode(value, i);
				System.out.println(decodedString);
				System.out.println("HIT");
				/*if(i!=binary.length() && i < binary.length())
					i++; 
				isString = false;*/
			}
		}
	}
}
