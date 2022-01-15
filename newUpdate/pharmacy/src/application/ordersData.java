package application;

public class ordersData {

	private int order_id;
	private int empId;
	private String order_date;
	private String inshurance_company_name;
	
	public ordersData() {
		super();
	}

	public ordersData(int order_id, int empId, String order_date, String inshurance_company_name) {
		super();
		this.order_id = order_id;
		this.empId = empId;
		this.order_date = order_date;
		this.inshurance_company_name = inshurance_company_name;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public String getInshurance_company_name() {
		return inshurance_company_name;
	}

	public void setInshurance_company_name(String inshurance_company_name) {
		this.inshurance_company_name = inshurance_company_name;
	}
	
	
	
}
