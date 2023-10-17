package com.example.demo.controller;

import com.example.demo.dto.CustomerReqDTO;
import com.example.demo.dto.CustomerResDTO;
import com.example.demo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerRestController {
    private final CustomerService customerService;

    @PostMapping
    public CustomerResDTO saveUser(@RequestBody CustomerReqDTO customerReqDTO) {
        return customerService.saveCustomer(customerReqDTO);
    }

    @GetMapping("/{id}")
    public CustomerResDTO getCustomerByID(@PathVariable Long id){
        return customerService.getCustomersById(id);
    }

    @GetMapping
    public List<CustomerResDTO> getCustomers(){
        return customerService.getCustomers();
    }

    @PatchMapping("/{email}")
    public CustomerResDTO updateCustomers(@PathVariable String email, @RequestBody CustomerReqDTO customerReqDTO){
        return customerService.updateCustomers(email, customerReqDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return ResponseEntity.ok(id+"User가 삭제처리 되었습니다.");
    }
}

