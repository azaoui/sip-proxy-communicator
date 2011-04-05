package gov.nist.sip.bill;

import java.util.*;
	
public class TimeThreadController {
	static Map<String, Thread> m = new HashMap<String, Thread>();
	static long start_time = 0;
	
	public static long Start(String callIdStr){
		if (!m.containsKey(callIdStr)){
			m.put(callIdStr, new CountTime(callIdStr));
			m.get(callIdStr).start();
		}
		//	Calendar cal = Calendar.getInstance();
			java.util.Date cal = new java.util.Date();
			start_time = cal.getTime();
			return start_time;
	}

	public static long getStartTime() {
		return start_time;
	}
	
	@SuppressWarnings("deprecation")
	public static long Stop(String callIdStr) {
		long end_time = 0;
		if (!m.isEmpty()) {
			m.get(callIdStr).stop();
		//	Calendar cal = Calendar.getInstance();
			java.util.Date cal = new java.util.Date();
			end_time = cal.getTime();
			System.out.println("STOPPED THREAD @ " + end_time + " id = " + callIdStr);
			m.clear();
		}
		return end_time;
	}
}
