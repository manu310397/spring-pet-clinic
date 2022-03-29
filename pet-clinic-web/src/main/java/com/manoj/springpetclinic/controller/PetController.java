package com.manoj.springpetclinic.controller;

import com.manoj.springpetclinic.OwnerService;
import com.manoj.springpetclinic.PetTypeService;
import com.manoj.springpetclinic.model.Owner;
import com.manoj.springpetclinic.model.PetType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    private final static String VIEW_PET_CREATE_OR_UPDATE_FORM = "pets/createOrUpdateOwnerForm";

    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    public PetController(OwnerService ownerService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
}
