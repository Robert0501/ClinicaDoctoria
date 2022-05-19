package model;

public class User {

	private String email;
	private String password;
	private String activationCode;
	private boolean isActivated;

	public User(String email, String password, String activationCode) {
		this.email = email;
		this.password = password;
		this.activationCode = activationCode;
		this.isActivated = false;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public boolean isActivated() {
		return isActivated;
	}

	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}

}
