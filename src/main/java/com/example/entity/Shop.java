package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Shop", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }

}

