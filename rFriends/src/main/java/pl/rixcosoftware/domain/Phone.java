package pl.riscosoftware.domain;

import javax.persistence.Entity;

@Entity
public class Phone {

	private String name;
	private String phoneNumber;
	private Person person;
	
	private Phone() {}
	public Phone(String name, String phoneNumber, Person person) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.person = person;
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
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
}
