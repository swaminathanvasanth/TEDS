package IEEE1451.Decoder;
/*
 * 
 * $RCSfile: TEDSDecoder.java $	
 *
 * Copyright (c) 2015, RBCCPS, IISc Bangalore. 
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions 
 * are met:
 * 
 *    -	Redistributions of source code must retain the above 
 *      copyright notice, this list of conditions and the following 
 *      disclaimer. 
 *    -	Redistributions in binary form must reproduce the above 
 *      copyright notice, this list of conditions and the following 
 *      disclaimer in the documentation and/or other materials provided 
 *      with the distribution. 
 *    -	Neither the name of RBCCPS, IISc Bangalore nor the names 
 *      of its contributors may be used to endorse or promote products 
 *      derived from this software without specific prior written 
 *      permission. 
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS 
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT 
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS 
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE 
 * COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, 
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, 
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; 
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER 
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT 
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN 
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 */

/**
 * TEDSDecoder
 * @author Swaminathan Vasanth Rajaraman (swaminathanvasanth.r@gmail.com)
 */

public class TEDSDecoder {

	public static String data = "";

	public static char decode(char c, int i) {
		// System.out.print("Binary is " +c);
		// System.out.print(" Char is " +c);
		data += "," + c;
		if (i <= 8)
			return ' ';
		else {
			int value = Integer.parseInt("" + c);
			return (char) value;
		}
	}

	public static String decode(String s, int i) {

		if (i <= 8)
			return " ";
		else {
			int value = Integer.parseInt(s);
			data += "," + (char) value;

			System.out.print("Binary is " + s + " - ");
			System.out.println("String is " + (char) value);
			return ("" + (char) value);
		}
	}
}
