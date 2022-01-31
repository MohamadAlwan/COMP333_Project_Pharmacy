package application;

public class inshuranceData {
	private int customerID;
	private String coustumerName;
	private String inshurance_companyName;
	
	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getCoustumerName() {
		return coustumerName;
	}

	public void setCoustumerName(String coustumerName) {
		this.coustumerName = coustumerName;
	}

	public String getInsurance_companyName() {
		return inshurance_companyName;
	}

	public void setInsurance_companyName(String insurance_companyName) {
		this.inshurance_companyName = insurance_companyName;
	}

	public inshuranceData(int customerID, String coustumerName, String insurance_companyName) {
		super();
		this.customerID = customerID;
		this.coustumerName = coustumerName;
		this.inshurance_companyName = insurance_companyName;
	}
	
	
	
}
