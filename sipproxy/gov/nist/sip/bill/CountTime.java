package gov.nist.sip.bill;

import java.util.Calendar;
import java.util.Date;

import javax.sip.header.Header;

public class CountTime extends Thread {
	String callIdStr;
	Date start_time  = null;
	
	public CountTime(String callIdStr2) {
		// TODO Auto-generated constructor stub
		callIdStr = callIdStr2;
		Calendar cal = Calendar.getInstance();
		start_time = cal.getTime();
		System.out.println("STARTED @ " + start_time + " call id : " + callIdStr );
	}

	public String getCallIdStr(){
		return callIdStr;
	}
	
	public Date getStartTime(){
		return start_time;
	}
	
	public void run() {
		while (true) {
			try {
				CountTime.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Calendar cal = Calendar.getInstance();
			System.out.println(cal.getTime() + " call id : " + callIdStr);
		}
	}
}
