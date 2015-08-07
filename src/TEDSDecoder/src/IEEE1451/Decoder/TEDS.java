package IEEE1451.Decoder;
/*
 * 
 * $RCSfile: TEDS.java $	
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
 * TEDS
 * @author Swaminathan Vasanth Rajaraman (swaminathanvasanth.r@gmail.com)
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;


public class TEDS {

	 private static Statement statement = null;
     private static PreparedStatement preparedStatement = null;
	 private static ResultSet resultSet = null;
	 static int [] ibinaryArray = null;
	 static String binaryTEDS;
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		// Connect to the SQL Server and fetch the binaryTEDS based on UUID // 
		
		System.out.println("Connecting to MYSQL .....");
		  
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}
	 
		System.out.println("Registered and Connected to MySQL .....");
		Connection connection = null;
	 
		try {
			connection = DriverManager
			.getConnection("jdbc:mysql://localhost:3306/TEDS?"+ "user=TEDSManager&password=TEDSPWD");

	 
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
	 
		if (connection != null) {
			System.out
			.println("\n-------------------- Database --------------------");

			 // statements allow to issue SQL queries to the database
		      statement = connection.createStatement();
		      // resultSet gets the result of the SQL query

		      preparedStatement = connection
			          .prepareStatement("SELECT * from MetaTEDS");
			      resultSet = preparedStatement.executeQuery();
			      while (resultSet.next()) {
				        String UUID = resultSet.getString("UUID");
				      String binaryTEDS = resultSet.getString("binaryTEDS");
				      System.out.println("[" + UUID +":" + binaryTEDS);
				     }
			      System.out
					.println("-------------------- Database --------------------");

		      preparedStatement = connection
		          .prepareStatement("select binaryTEDS from MetaTEDS where UUID ='000000144F01000061DB'");
		      
		      resultSet = preparedStatement.executeQuery();	
		      while (resultSet.next()) {
		      binaryTEDS = resultSet.getString("binaryTEDS");
		      
		      System.out.println("\nQuerying binary TEDS for UUID ='000000144F01000061DB'");
		      System.out.println("...");
			  System.out.println("...");
			  System.out.println("Obtained binary TEDS for UUID ='000000144F01000061DB'");
			  System.out.println(binaryTEDS);
		      
		      System.out.println("\nDecoding binary TEDS for UUID ='000000144F01000061DB'");
		      System.out.println("...");
			  System.out.println("...");
		      String[] sbinaryArray = binaryTEDS.replaceAll("\\[", "").replaceAll("\\]", "").split(",");

		      ibinaryArray = new int[sbinaryArray.length];

		      for (int i = 0; i < sbinaryArray.length; i++) {
		          try {
		        	  ibinaryArray[i] = Integer.parseInt(sbinaryArray[i]);
		          } catch (NumberFormatException nfe) {};
		      }
		      
		     // System.out.println(Arrays.toString(ibinaryArray));
		      
		      }
		
		     
		    // - Test Set -1 //
			//int [] binaryArray = new int [] {0,0,0,42,10,1,49,128,1,65,4,20,48,48,48,48,48,48,49,52,52,70,48,49,48,48,48,48,54,49,68,66,3,1,49,13,1,49,12,4,49,46,50,51,249,106};
			  
			// - Test Set -2 //
			// binaryArray= new int [] {0,0,0,132,10,11,49,46,50,51,52,53,54,55,56,57,48,128,81,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,32,44,46,47,59,58,39,34,91,123,93,125,92,124,96,126,33,64,35,36,37,94,42,40,41,95,45,61,43,4,20,48,48,48,48,48,48,49,52,52,70,48,49,48,48,48,48,54,49,68,66,3,1,49,13,1,49,12,4,49,46,50,51,220,136};
			
			// - Use for Random Test -
			// int [] binaryArray = new int [] {0,0,0,113,10,15,49,49,50,115,100,115,97,100,115,97,100,115,97,100,49,128,32,74,97,121,32,72,105,32,33,32,115,100,115,100,115,97,100,115,100,115,100,102,103,104,106,121,121,114,116,121,106,104,46,4,31,48,48,48,48,48,48,49,52,52,70,48,49,48,48,48,48,54,49,68,66,115,100,115,100,115,100,115,115,100,115,100,3,3,49,50,49,13,14,49,50,51,51,100,115,97,100,115,100,115,97,100,50,12,4,49,46,50,51,222,178};
		}	
		
		
		
		RuntimeBinaryArraySplitter arraySplitter = new RuntimeBinaryArraySplitter();
		arraySplitter.split(ibinaryArray);
	}
}
