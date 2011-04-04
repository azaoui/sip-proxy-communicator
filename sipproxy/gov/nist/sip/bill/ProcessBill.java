package gov.nist.sip.bill;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class ProcessBill {
	public void updateCallDB(Statement sql, int UserFromid, int UserToid, Date Start, Date End, int cost){
		try {
			sql.executeUpdate("insert into call (caller, callee, start_date, end_date, cost) values (" + UserFromid + "," + UserToid + "," + Start + "," + End + "," + cost + ")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
