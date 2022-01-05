package com.example.demo.startup;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import com.example.demo.appuser.AppUserRoleEnum;
import com.example.demo.appuser.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserCreationRunner {

    @Autowired
    private AppUserService appUserService;


    @PostConstruct
    public void createUserOnStartup(){

        AppUser user1 = new AppUser();
        user1.setEmail("user");
        user1.setPassword("user");
        user1.setFirstName("panda");
        user1.setLastName("panda");
        user1.setAppUserRoleEnum( AppUserRoleEnum.USER);

        appUserService.signUpUser(user1);



        AppUser user2 = new AppUser();
        user2.setEmail("admin");
        user2.setPassword("admin");
        user2.setFirstName("bear");
        user2.setLastName("bear");
        user2.setAppUserRoleEnum( AppUserRoleEnum.ADMIN);

        appUserService.signUpUser(user2);

    }


}
