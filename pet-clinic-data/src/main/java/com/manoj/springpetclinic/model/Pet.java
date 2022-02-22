package com.manoj.springpetclinic.model;

import java.time.LocalDate;

public class Pet extends BaseEntity {
    private String name;

    private PetType petType;

    private Owner owner;

    private LocalDate birthDate;

}
