package br.com.rei.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rei.data.vo.v1.PersonVO;
import br.com.rei.data.vo.v2.PersonVOV2;
import br.com.rei.exceptions.ResourceNotFoundException;
import br.com.rei.mapper.Mapper;
import br.com.rei.mapper.custom.PersonMapper;
import br.com.rei.models.Person;
import br.com.rei.repositories.PersonRepository;

@Service
public class PersonServices {
	
	@Autowired
	PersonRepository repository;
	
	@Autowired
	private PersonMapper customMapper;
	
	public List<PersonVO> findAll() {
		return Mapper.parseListObjects(repository.findAll(), PersonVO.class);
	}
	
	public PersonVO findById(Long id) {
		var entity= repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		return Mapper.parseObject(entity, PersonVO.class);
	}
	
	public PersonVO create(PersonVO person) {		
		var entity = Mapper.parseObject(person, Person.class);
		
		var vo = Mapper.parseObject(repository.save(entity), PersonVO.class);
		
		return vo;	
	}
	
	public PersonVOV2 createV2(PersonVOV2 person) {		
		var entity = customMapper.convertVoToEntity(person);
		
		var vo = customMapper.convertEntityToVo(repository.save(entity));
		
		return vo;	
	}
	
	public PersonVO update(PersonVO person) {
		
		var entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		entity.setId(person.getId());
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		var vo = Mapper.parseObject(repository.save(entity), PersonVO.class);
		
		return vo;	
	}
	
	public void delete(Long id) {
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		repository.delete(entity);
	}
	
}
