package br.com.rei.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.rei.models.Person;

@Service
public class PersonServices {
	
	private final AtomicLong counter = new AtomicLong();
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	public List<Person> findAll() {
		
		logger.info("finding all people!");
		
		List<Person> persons = new ArrayList<>();
		
		for (int i = 0; i < 8; i++) {
			Person person = mockPerson(i);
			persons.add(person);
		}
		
		return persons;
	}
	
	public Person findById(String id) {
		
		logger.info("finding one person!");
		
		Person person = new Person();
		
		person.setId(counter.incrementAndGet());
		person.setFirstName("Reinaldo");
		person.setLastName("Yabiku");
		person.setAddress("São Paulo - SP -Brasil");
		person.setGender("Male");
		
		return person;
	}
	
	public Person mockPerson(int i) {
		Person person = new Person();
		
		person.setId(counter.incrementAndGet());
		person.setFirstName("Some name " + i);
		person.setLastName("Some last name " + i);
		person.setAddress("Some address in são mateus " + i);
		person.setGender("Male");
		
		return person;
	}
}
