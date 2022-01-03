package com.example.demo.registration.token;

import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepositroy<ConfirmationToken,Long> {
    Optional<ConfirmationToken> findByToken(String token);
}
