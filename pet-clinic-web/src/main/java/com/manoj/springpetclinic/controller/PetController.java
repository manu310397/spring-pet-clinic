package com.manoj.springpetclinic.controller;

import com.manoj.springpetclinic.OwnerService;
import com.manoj.springpetclinic.PetService;
import com.manoj.springpetclinic.PetTypeService;
import com.manoj.springpetclinic.model.Owner;
import com.manoj.springpetclinic.model.Pet;
import com.manoj.springpetclinic.model.PetType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    private final static String VIEW_PET_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

    private final OwnerService ownerService;
    private final PetTypeService petTypeService;
    private final PetService petService;

    public PetController(OwnerService ownerService, PetTypeService petTypeService, PetService petService) {
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
        this.petService = petService;
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

    @GetMapping("/pets/new")
    public String initCreationForm(Owner owner, Model model) {
        Pet pet = Pet.builder().build();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);

        return VIEW_PET_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("pets/new")
    public String processCreationForm(Owner owner, @Validated Pet pet, BindingResult bindingResult, Model model) {
        owner.getPets().add(pet);

        if(bindingResult.hasErrors()) {
            model.addAttribute("pet", pet);
            return VIEW_PET_CREATE_OR_UPDATE_FORM;
        } else {
            petService.save(pet);
            return "redirect:/owners/"+owner.getId();
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable Long petId, Model model) {
        model.addAttribute("pet", petService.findById(petId));

        return VIEW_PET_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(@Validated Pet pet, Owner owner, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            pet.setOwner(owner);
            model.addAttribute("pet", pet);

            return VIEW_PET_CREATE_OR_UPDATE_FORM;
        } else {
            owner.getPets().add(pet);
            petService.save(pet);

            return "redirect:/owners/"+owner.getId();
        }
    }
}
