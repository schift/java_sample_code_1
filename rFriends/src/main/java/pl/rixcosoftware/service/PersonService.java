package pl.riscosoftware.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.riscosoftware.domain.Person;
import pl.riscosoftware.domain.Phone;
import pl.riscosoftware.dto.PersonDTO;
import pl.riscosoftware.dto.PhoneDTO;
import pl.riscosoftware.dto.ResultDTO;
import pl.riscosoftware.err.handler.PersonException;

@Service
public class PersonService {

	@Autowired
	private InitWithDataService initWithDataService;
	
	
	public ResultDTO getPersonList(Integer start, Integer offset, Boolean sorted, String login, String p_phone) throws PersonException {
		
		List<Person> persons = initWithDataService.getPersons();
		List<Phone> phones = initWithDataService.getPhones();
		int totalCount = persons.size();
		
		if (persons == null || persons.isEmpty()) {
			throw new PersonException("persons list is empty");
		}
		
		if (sorted != null && sorted) {
			Collections.sort(persons, new Comparator<Person>() {
				public int compare(Person o1, Person o2) {
					return o1.getSalary().compareTo(o2.getSalary());
				}
			});
		}
		
		if (offset != null && start != null) {
			if (offset <= persons.size() && offset > 0 && offset >= start) {
				persons = persons.subList(start, offset);
			} else {
				throw new PersonException("pagination out of range");
			}
		}
		
		List<PersonDTO> result = new ArrayList<PersonDTO>();
		if (StringUtils.isNotEmpty(login) || StringUtils.isNotEmpty(p_phone)) {
			for (Person person : persons) {
				if (StringUtils.isNotEmpty(login) && (person.getLogin().equals(login) || person.getLogin().startsWith(login))) {
					if (result.stream().filter(pdto -> pdto.getId().equals(person.getId())).findAny().orElse(null) == null) {
						result.add(new PersonDTO().fromPerson(person));
					}
				}
				if (phones != null && !phones.isEmpty())
					for (Phone phone : phones) {
						if (StringUtils.isNotEmpty(p_phone) && (phone.getPhoneNumber().equals(p_phone) || phone.getPhoneNumber().startsWith(p_phone))) {
							if (result.stream().filter(pdto -> pdto.getId().equals(phone.getPerson().getId())).findAny().orElse(null) == null) {
								result.add(new PersonDTO().fromPerson(phone.getPerson()));
							}
						}
					}
			}
		}
		
		List<PersonDTO> resultList = new ArrayList<PersonDTO>();
		if (result != null && !result.isEmpty()) {
			resultList.addAll(result);
		} else {
			List<PersonDTO> personsDTO = new ArrayList<PersonDTO>();
			for (Person person : persons) {
				personsDTO.add(new PersonDTO().fromPerson(person));
			}
			resultList.addAll(personsDTO);
		}
		
		for (PersonDTO personDTO : resultList) {
			for (Phone phone : phones) {
				if (phone.getPerson().getId().equals(personDTO.getId())) {
					personDTO.getPhone().add(new PhoneDTO().fromPhone(phone));
				}
			}
		}
		
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setPersonDTO(resultList);
		resultDTO.setTotal_count(totalCount);
		if (offset != null) resultDTO.setRange(offset);
		if (start != null) resultDTO.setStart_index(start);
		
		return resultDTO;
	}
	
	public List<Person> findFriends(String personLogin) throws PersonException {
		List<Person> persons = initWithDataService.getPersons();
		List<Person> result = new ArrayList<Person>();
		
		Person person = null;
		for (Person p_person : persons) {
			if (p_person.getLogin().equals(personLogin)) {
				person = p_person;
				break;
			}
		}
		
		if (person == null) {
			throw new PersonException("Person not found"); 
		}
		
		if (person.getFriend().isEmpty()) {
			throw new PersonException("Person has not friends");
		}
		
		for (Person p_friend : person.getFriend()) {
			result.add(p_friend);
		}
		
		return result;
	}
	
	public void addPerson(Person p_person) {
		List<Person> persons = initWithDataService.getPersons();
		persons.add(p_person);
	}
	
	public void updatePerson(Person p_person) throws PersonException {
		List<Person> persons = initWithDataService.getPersons();
		boolean isFound = false;
		for (Iterator<Person> iter = persons.listIterator(); iter.hasNext(); ) {
			Person p = iter.next();
		    if (p.getId() == p_person.getId()) {
		        iter.remove();
		        isFound = true;
		        break;
		    }
		}
		if (isFound) {
			persons.add(p_person);			
		} else {
			throw new PersonException("Person not found"); 
		}
	}
	
	public void removePerson(UUID personId) throws PersonException {
		List<Person> persons = initWithDataService.getPersons();
		boolean isFound = false;
		for (Iterator<Person> iter = persons.listIterator(); iter.hasNext(); ) {
			Person p = iter.next();
		    if (p.getId() == personId) {
		        iter.remove();
		        isFound = true;
		        break;
		    }
		}
		if (!isFound) {
			throw new PersonException("Person not found"); 
		}
	}
}
