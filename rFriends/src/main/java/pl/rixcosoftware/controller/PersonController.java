package pl.riscosoftware.controller;

import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pl.riscosoftware.domain.Person;
import pl.riscosoftware.dto.ResultDTO;
import pl.riscosoftware.err.handler.ErrorResponse;
import pl.riscosoftware.err.handler.PersonException;
import pl.riscosoftware.service.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	private PersonService personService;

	@RequestMapping("/")
	String home() {
		return "Hello rFriends";
	}


	@RequestMapping(value = "/find/all", method = RequestMethod.GET)
	public ResponseEntity<String> getPersonList(@RequestParam(required = false) Integer start,
												@RequestParam(required = false) Integer offset,
												@RequestParam(required = false) Boolean sorted, 
												@RequestParam(required = false) String login, 
												@RequestParam(required = false) String phone) throws PersonException {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String outputJson = null;
		
		ResultDTO resultDTO = personService.getPersonList(start, offset, sorted, login, phone);
		outputJson = gson.toJson(resultDTO);
		
		return new ResponseEntity<String>(outputJson,  new HttpHeaders(), HttpStatus.OK);
	}
	
	

	@GetMapping("/find/friends/{personLogin}")
	public ResponseEntity<List<Person>> findFriends(@PathVariable("personLogin") String personLogin) throws PersonException {
		
		List<Person> result = personService.findFriends(personLogin);
		
		return new ResponseEntity<List<Person>>(result,  new HttpHeaders(), HttpStatus.OK);
	}

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public ResponseEntity<Void> addPerson(@RequestBody Person p_person) throws URISyntaxException {
		personService.addPerson(p_person);
		return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/update/{personId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateExist(@RequestBody Person p_person, @PathVariable UUID personId)	throws PersonException {
		try {
			p_person.setId(personId);
			personService.updatePerson(p_person);
			return ResponseEntity.noContent().build();
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@RequestMapping(path = "/remove/{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@PathVariable UUID personId) throws PersonException {
		try {
			personService.removePerson(personId);
			return ResponseEntity.noContent().build();
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@ExceptionHandler(PersonException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}

}
