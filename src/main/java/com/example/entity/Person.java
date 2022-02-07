package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @NotBlank
    @Column(name = "firstName", length = 20)
    private String firstName;

    @NotBlank
    @Column(name = "lastName")
    private String lastName;

    @Min(1)
    @Max(120)
    @Column(name = "age")
    private Integer age;

    @NotBlank
    @Column(name = "phoneNumber")
    @Pattern(regexp = "^([0-9\\(\\)\\/\\+ \\-]*)$")
    private String phoneNumber;

    @NotBlank
    @Email
    @Column(name = "email")
    private String email;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age='" + age + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
//                ", carts=" + carts +
                '}';
    }

}
