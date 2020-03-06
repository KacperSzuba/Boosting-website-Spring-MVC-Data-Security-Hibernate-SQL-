package com.BoostingWebsite.account.password.forgot;

import org.springframework.data.repository.CrudRepository;

public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken,Long> {
}
