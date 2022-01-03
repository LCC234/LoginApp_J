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
        user1.setEmail("kai@mail.com");
        user1.setPassword("kai");
        user1.setFirstName("kk");
        user1.setLastName("ww");
        user1.setAppUserRoleEnum( AppUserRoleEnum.USER);

        appUserService.signUpUser(user1);



        AppUser user2 = new AppUser();
        user2.setEmail("cc@mail.com");
        user2.setPassword("cc");
        user2.setFirstName("cc");
        user2.setLastName("dd");
        user2.setAppUserRoleEnum( AppUserRoleEnum.ADMIN);

        appUserService.signUpUser(user2);

    }


}
