package com.example.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.store.dto.UserDto;
import com.example.store.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MainController {
    private UserService userService;

    @GetMapping("/registration")
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new UserDto());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user") UserDto userRegistrationDto){
        userService.saveUser(userRegistrationDto);
        return "redirect:/registration?success";
    }

    
}
