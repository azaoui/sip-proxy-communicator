package gov.nist.sip.bill;

import java.util.*;
	
public class TimeThreadController {
	static Map<Integer, Thread> m = new HashMap<Integer, Thread>();
	static Date start_time = null;
	
	public static Date Start(int i){
		if (!m.containsKey(i)){
			m.put(i, new CountTime(i));
			m.get(i).start();
		}
			Calendar cal = Calendar.getInstance();
			start_time = cal.getTime();
			return start_time;
	}

	public static Date getStartTime() {
		return start_time;
	}
	
	@SuppressWarnings("deprecation")
	public static Date Stop(int CallIdStr) {
		Date end_time = null;
		if (!m.isEmpty()) {
			m.get(CallIdStr).stop();
			Calendar cal = Calendar.getInstance();
			end_time = cal.getTime();
			System.out.println("STOPPED THREAD @ " + end_time + " id = " + CallIdStr);
			m.clear();
		}
		return end_time;
	}
}
