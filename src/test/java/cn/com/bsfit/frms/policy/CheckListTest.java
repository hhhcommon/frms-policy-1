package cn.com.bsfit.frms.policy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckListTest {
	
	private static final String USERNAME = "FRMS_RAMS_DANDONG";
	private static final String PASSWORD = "bangsun";
	private static final String URL = "jdbc:oracle:thin:@10.100.1.85:1521:db1";
	
	public static void main(String[] args) {
		try {
			getChecklistResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ResultSet getChecklistResultSet() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM CHECKLIST");
			return rs;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
		return null;
	}
}
