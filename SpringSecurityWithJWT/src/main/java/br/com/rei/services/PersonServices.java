package br.com.rei.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import br.com.rei.controllers.PersonController;
import br.com.rei.data.dto.v1.PersonDto;
import br.com.rei.exceptions.RequiredObjectIsNullException;
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
		
		List<PersonDto> dtoList = personMapper.personsToPersonsDto(repository.findAll());
		
		dtoList
			.stream()
			.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		
		return dtoList;
	}
	
	public PersonDto findById(Long id) {
		
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		PersonDto personDto = personMapper.personToPersonDto(entity);
		personDto.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		
		return personDto;
	}
	
	public PersonDto create(PersonDto person) {
		
		if (person == null) throw new RequiredObjectIsNullException();
		
		Person entity = personMapper.personDtoToPerson(person);
		
		PersonDto dto = personMapper.personToPersonDto(repository.save(entity));
		dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());
		
		return dto;	
	}
	
	public PersonDto update(PersonDto person) {
		
		if (person == null) throw new RequiredObjectIsNullException();
		
		Person entity = repository.findById(person.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		PersonDto dto = personMapper.personToPersonDto(repository.save(entity));
		dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());
		return dto;	
	}
	
	public void delete(Long id) {
		
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		repository.delete(entity);
	}
	
}
