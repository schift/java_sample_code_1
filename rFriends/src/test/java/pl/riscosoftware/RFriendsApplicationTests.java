package pl.riscosoftware;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import pl.riscosoftware.service.InitWithDataService;
import pl.riscosoftware.service.PersonService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RFriendsApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private InitWithDataService initWithDataService; 
    
    @Autowired
    private PersonService personService;
    
    @Before
    public void setUp() {
    	try {
			initWithDataService.doFill();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    
    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/persons/", String.class)).contains("Hello");
    }
	
    @Test
    public void getShouldReturnPerson() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/persons/find/all?login=jkowalski", String.class)).contains("Jan Kowalski");
    }
    
    @Test
    public void getFriendsShouldReturnFriendsList() throws Exception {
    	assertFalse(personService.findFriends("jkowalski").isEmpty());
    }    
}
