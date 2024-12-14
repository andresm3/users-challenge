package com.challenge.bci.users.repository;

import com.challenge.bci.users.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Customer, Integer> {

  Customer findByEmail(String email);
}
