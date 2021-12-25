package com.app.AcademicProgram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The type User controller.
 */
@Controller
@RequestMapping("/users")
public class UserController {
    /**
     * Users homepage string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/list")
    public String usersHomepage(Model model){
        model.addAttribute("users","users will be here");
        return "users";
    }

}
