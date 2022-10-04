
public class Operator {
	private int id;
    private String operatorName, operatorUsername, operatorPassword, operatorEmail;
    private boolean checkedSupervisor;


	public Operator(int id, String operatorName, String operatorUsername, String operatorPassword,String operatorEmail) {
		super();
		this.id = id;
		this.operatorName = operatorName;
		this.operatorUsername = operatorUsername;
		this.operatorPassword = operatorPassword;
		this.operatorEmail= operatorEmail;
		checkedSupervisor=false;
	}
	
	
	
	
	public boolean isCheckedSupervisor() {
		return checkedSupervisor;
	}




	public void setCheckedSupervisor(boolean checkedSupervisor) {
		this.checkedSupervisor = checkedSupervisor;
	}




	public String getOperatorEmail() {
		return operatorEmail;
	}



	public void setOperatorEmail(String operatorEmail) {
		this.operatorEmail = operatorEmail;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOperatorUsername() {
		return operatorUsername;
	}

	public void setOperatorUsername(String operatorUsername) {
		this.operatorUsername = operatorUsername;
	}

	public String getOperatorPassword() {
		return operatorPassword;
	}

	public void setOperatorPassword(String operatorPassword) {
		this.operatorPassword = operatorPassword;
	}

}
