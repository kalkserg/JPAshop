package com.example.service;

import com.example.entity.Cart;
import com.example.entity.Person;

public interface PersonService {

    Person getPersonById(Long id);

    Long createPerson(Person person);

    Person updatePerson(Person person);

    void deletePersonById(Long id);

    Iterable<Person> getAllPersons();

}
