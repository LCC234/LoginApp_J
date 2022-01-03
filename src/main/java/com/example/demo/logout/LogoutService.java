package com.example.demo.logout;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserService;
import org.springframework.stereotype.Service;

@Service
public class LogoutService {

    private AppUserService appUserService;

    public boolean logout(LogoutRequest logoutRequest){



        return appUserService.logoutUserByUserName(new AppUser());

    }


}
