
package com.example.demo.service;

import com.example.demo.dto.CustomerReqDTO;
import com.example.demo.dto.CustomerReqForm;
import com.example.demo.dto.CustomerResDTO;
import com.example.demo.entity.Customers;
import com.example.demo.exception.BusinessException;
import com.example.demo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CustomerResDTO saveCustomer(CustomerReqDTO customerReqDto){
        Customers customers = modelMapper.map(customerReqDto, Customers.class);
        Customers savedCustomer = customerRepository.save(customers);
        return modelMapper.map(savedCustomer, CustomerResDTO.class);
    }

    public CustomerResDTO getCustomersById(Long id){
        Customers customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new BusinessException(id + "Customer Not Found", HttpStatus.NOT_FOUND));
        CustomerResDTO customerResDTO = modelMapper.map(customerEntity, CustomerResDTO.class);
        return customerResDTO;
    }

    public List<CustomerResDTO> getCustomers() {
        // List<Customers> => List<CustomerResDTO>
        List<Customers> customerList = customerRepository.findAll(); // List<Customer>

        List<CustomerResDTO> customerResDTOList = customerList.stream() // Stream<Customer>
                //map(Function) Function의 추상메서드는 R apply(T t)
                .map(customer -> modelMapper.map(customer, CustomerResDTO.class)) // Stream<CustomerResDTO>
                // customer-> = T /modelMapper.map(customer,CustomerResDTO.class) = t
                .collect(toList());//.collect(Collectors.toList()) //List<CustomerResDTO>
        return customerResDTOList;
    }

    public CustomerResDTO updateCustomers(String email, CustomerReqDTO customerReqDTO) {
        Customers existCustomer = customerRepository.findByEmail(email)
                .orElseThrow(() ->
                        new BusinessException(email + " Customer Not Found", HttpStatus.NOT_FOUND));
        //Dirty Checking 변경감지를 하므로 setter method만 호출해도 update query가 실행이 된다.
        existCustomer.setName(customerReqDTO.getName());
        return modelMapper.map(existCustomer, CustomerResDTO.class);
    }

    public void updateCustomerForm(CustomerReqForm customerReqForm){
        Customers existCustomer = customerRepository.findById(customerReqForm.getId())
                .orElseThrow(() ->
                        new BusinessException(customerReqForm.getId() + " Customer Not Found", HttpStatus.NOT_FOUND));
        existCustomer.setName(customerReqForm.getName());
    }

    public void deleteCustomer(Long id) {
        Customers customers = customerRepository.findById(id) //Optional<Customer>
                .orElseThrow(() ->
                        new BusinessException(id + " Customer Not Found", HttpStatus.NOT_FOUND));
        customerRepository.delete(customers);
    }

}
