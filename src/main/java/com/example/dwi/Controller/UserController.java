package com.example.dwi.Controller;


import java.security.Principal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.dwi.Dto.UserDto;
import com.example.dwi.Service.UserService;




@Controller
public class UserController {
   
    @Autowired
    UserDetailsService userDetailsService;
   
    @Autowired
    private UserService userService;
   
   
    @GetMapping("/registration")
    public String getRegistrationPage(@ModelAttribute("user") UserDto userDto) {
        return "register";
    }
   
    @PostMapping("/registration")
    public String saveUser(@ModelAttribute("user") UserDto userDto, Model model) {
        userService.save(userDto);
        model.addAttribute("message", "Registered Successfuly!");
        return "register";
    }
   
    @GetMapping("/login")
    public String login() {
        return "login";
    }


}