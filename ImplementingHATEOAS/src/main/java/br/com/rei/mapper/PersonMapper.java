package br.com.rei.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.rei.data.dto.v1.PersonDto;
import br.com.rei.models.Person;

@Mapper(componentModel = "spring")
public interface PersonMapper {
	
	@Mapping(target = "key", source = "id")
	PersonDto personToPersonDto(Person person);
	
	@Mapping(target = "id", source = "key")
	Person personDtoToPerson(PersonDto personDto);
	
	@Mapping(target = "key", source = "id")
	List<PersonDto> personsToPersonsDto(List<Person> persons);
	
	@Mapping(target = "id", source = "key")
	List<Person> personsDtoToPersons(List<PersonDto> personsDto);
}
