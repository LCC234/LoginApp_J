package com.example.demo.appuser;

import com.example.demo.security.PasswordEncoder;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";
    private final AppUserRepository appUserRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,email)));
    }

    public String signUpUser(AppUser appUser){

        appUser.setActive(true);
        appUser.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(appUser.getPassword()));
        AppUser user = appUserRepository.save(appUser);

        return  user.toString();
    }


    public boolean logoutUserByUserName(AppUser appUser){

        appUser.setActive(false);
        appUserRepository.save(appUser);

        return true;
    }
}
