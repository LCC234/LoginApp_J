package com.example.demo.logout;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/logout")
@AllArgsConstructor
public class LogoutController {

    private LogoutService logoutService;

    @PostMapping
    public String logout(@RequestBody LogoutRequest request){


        return logoutService.logout(request) ? "OK" : "FAIL";
    }


}
