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

    @Autowired
    private PersonService personService;

    /**
     * Create new Person
     *
     * @param person in JSON, fill in the attributes of the Person class.
     * @return Id of сreated Person
     */
    @PostMapping("/create")
    public Long createPerson(@Valid @RequestBody Person person) {
        return personService.createPerson(person);
    }


    /**
     * Update the Person object with a unique Id.
     *
     * @param person in JSON, fill in the attributes of the Person class.
     * @return created Person
     */
    @PostMapping("/update")
    public Person updatePerson(@Valid @RequestBody Person person) {
        return personService.updatePerson(person);
    }


    /**
     * Delete Person by Id
     *
     * @param idPerson unique Person Id
     */
    @GetMapping(value = "/delete/{idPerson}")
    public void delPerson(@PathVariable String idPerson) {
        personService.deletePersonById(Long.parseLong(idPerson));
    }

    /**
     * Get list of all Persons
     *
     * @return List of all Persons
     */
    @GetMapping(value = "/getAll")
    public Iterable<Person> getAllPersons() {
        return personService.getAllPersons();
    }

}
