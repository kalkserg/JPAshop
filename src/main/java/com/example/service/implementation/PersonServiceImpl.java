package com.example.service.implementation;

import com.example.entity.Person;
import com.example.repository.PersonRepository;
import com.example.service.PersonService;
import com.example.service.exception.ThereIsNoSuchPersonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.converter.PersonConverter.personConverter;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public Person getPersonById(Long id) {
        Optional<Person> person = personRepository.findById(id);
        return person.orElseThrow(() -> new ThereIsNoSuchPersonException());
    }

    @Override
    public Long createPerson(Person person) {
        return personRepository.save(person).getId();
    }

    @Override
    public Person updatePerson(Person person) {
        Person oldPerson = personRepository.findById(person.getId()).orElseGet(Person::new);
        return personRepository.save(personConverter(person, oldPerson));
    }

    @Override
    public void deletePersonById(Long id) {
        try {
            personRepository.deleteById(id);
        } catch (Exception ex) {
            throw new ThereIsNoSuchPersonException();
        }
    }

    @Override
    public Iterable<Person> getAllPersons() {
        return personRepository.findAll();
    }

}
