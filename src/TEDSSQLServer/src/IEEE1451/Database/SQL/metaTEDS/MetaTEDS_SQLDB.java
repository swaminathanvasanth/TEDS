/*
 * 
 * $RCSfile: MetaTEDS_SQLDB.java $	
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
 * MetaTEDS_SQLDB
 * @author Swaminathan Vasanth Rajaraman (swaminathanvasanth.r@gmail.com)
 */

package IEEE1451.Database.SQL.metaTEDS;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
 
public class MetaTEDS_SQLDB {
 
	
	  private static Statement statement = null;
	  private static PreparedStatement preparedStatement = null;
	  private static ResultSet resultSet = null;

	
  public static void main(String UUID, String encodedTEDS) throws SQLException {

	  String _UUID = UUID;
	  String _encodedTEDS = encodedTEDS;
 
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
/*
	      preparedStatement = connection
		          .prepareStatement("DELETE '* from MetaTEDS");
		      preparedStatement.executeUpdate();*/
	      
	     preparedStatement = connection
		          .prepareStatement("DELETE from MetaTEDS where UUID='"+_UUID +"'");
		      preparedStatement.executeUpdate();

	      // preparedStatements can use variables and are more efficient
	      preparedStatement = connection
	          .prepareStatement("insert into MetaTEDS values (default, ?, ?)");
	      // "myuser, webpage, datum, summary, COMMENTS from FEEDBACK.COMMENTS");
	      // parameters start with 1

	      preparedStatement.setString(1, _UUID);
	      preparedStatement.setString(2, _encodedTEDS);
	      
	      preparedStatement.executeUpdate();
	   
	      System.out.println("Database Structure : [UUID: Binary TEDS]");
	      
	      writeResultSet(resultSet);
	      System.out
			.println("-------------------- Database --------------------");

	} else {
		System.out.println("Failed to make connection!");
	}
  }
  
  
  
  private static void writeResultSet(ResultSet resultSet) throws SQLException {
	    // resultSet is initialised before the first data set
	    while (resultSet.next()) {
	      // it is possible to get the columns via name
	      // also possible to get the columns via the column number
	      // which starts at 1
	      // e.g., resultSet.getSTring(2);
	      String UUID = resultSet.getString("UUID");
	      String binaryTEDS = resultSet.getString("binaryTEDS");
	      System.out.println("[" + UUID +":" + binaryTEDS);
	      // System.out.println("UUID: " + UUID);
	      // System.out.println("binaryTEDS: " + binaryTEDS);
	     }
	  }
  
  private static void writeMetaData(ResultSet resultSet) throws SQLException {
	    // now get some metadata from the database
	    System.out.println("The columns in the table are: ");
	    System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
	    for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
	      System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
	    }
	  }
}



