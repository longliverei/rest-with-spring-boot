package br.com.rei.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rei.models.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {}
