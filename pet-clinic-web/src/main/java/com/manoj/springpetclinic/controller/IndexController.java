package com.manoj.springpetclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"", "/", "index", "index.html"})
    public String index(Model model) {
        model.addAttribute("welcome", "Welcome!");
        return "index";
    }

    @RequestMapping("/oups")
    public String errorHandler() {
        return "notimplemented";
    }
}
