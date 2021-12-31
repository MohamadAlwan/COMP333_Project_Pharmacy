package application;

public class Employeedata {
	private int id;
	private String employee_name;
	private String birthday;
	private String date_of_employment;
	private String emp_password;

	public Employeedata() {
		super();
	}

	public Employeedata(String employee_name, String birthday, String date_of_employment, String emp_password) {
		super();
		this.employee_name = employee_name;
		this.birthday = birthday;
		this.date_of_employment = date_of_employment;
		this.emp_password = emp_password;
	}

	public int getId() {
		return id;
	}

	public String getEmp_password() {
		return emp_password;
	}

	public void setEmp_password(String emp_password) {
		this.emp_password = emp_password;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getDate_of_employment() {
		return date_of_employment;
	}

	public void setDate_of_employment(String date_of_employment) {
		this.date_of_employment = date_of_employment;
	}

}
