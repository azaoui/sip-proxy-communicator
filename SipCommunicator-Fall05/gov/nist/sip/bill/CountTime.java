package gov.nist.sip.bill;

import java.util.Calendar;

import javax.sip.header.Header;

public class CountTime extends Thread {
	int callIdStr;
	
	public CountTime(int callIdStre) {
		// TODO Auto-generated constructor stub
		callIdStr = callIdStre;
		Calendar cal = Calendar.getInstance();
		System.out.println("STARTED @ " + cal.getTime() + " call id : " + callIdStr );
	}

	public int getCallIdStr(){
		return callIdStr;
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
