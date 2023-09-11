package br.com.rei.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rei.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {}
