package br.com.rei.services;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.rei.models.Person;

@Service
public class PersonServices {
	
	private final AtomicLong counter = new AtomicLong();
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
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
}
