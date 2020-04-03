package com.example.customerList.controller;

import com.example.customerList.model.Customer;
import com.example.customerList.repository.CustomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class CustController {

    private CustomRepo customRepo;

    @Autowired
    public CustController(
            CustomRepo customRepo
    ) {
        this.customRepo = customRepo;
    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexPage() {
        return "home";
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String customerList(Model model) {
        List<Customer> customerList = customRepo.findAll();
        if (customerList != null) {
            model.addAttribute("customers", customerList);
        }
        return "list";
    }

    @RequestMapping(value = "/customers/add", method = RequestMethod.GET)
    public String showCustomerForm() {
        return "form";
    }

    @RequestMapping(value = "/customers/add", method = RequestMethod.POST)
    public String addToCustomerList(Customer customer) {
        customRepo.save(customer);
        return "redirect:/customers";
    }
}
