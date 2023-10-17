package com.example.demo.controller;


import com.example.demo.dto.Customer;
import com.example.demo.entity.Customers;
import com.example.demo.exception.BusinessException;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerBasicRestController {
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping
    public Customers create(@RequestBody Customers customer) {
        return customerRepository.save(customer);
    }

    @GetMapping
    public List<Customers> getCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Customers getCustomers(@PathVariable Long id) {
        Optional<Customers> optionalCustomer = customerRepository.findById(id);

        //orElseThrow(Supplier) Supplier의 추상메서드 T get()
        Customers customer = optionalCustomer.orElseThrow(() -> new BusinessException("Customer Not Found", HttpStatus.NOT_FOUND));
        return customer;
    }

    @GetMapping("/email/{email}")
    public Customers getCustomersByEmail(@PathVariable String email) {
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("요청하신 email에 해당하는 고객이 없습니다.", HttpStatus.NOT_FOUND));
    }

    @GetMapping("/age/{age}")
    public List<Customers> getCustomersByAge(@PathVariable Long age) {
        return customerRepository.findByAge(age);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomers(@PathVariable Long id) {
        Customers customer = customerRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Customer Not Found", HttpStatus.NOT_FOUND));
        customerRepository.delete(customer);
        return ResponseEntity.ok(id+" Customer이 삭제되었습니다!");
    }

}
