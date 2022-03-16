package com.manoj.springpetclinic.sdj;

import com.manoj.springpetclinic.model.Owner;
import com.manoj.springpetclinic.repositories.OwnerRepository;
import com.manoj.springpetclinic.repositories.PetRepository;
import com.manoj.springpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJServiceTest {

    public static final String LAST_NAME = "Manu";
    public static final long OWNER_ID = 1L;
    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJService ownerSDJService;

    Owner returnedOwner;

    @BeforeEach
    void setUp() {
        returnedOwner = new Owner();
        returnedOwner.setId(OWNER_ID);
        returnedOwner.setLastName(LAST_NAME);
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(returnedOwner);

        Owner manu = ownerSDJService.findByLastName(LAST_NAME);

        assertNotNull(manu);
        assertEquals("Manu", manu.getLastName());

        verify(ownerRepository).findByLastName(any());
        verify(ownerRepository, times(1)).findByLastName(any());
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }
}