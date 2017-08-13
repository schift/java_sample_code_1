package pl.riscosoftware.service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.riscosoftware.domain.Person;
import pl.riscosoftware.domain.Phone;

@Component
public class InitWithDataService {

	private static final Logger logger = LoggerFactory.getLogger(InitWithDataService.class);
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private List<Person> persons = new ArrayList<Person>();
	private List<Phone> phones = new ArrayList<Phone>();
	
	@Autowired
	private CryptographyService cryptographyService;
	
	public void doFill() throws Exception {
		logger.info("fill with test data starting..");
		//PrivateKey privateKey = cryptographyService.getPrivate("privateKey");
		
		try {
			List<Person> friends = new ArrayList<Person>();
			friends.add(new Person.Builder()
					.age(21)
					.birthdate(sdf.parse("22/03/1978"))
					.name("Jacek Grabarczyk")
					.login("jgrabarczyk")
					// .password(cryptographyService.encryptText("tt55sdf", privateKey))
					.password("tt55sdf")
					.salary(new BigDecimal("1.700"))
					.build());
			friends.add(new Person.Builder()
					.age(48)
					.birthdate(sdf.parse("11/11/1945"))
					.name("Janusz Wolski")
					.login("jwolski")
					.password("kaz99bb")
					.salary(new BigDecimal("7.700"))
					.build());
			friends.add(new Person.Builder()
					.age(25)
					.birthdate(sdf.parse("23/07/1986"))
					.name("Marta Bielan")
					.login("mbielan")
					.password("kob11l")
					.salary(new BigDecimal("2.100"))
					.build());
			
			// 0
			persons.add(new Person.Builder()
					//.id(1)
					.age(33)
					.birthdate(sdf.parse("17/07/1989"))
					.name("Jan Kowalski")
					.login("jkowalski")
					.password("pass123456")
					.salary(new BigDecimal("6.700"))
					.friend(new ArrayList<Person>(Arrays.asList(friends.get(0))))
					.build());
			
			// 1
			persons.add(new Person.Builder()
					.age(33)
					.birthdate(sdf.parse("13/01/1977"))
					.name("Piotr Nowak")
					.login("pnowak")
					.password("haslo9")
					.salary(new BigDecimal("5.100"))
					.friend(new ArrayList<Person>(Arrays.asList(friends.get(1))))
					.build());
			
			// 2
			persons.add(new Person.Builder()
					.age(33)
					.birthdate(sdf.parse("21/12/1970"))
					.name("Ania Wrona")
					.login("awrona")
					.password("pp190c")
					.salary(new BigDecimal("3.400"))
					.friend(new ArrayList<Person>(Arrays.asList(friends.get(2), friends.get(0))))
					.build());
			
			// 3
			persons.add(new Person.Builder()
					.age(33)
					.birthdate(sdf.parse("19/09/1983"))
					.name("Adam Nowak")
					.login("anowak")
					.password("fff999")
					.salary(new BigDecimal("1.500"))
					.friend(new ArrayList<Person>(Arrays.asList(friends.get(1), friends.get(2))))
					.build());
			
			// 4
			persons.add(new Person.Builder()
					.age(33)
					.birthdate(sdf.parse("12/12/1971"))
					.name("Jacek Kowal")
					.login("jkowal")
					.password("hou99x")
					.salary(new BigDecimal("4.100"))
					.build());
			
			// 5
			persons.add(new Person.Builder()
					.age(33)
					.birthdate(sdf.parse("01/12/1980"))
					.name("Anna Kowalczyk")
					.login("akowalczyk")
					.password("hou99x")
					.salary(new BigDecimal("2.500"))
					.build());
			
			// 6
			persons.add(new Person.Builder()
					.age(33)
					.birthdate(sdf.parse("01/12/1980"))
					.name("Jadwiga Prom")
					.login("jprom")
					.password("000sa")
					.salary(new BigDecimal("9.000"))
					.friend(new ArrayList<Person>(Arrays.asList(friends.get(2), friends.get(0), friends.get(1))))
					.build());
			
			logger.info("data has been initialized, persons size is now: " + persons.size());

			this.fillWithPhones();

			
		} catch (ParseException e) {
			logger.error(e.toString());
		}
	}

	private void fillWithPhones() {
		// ----------- phones -------------- //
		Phone p1 = new Phone("private", "+3381499109", persons.get(5));
		phones.add(p1);

		Phone p2 = new Phone("private", "+48899111120", persons.get(1));
		phones.add(p2);
		
		Phone p3 = new Phone("work", "501901122", persons.get(6));
		phones.add(p3);
		
		Phone p4 = new Phone("private", "+3381499109", persons.get(1));
		phones.add(p4);
		
		Phone p5 = new Phone("home", "799010122", persons.get(2));
		phones.add(p5);
		
		Phone p6 = new Phone("private", "609991132", persons.get(4));
		phones.add(p6);
		
		Phone p7 = new Phone("private", "+34833122341", persons.get(3));
		phones.add(p7);
		
		Phone p8 = new Phone("work", "900811233", persons.get(3));
		phones.add(p8);
		
		Phone p9 = new Phone("home", "+12814900122", persons.get(4));
		phones.add(p9);

		logger.info("data has been initialized, phones size is now: " + phones.size());
	}
	
	public List<Person> getPersons() {
		return persons;
	}

	public List<Phone> getPhones() {
		return phones;
	}
}
