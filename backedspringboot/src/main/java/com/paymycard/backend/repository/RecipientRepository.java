package com.paymycard.backend.repository;

import com.paymycard.backend.model.Recipient;
import com.paymycard.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RecipientRepository extends JpaRepository<Recipient, UUID> {
    List<Recipient> findAllByUser(User user);
}
