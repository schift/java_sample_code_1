package pl.riscosoftware.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Entity;

@Entity
public class Person {

//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "id")
	private UUID id;
	private final String name;
	private final String login;
	private final String password;
	private final int age;
	private final BigDecimal salary;
	private final Date birthdate;
	private final List<Person> friend;
	
	private Person(Builder builder) {
		//this.id = builder.id;
		this.id = UUID.randomUUID();
		this.name = builder.name;
		this.login = builder.login;
		this.password = builder.password;
		this.age = builder.age;
		this.salary = builder.salary;
		this.birthdate = builder.birthdate;
		this.friend = builder.friend;
		//this.phone = builder.phone;
	}
	
	// has been used builder pattern
	@SuppressWarnings("unused")
	public static class Builder {
		private long id;
		private String name;
		private String login;
		private String password;
		private int age;
		private BigDecimal salary;
		private Date birthdate;
		private List<Person> friend;
		//private Set<Phone> phone;
		
		public Builder() {}
		
		public Builder id(long id) {
			//this.id = id;
			return this;
		}
		
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		
		public Builder login(String login) {
			this.login = login;
			return this;
		}
		
		public Builder password(String password) {
			this.password = password;
			return this;
		}
		
		public Builder age(int age) {
			this.age = age;
			return this;
		}
		
		public Builder salary(BigDecimal salary) {
			this.salary = salary;
			return this;
		}
		
		public Builder birthdate(Date birthday) {
			this.birthdate = birthday;
			return this;
		}
		
		public Builder friend(List<Person> friend) {
			this.friend = friend;
			return this;
		}
		
//		public Builder phone(Set<Phone> phone) {
//			this.phone = phone;
//			return this;
//		}
		
		public Person build() {
			return new Person(this);
		}
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
	
	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public int getAge() {
		return age;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public Date getBirthdate() {
		return birthdate;
	}
	
	public List<Person> getFriend() {
		return friend;
	}
	
}