package com.example.demo.security.config.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/login")
@AllArgsConstructor

public class LoginController {

    private RegistrationService registrationService;
    @PostMapping
    public String login(@RequestBody RegistrationRequest request){


        return registrationService.register(request);
    }


    @GetMapping
    public String loginUsingGet(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password){

        return username + password;

    }
}
