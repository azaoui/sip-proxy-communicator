package gov.nist.sip.bill;

import gov.nist.sip.db.DbConnection;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import org.postgresql.util.PGmoney;

public class ProcessBill {
	public static void updateCallDB(String FromUser, String ToUser, long l, long endTime, BigDecimal cost){
		try {
			Statement sql = DbConnection.getSql();
			int ToUserID = DbConnection.findUserID(ToUser);
			int FromUserID = DbConnection.findUserID(FromUser);
			java.sql.Timestamp start_time = new java.sql.Timestamp(l);
			java.sql.Timestamp end_time = new java.sql.Timestamp(endTime);
			
			sql.executeUpdate("insert into call (caller, callee, start_time, end_time, cost) values (" + FromUserID + "," + ToUserID + ",'" + start_time + "','" + end_time + "'," + cost + ")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}