package pl.riscosoftware.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import pl.riscosoftware.domain.Person;

public class PersonDTO {

	private UUID id;
	private String name;
	private String login;
	private String password;
	private int age;
	private BigDecimal salary;
	private Date birthdate;
	private List<PersonDTO> friends;
	private Set<PhoneDTO> phone = new HashSet<PhoneDTO>();

	
	public PersonDTO fromPerson(Person person) {
		this.id = person.getId();
		this.name = person.getName();
		this.login = person.getLogin();
		this.password = person.getPassword();
		this.age = person.getAge();
		this.salary = person.getSalary();
		this.birthdate = person.getBirthdate();
		
		if (person.getFriend() != null) {
			List<PersonDTO> listPersonDTO = new ArrayList<PersonDTO>();
			for (Person p : person.getFriend()) {
				listPersonDTO.add(new PersonDTO().fromPerson(p));
			}
			this.friends = listPersonDTO;
		}
		
		return this;
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public BigDecimal getSalary() {
		return salary;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public List<PersonDTO> getFriend() {
		return friends;
	}
	public void setFriend(List<PersonDTO> friend) {
		this.friends = friend;
	}
	public Set<PhoneDTO> getPhone() {
		return phone;
	}
	public void setPhone(Set<PhoneDTO> phone) {
		this.phone = phone;
	}
}
