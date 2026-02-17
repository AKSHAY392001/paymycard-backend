package com.paymycard.backend.repository;

import com.paymycard.backend.model.User;
import com.paymycard.backend.model.UserBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserBankRepository extends JpaRepository<UserBank, UUID> {
    List<UserBank> findAllByUser(User user);
}
