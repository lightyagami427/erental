package edu.cvr.erental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.cvr.erental.model.ErentalUsers;
import edu.cvr.erental.model.ErentalUsersRegistry;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@Slf4j
public class ErentalController {
    @Autowired
    private ErentalUsersRegistry usersRegistry;
    @GetMapping("/")
    public String getMethodName() {
        return "index";
    }
    @PostMapping("/registeruser")
    public String postMethodName(@ModelAttribute ErentalUsers user) {
        log.info("username={},email={}",user.getUserName(),user.getEmail());
        usersRegistry.save(user);
        return "signup";
    }
    @GetMapping("/home")
    public String getHome()
    {
        return "home";
    }
    
}
