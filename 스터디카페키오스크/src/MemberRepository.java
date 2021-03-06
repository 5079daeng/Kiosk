
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.channels.ConnectionPendingException;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;

import javax.swing.ImageIcon;

import com.mysql.cj.protocol.Resultset;

public class MemberRepository {

	public Member selectMember(String s) {
		String query = "Select * from member where PhoneNumber = ? ";
		Member member = null;
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);) {
			stmt.setString(1, s);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String phoneNumber = rs.getString("PhoneNumber");
				String startDate = rs.getString("StartDate");
				String lastDate = rs.getString("lastDate");
				int seat = rs.getInt("selectedSeat");
				member = new Member(phoneNumber, startDate, lastDate, seat);
			}

			return member;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return member;
	}

	public int joinMember(Member m) {

		String query = "insert into member (PhoneNumber, Startdate ,lastDate, selectedSeat )"
				+ " values(? ,now() ,?,?);";

		int result = 0;
		try (Connection conn = ConnectionProvider.getConnection(); PreparedStatement ps = conn.prepareStatement(query);

		) {
			ps.setString(1, m.getPhoneNumber());
			ps.setString(2, m.getLastDate());
			ps.setInt(3, m.getSeat());

			result = ps.executeUpdate();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public boolean checkSelectedSeat(int k) {

		String query = " select * from member where selectedSeat = ?";
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);) {
			stmt.setInt(1, k);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int answer = rs.getInt("selectedSeat");
				System.out.print(answer);
				if (answer > 0) {
					return true;
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public void updateSeat(String s, String p) {
		String query = "update  member set selectedSeat = ? where phonenumber = ?";

		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, Integer.valueOf(s));
			stmt.setString(2, p);
			stmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public String getPhoneNumber(String s) {
		String a = null;
		String query = "select PhoneNumber from member where selectedSeat = ? ";
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, s);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				a = rs.getString("phoneNumber");
			}
			return a;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;

	}

	public String check(String s) {
		String a = null;
		String select = "select phoneNumber from member where phonenumber = ? ;";
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement stmt = conn.prepareStatement(select);) {
			stmt.setString(1, s);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				a = s;
			}
			return a;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	public int checkMemberSeat(String phoneNumber) {
		int a = -1;
		String query = "select selectedSeat from member where phonenumber = ?";
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);) {
			stmt.setString(1, phoneNumber);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				a = rs.getInt("selectedseat");
			}
			return a;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;

	}

	public String checkTicket(String phoneNumber) {
		String query = "select lastDate from member where phonenumber = ?";
		String lastDate = null;
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				lastDate = rs.getString("lastDate");

			}
			return lastDate;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lastDate;

	}

	public void deleteMember() {
		String query = "delete * from memeber where ((select dateDiff(select DATE_ADD(startdate, INTERVAL ticket_Period DAY) , now())) < 0 );";
	}

	public void newUpdateTicketPeriod(int days) {
		String query = "update member set lastDate = (DATE_ADD(startdate, INTERVAL ? DAY)) ";
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);) {
			stmt.setInt(1, days);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void oldupdateTicketPeriod(int days, String phoneNumber) {
		String query = "update member set lastDate = (DATE_ADD(lastdate, INTERVAL ? DAY)) where phoneNumber = ? ";
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);) {
			stmt.setInt(1, days);
			stmt.setString(2, phoneNumber);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
