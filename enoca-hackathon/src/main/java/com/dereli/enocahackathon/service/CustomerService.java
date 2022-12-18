package com.dereli.enocahackathon.service;

import com.dereli.enocahackathon.dto.AddressListResponse;
import com.dereli.enocahackathon.dto.CustomerDto;
import com.dereli.enocahackathon.dto.SaveCustomerRequest;
import com.dereli.enocahackathon.exception.CustomerNotFoundException;
import com.dereli.enocahackathon.model.Address;
import com.dereli.enocahackathon.model.Customer;
import com.dereli.enocahackathon.model.UserRole;
import com.dereli.enocahackathon.repository.CustomerRepository;
import com.sun.jdi.IntegerValue;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public CustomerDto createCustomer(SaveCustomerRequest saveCustomerRequest){
        Customer customer = new Customer();
                customer.setEmail(saveCustomerRequest.getEmail());
                customer.setPassword(passwordEncoder.encode(saveCustomerRequest.getPassword()));
                customer.setRole(UserRole.ADMIN);

        return convertResponse(customerRepository.save(customer));
    }


    public Customer findByCustomerEmail(String email){
        return customerRepository.findCustomerByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException("Customer couldn't found by email: " + email));


    }

    public CustomerDto getCustomerDto(String email) {
        var customer =findByCustomerEmail(email);
        return CustomerDto.builder()
                .email(customer.getEmail())
                .role(customer.getRole())
                .build();
    }

    private static CustomerDto convertResponse(Customer model){
        return new CustomerDto(model.getEmail(),
                model.getPassword(),
                model.getAddresses());
    }
}
