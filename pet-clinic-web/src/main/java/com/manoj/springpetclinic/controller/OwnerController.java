package com.manoj.springpetclinic.controller;

import com.manoj.springpetclinic.OwnerService;
import com.manoj.springpetclinic.model.Owner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final static String VIEW_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping("/find")
    public String findOwners(Model model) {
        model.addAttribute("owner", new Owner());

        return "owners/findOwners";
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));

        return mav;
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult bindingResult, Model model) {
        if (owner.getLastName() == null)
            owner.setLastName("");

        List<Owner> results = ownerService.findAllByLastNameLike("%"+owner.getLastName()+"%");

        if (results.isEmpty()) {
            bindingResult.rejectValue("lastName", "Not found", "Not found");
            return "owners/findOwners";
        } else if(results.size() == 1) {
            owner = results.iterator().next();
            return "redirect:/owners/"+owner.getId();
        } else {
            model.addAttribute("selections", results);
            return "owners/ownersList";
        }
    }

    @GetMapping("/new")
    public String initCreateOwnerForm(Model model) {
        model.addAttribute("owner", new Owner());

        return VIEW_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreateOwnerForm (@Validated Owner owner, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return VIEW_OWNER_CREATE_OR_UPDATE_FORM;
        else {
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/"+savedOwner.getId();
        }
    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model) {
        model.addAttribute("owner", ownerService.findById(ownerId));

        return VIEW_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@Validated Owner owner, BindingResult bindingResult, @PathVariable Long ownerId) {
        if(bindingResult.hasErrors())
            return VIEW_OWNER_CREATE_OR_UPDATE_FORM;
        else {
            owner.setId(ownerId);
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/"+savedOwner.getId();
        }
    }

}
