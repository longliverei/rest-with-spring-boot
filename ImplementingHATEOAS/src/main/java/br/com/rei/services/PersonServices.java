package br.com.rei.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rei.data.dto.v1.PersonDto;
import br.com.rei.exceptions.ResourceNotFoundException;
import br.com.rei.mapper.PersonMapper;
import br.com.rei.models.Person;
import br.com.rei.repositories.PersonRepository;

@Service
public class PersonServices {

	@Autowired
	private PersonRepository repository;
	
	@Autowired
	private PersonMapper personMapper;
	
	public List<PersonDto> findAll() {
		return personMapper.personsToPersonsDto(repository.findAll());
	}
	
	public PersonDto findById(Long id) {
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		return personMapper.personToPersonDto(entity);
	}
	
	public PersonDto create(PersonDto person) {		
		Person entity = personMapper.personDtoToPerson(person);
		
		PersonDto vo = personMapper.personToPersonDto(repository.save(entity));
		
		return vo;	
	}
	
	public PersonDto update(PersonDto person) {
		
		Person entity = repository.findById(person.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		PersonDto vo = personMapper.personToPersonDto(repository.save(entity));
		
		return vo;	
	}
	
	public void delete(Long id) {
		
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		repository.delete(entity);
	}
	
}
