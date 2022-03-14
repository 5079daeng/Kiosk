import java.time.LocalDate;
import java.util.Date;

public class Member {
	String PhoneNumber;
	String StartDate;
	int TicketPeriod;
	int seat;

	public Member(String phoneNumber, String startDate, int ticketPeriod, int seat) {
		super();
		PhoneNumber = phoneNumber;
		StartDate = startDate;
		TicketPeriod = ticketPeriod;
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

	public int getTicketPeriod() {
		return TicketPeriod;
	}

	public void setTicketPeriod(int ticketPeriod) {
		TicketPeriod = ticketPeriod;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	@Override
	public String toString() {
		return "Member [PhoneNumber=" + PhoneNumber + ", StartDate=" + StartDate + ", TicketPeriod=" + TicketPeriod
				+ ", seat=" + seat + "]";
	}

}
