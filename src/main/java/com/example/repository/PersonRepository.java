package com.example.repository;

import com.example.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person,Long > {

    List<Person> findPersonByFirstName(String firstName);

    Person findPersonByAge(Integer age);
}
