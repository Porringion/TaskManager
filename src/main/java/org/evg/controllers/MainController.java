package org.evg.controllers;

import org.evg.repos.TaskRepo;
import org.evg.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class MainController {

    @GetMapping("/")
    public String greeting(Map<String, Object> model){
        return "redirect:/tasks";
    }

}
