package br.com.rei.unittests.mockito.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.rei.data.dto.v1.PersonDto;
import br.com.rei.mapper.PersonMapper;
import br.com.rei.models.Person;
import br.com.rei.repositories.PersonRepository;
import br.com.rei.services.PersonServices;
import br.com.rei.unittests.mapper.mocks.MockPerson;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith({ MockitoExtension.class })
class PersonServicesTest {
	
	MockPerson input;
	
	@InjectMocks
	private PersonServices service;
	
	@Mock
	private PersonRepository repository;
	
	@Mock
	private PersonMapper personMapper;
	
	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockPerson();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	void testFindById() {
		
		Person person = input.mockEntity();
		PersonDto personDto = input.mockVO();
		
		when(repository.findById(person.getId()))
			.thenReturn(Optional.of(person));
		when(personMapper.personToPersonDto(person))
			.thenReturn(personDto);
		
		PersonDto result = service.findById(person.getId());
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/person/v1/0>;rel=\"self\"]"));
		assertThat(result)
			.isSameAs(personDto);
	}

	@Test
	void testCreate() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

}
