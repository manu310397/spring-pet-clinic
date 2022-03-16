package com.manoj.springpetclinic.map;

import com.manoj.springpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;

    final Long id = 1L;

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeMap(), new PetServiceMap());
        Owner owner1 = new Owner();
        owner1.setId(id);
        ownerServiceMap.save(owner1);
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerServiceMap.findAll();

        assertEquals(1, owners.size());
    }

    @Test
    void save() {
        Long id = 2L;
        Owner owner2 = new Owner();
        owner2.setId(id);

        ownerServiceMap.save(owner2);

        Owner savedOwner = ownerServiceMap.findById(id);

        assertEquals(2L, savedOwner.getId());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(id);

        assertEquals(1L, owner.getId());
    }

}