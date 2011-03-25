package gov.nist.sip.block;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import gov.nist.sip.db.DbConnection;

public class block {
	void BlockUser(String FromUser, String ToUser) {

	}

	void UnblockUser(String FromUser, String ToUser) {
		// Unblock a user by updating the block table in db
	}

	//Check if a user is blocked(0) or not (1)
	public static boolean CheckBlock(String FromUser, String ToUser) throws SQLException {
		Statement sql = DbConnection.getSql();
		int ToUserID = DbConnection.findUserID(ToUser);
		int FromUserID = DbConnection.findUserID(FromUser);
		
		ResultSet result = sql.executeQuery("select * from block where blocker =" + ToUserID +
				" and blockee =" + FromUserID);
		
		if (result.next()){
			result.close();
			return false;
		} else {
			result.close();
			return true;
		}
	}
}
