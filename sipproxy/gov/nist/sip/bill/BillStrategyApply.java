package gov.nist.sip.bill;

import java.math.BigDecimal;

public class BillStrategyApply {
	private BillingStrategy billngstrategy;
	
	public BillStrategyApply(BillingStrategy billingStrategy) {
		this.billngstrategy = billingStrategy;
	}
	
	public BigDecimal executeStrategy(int UserFrom_id, int UserToId, long start_time, long end_time) {
		return billngstrategy.calculate(UserFrom_id, UserToId, start_time, end_time);
	}

}
