package MRSConfig;
import java.util.*;
import java.sql.*;
import java.io.*;
public class DBconfig {
 private static Connection conn;
 private static PreparedStatement stmt;
 private static ResultSet rs;
 private static DBconfig db=null;
 private  DBconfig() {
	try {
		
		Properties p=new Properties();
		p.load(PathHelper.fin);
//		System.out.println(PathHelper.fin);
		String DriverClassName=p.getProperty("driver.classname");
//		System.out.println(DriverClassName);
		String username=p.getProperty("db.username");
		String password=p.getProperty("db.password");
		String url=p.getProperty("db.url");
		Class.forName(DriverClassName);
		conn=DriverManager.getConnection(url,username,password);
		if(conn!=null) {
			System.out.println("Database connected");
		}else {
			System.out.println("Some problem to connect DB");
		}
	}catch(Exception ex) {
		System.out.println("error is: "+ex);
	}

}
	public static DBconfig getDBInstance() {
		if(db==null) {
			db=new DBconfig();
		}
//			System.out.println("Db is Created:"+db);
			return db;
		
	}
	public static Connection getconnection() {
		return conn;
	}
	public static PreparedStatement getStatement() {
		return stmt;
	}
	public static ResultSet getResultSet() {
		return rs;
	}
}
