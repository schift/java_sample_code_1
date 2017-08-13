package pl.riscosoftware.dto;

import pl.riscosoftware.domain.Phone;

public class PhoneDTO {

	private String name;
	private String phoneNumber;
	
	public PhoneDTO fromPhone(Phone phone) {
		this.name = phone.getName();
		this.phoneNumber = phone.getPhoneNumber();
		return this;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
