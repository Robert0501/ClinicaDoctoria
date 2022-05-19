package model;

public class Address {

	private String country;
	private String city;
	private String street;
	private String address;

	public Address(String country, String city, String street, String address) {
		this.country = country;
		this.city = city;
		this.street = street;
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}