

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	private static final String Driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/my_db";
	private static final String id = "root";
	private static final String pw = "root";
	static { // static으로 되어 있으면 위에있는 것이 먼저 실행이 된다
		try {
			Class.forName(Driver);
			System.out.println("드라이버 로드");
		} catch (ClassNotFoundException e) {
			System.out.println("문제있음");
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, id, pw);
	}
}
