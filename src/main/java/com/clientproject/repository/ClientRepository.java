package com.clientproject.repository;

import com.clientproject.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByMobileNumber(String mobileNumber);
    Optional<Client> findByFirstName(String firstName);
    Optional<Client> findByIdNumber(String id);

}
