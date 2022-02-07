package com.example.controller;

import com.example.entity.Person;
import com.example.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping(path = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
class PersonController {

    private PersonService personService;

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/create")
    public Long createPerson(@Valid @RequestBody Person person) {
        return personService.createPerson(person);
    }

    @PostMapping("/update")
    public Person updatePerson(@Valid @RequestBody Person person) {
        return personService.updatePerson(person);
    }

    @GetMapping(value = "/delete/{id}")
    public void delPerson(@PathVariable String id) {
        personService.deletePersonById(Long.parseLong(id));
    }


    @GetMapping(value = "/getAll")
    public Iterable<Person> getAllPersons() {
        return personService.getAllPersons();
    }

}
