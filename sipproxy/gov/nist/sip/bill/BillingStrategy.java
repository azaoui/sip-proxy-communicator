package gov.nist.sip.bill;

import java.math.BigDecimal;

public interface BillingStrategy {
	BigDecimal calculate(int UserFrom_id, int UserToId, long start_time, long end_time);
}
