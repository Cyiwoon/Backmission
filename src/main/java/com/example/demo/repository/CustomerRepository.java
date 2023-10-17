package com.example.demo.repository;

import com.example.demo.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customers, Long> {
    Optional<Customers> findByEmail(String email);
    List<Customers> findByName(String name);
    List<Customers> findByAge(Long age);
}
