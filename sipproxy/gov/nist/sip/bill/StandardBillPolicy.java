package gov.nist.sip.bill;

import java.math.BigDecimal;

import com.sun.jmx.snmp.Timestamp;

public class StandardBillPolicy implements BillingStrategy {

	@Override
	public BigDecimal calculate(int UserFrom_id, int UserToId, long startTime,
			long endTime) {
		// TODO Auto-generated method stub
		java.sql.Timestamp st = new java.sql.Timestamp(startTime);
		java.sql.Timestamp et = new java.sql.Timestamp(endTime);
		int sh = st.getHours();
		int sm = st.getMinutes();
		int ss = st.getSeconds();
		
		int th = et.getHours();
		int tm = et.getMinutes();
		int ts = et.getSeconds();
		
		System.out.println("START TIME" +  st.toString());
		System.out.println("end TIME" +  et.toString());
		
		int s_time = sh*3600 + sm*60 + ss;
		int e_time = th*3600 + tm*60 + ts;
		
		int total_time = e_time - s_time;
		System.out.println("TOTAL TIME" + total_time);
		
		double cost = total_time*0.1;
		BigDecimal final_cost = new BigDecimal(cost);
		return final_cost;
	}

}
