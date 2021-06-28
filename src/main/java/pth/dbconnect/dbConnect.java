package pth.dbconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class dbConnect {

	public static String dbUrl = "jdbc:mysql://pthapp.co.in:3306/pthapp_wp2";
	public static String username = "pthapp_wp2";
	public static String password = "O.B9vAiYOUgsutWanq019";
	public static String dbDriver = "com.mysql.jdbc.Driver";

	static Connection conn;
	static Statement stat;
	static ResultSet set;
	static ResultSetMetaData meta;
	
	
	public String connectDB() {
		
		String value = "";
		
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, username,password);
			stat = conn.createStatement();
			set = stat.executeQuery("select ID,post_title,post_status,ping_status,post_name from wp_posts where ID=17");
			meta = set.getMetaData();
			int dbColumnCount = meta.getColumnCount();
			while(set.next()) {
//				String value = set.getString(1);
//				System.out.println(value);
				for(int i=1; i<=dbColumnCount; i++) {
					value = set.getString(meta.getColumnName(i));
				}

			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return value;
	}
	
	
}
