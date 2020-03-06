package com.BoostingWebsite.account.password.forgot;

import com.BoostingWebsite.account.user.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken,Long> {

    Optional<PasswordResetToken> findByUser(User user);

    @Transactional
    @Modifying
    @Query("UPDATE PasswordResetToken prt SET prt.token =:token WHERE prt.user=:user")
    void changeToken(@Param("token") String token, @Param("user") User user);

    PasswordResetToken findByToken(String token);
}
