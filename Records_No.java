package com.test;

//import java.util.concurrent.TimeUnit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class Records_No {

	final static Logger logger = Logger.getLogger(Records_No.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection c = null;
		Statement stmt = null;
		int count = 1;
		try {
			Class.forName("org.postgresql.Driver");

			// adding url, user and password
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "postgres", "123456");

			c.setAutoCommit(false);

			stmt = c.createStatement();

			// passing the query and adding it on resultset
			ResultSet rs = stmt.executeQuery("SELECT * FROM collegedata;");
			
			//storing the time in milliseconds
			long startTime = System.currentTimeMillis();
			long endTime = 0;

			while (rs.next()) {
				int id = rs.getInt("student_id");
				String name = rs.getString("student_name");
				String gender = rs.getString("student_gender");
				String email = rs.getString("student_emailid");
				
				//fetching the data and storing in the log file
				logger.info("ID = " + id + " , NAME = " + name + " , GENDER = " + gender + " , EMAIL = " + email);
				count++;
			}
			
			
			endTime = System.currentTimeMillis();

			//storing the time in minute and second
			long minutes = (-1) * ((startTime - endTime) / 1000) / 60;
			long seconds = (-1) * ((startTime - endTime) / 1000) % 60;

			logger.info("Total Time taken : Minutes -> " + minutes + " and, Second -> " + seconds);

			rs.close();
			stmt.close();
			c.close();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getClass().getName() + ": " + e.getMessage());
			// System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		logger.info("No of records : " + count);
	}

}
