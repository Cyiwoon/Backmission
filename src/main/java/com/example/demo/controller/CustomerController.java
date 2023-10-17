package com.example.demo.controller;

import com.example.demo.dto.CustomerReqDTO;
import com.example.demo.dto.CustomerReqForm;
import com.example.demo.dto.CustomerResDTO;
import com.example.demo.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/customerspage")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/index")
    public ModelAndView index(){
        List<CustomerResDTO> customerResDTOList = customerService.getCustomers();
        return new ModelAndView("index","customer",customerResDTOList);
    }

    @GetMapping("/signup")
    public String showSignupForm(CustomerReqDTO customer){
        return "add-customer";
    }

    @PostMapping("/addcustomer")
    public String addCustomer(@Valid CustomerReqDTO customer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-customer";
        }

        customerService.saveCustomer(customer);
//        model.addAttribute("cutomers", customerService.getCustomers());
//        return "index";
        return "redirect:/customerspage/index";
    }

    @GetMapping("/edit/{id}")
    public String  showUpdateForm(@PathVariable Long id, Model model){
        CustomerResDTO customerResDTO = customerService.getCustomersById(id);
        model.addAttribute("customer",customerResDTO);
        return "update-customer";
    }

    @PostMapping("/update/{id}")
    public String updateCustomer(@PathVariable("id") Long id, @Valid CustomerReqForm customer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println(">>>>>>hasErrors customer " + customer);
            model.addAttribute("customer",customer);
            //customer.setId(id);
            return "update-customer";
            //return "redirect:/customerspage/edit/{id}(id=${customer.id})";
        }
        customerService.updateCustomerForm(customer);
        //customerService.update(customer);
        return "redirect:/customerspage/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") long id) {
        customerService.deleteCustomer(id);
        return "redirect:/customerspage/index";
    }

}
