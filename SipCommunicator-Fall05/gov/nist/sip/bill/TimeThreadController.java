package gov.nist.sip.bill;

import java.util.*;
	
public class TimeThreadController {
	static Map<Integer, Thread> m = new HashMap<Integer, Thread>();
	
	public static void Start(int i){
		if (!m.containsKey(i)){
			m.put(i, new CountTime(i));
			m.get(i).start();
		}
	}
	
	public static void Stop(int CallIdStr) {
		if (m.get(CallIdStr).isAlive()) {
			m.get(CallIdStr).stop();
			Calendar cal = Calendar.getInstance();
			System.out.println("STOPPED THREAD @ " + cal.getTime() + " id = " + CallIdStr);
			m.clear();
		}
	}
}
