import java.time.LocalDate;
import java.util.Date;

public class Member {
	String PhoneNumber;
	String StartDate;
	String lastDate;
	int seat;
	public Member(String phoneNumber, String startDate, String lastDate, int seat) {
		super();
		PhoneNumber = phoneNumber;
		StartDate = startDate;
		this.lastDate = lastDate;
		this.seat = seat;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public String getStartDate() {
		return StartDate;
	}
	public void setStartDate(String startDate) {
		StartDate = startDate;
	}
	public String getLastDate() {
		return lastDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
	public int getSeat() {
		return seat;
	}
	public void setSeat(int seat) {
		this.seat = seat;
	}
	@Override
	public String toString() {
		return "Member [PhoneNumber=" + PhoneNumber + ", StartDate=" + StartDate + ", lastDate=" + lastDate + ", seat="
				+ seat + "]";
	}

}
