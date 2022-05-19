package model;

public class Doctor extends Person {

	private String clinicName;

	public Doctor(String firstName, String lastName, String email, String clinicName) {
		super(firstName, lastName, email);
		this.clinicName = clinicName;
	}

	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

}
