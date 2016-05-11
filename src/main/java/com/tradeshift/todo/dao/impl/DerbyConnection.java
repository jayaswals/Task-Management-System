package com.tradeshift.todo.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

@Component("connection")
public class DerbyConnection {

	private Connection conn;
	
	@PostConstruct
	private void init(){
		try{
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
			String os = System.getProperty("os.name");
			if(os.contains("Mac")){
				this.conn= DriverManager.getConnection("jdbc:derby:\\opt\\TSDB\\ts;create=true");
			}else if(os.contains("Windows")){
				this.conn = DriverManager.getConnection("jdbc:derby:c:\\opt\\TSDB\\ts;create=true");
			}else{
				this.conn= DriverManager.getConnection("jdbc:derby:\\opt\\TSDB\\ts;create=true");
			}
			
			Statement stmt = conn.createStatement();
//			stmt.executeUpdate("Create table users (user_id int primary key,user_guid varchar(30), user_name varchar(30))");
//			stmt.executeUpdate("Create table tasks (task_id int primary key,task_name varchar(200), created_by int, owner_id int, is_complete int)");		
		
			stmt.executeUpdate("Create table users (user_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 100, INCREMENT BY 1),user_guid varchar(30) NOT NULL, user_name varchar(30), CONSTRAINT primary_key PRIMARY KEY (user_id))");
			stmt.executeUpdate("Create table tasks (task_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 100, INCREMENT BY 1),task_name varchar(200) NOT NULL, created_by int NOT NULL, owner_id int, is_complete int, CONSTRAINT primary_key1 PRIMARY KEY (task_id))");		
		
//			stmt.executeUpdate("insert into users values (1,'qwerty','Sam')");
//			stmt.executeUpdate("insert into users values (2,'poiuy','Peter')");
//			stmt.executeUpdate("insert into users values (3,'asdfghjkl','Mary')");
//			stmt.executeUpdate("insert into users values (4,'mnbvcxz','John')");
			stmt.executeUpdate("insert into users(user_guid, user_name) values ('qwerty','Sam')");
			stmt.executeUpdate("insert into users(user_guid, user_name) values ('poiuy','Peter')");
			stmt.executeUpdate("insert into users(user_guid, user_name) values ('asdfghjkl','Mary')");
			stmt.executeUpdate("insert into users(user_guid, user_name) values ('mnbvcxz','John')");
			
			/*String userGuid = "qwerty";
			
			ResultSet  rs = stmt.executeQuery("Select * from Users where user_guid='"+userGuid+"'");
			while(rs.next()){
				System.out.println("#########"+rs.getLong(1));
			}*/
			stmt.close();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}

	@PreDestroy
	private void dropTables() {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("drop table users");
			stmt.executeUpdate("drop table tasks");
			this.conn.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @return the conn
	 */
	public Connection getConn() {
		return this.conn;
	}
	
	
	
}
