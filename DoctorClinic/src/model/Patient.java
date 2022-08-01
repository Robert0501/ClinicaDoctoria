package model;

public class Patient extends Person {

	private String phoneNumber;
	private String CNP;
	private String dateOfBirth;
	private String country;
	private String address;

	public Patient(String firstName, String lastName, String CNP, String dateOfBirth, String phoneNumber, String email,
			String country, String address) {
		super(firstName, lastName, email);
		this.CNP = CNP;
		this.dateOfBirth = dateOfBirth;
		this.phoneNumber = phoneNumber;
		this.country = country;
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCNP() {
		return CNP;
	}

	public void setCNP(String cNP) {
		CNP = cNP;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
