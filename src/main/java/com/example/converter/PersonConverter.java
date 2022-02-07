package com.example.converter;

import com.example.entity.Person;

public final class PersonConverter {

    public static Person personConverter(Person updatePerson, Person savedPerson) {
        savedPerson.setFirstName(updatePerson.getFirstName());
        savedPerson.setLastName(updatePerson.getLastName());
        savedPerson.setAge(updatePerson.getAge());
        savedPerson.setPhoneNumber(updatePerson.getPhoneNumber());
        savedPerson.setEmail(updatePerson.getEmail());
        return savedPerson;
    }
}
