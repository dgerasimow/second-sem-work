package ru.itis.gerasimow.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InfoController {

    @GetMapping("/")
    public String getInfoPage() {
        return "redirect:/login";
    }
}
