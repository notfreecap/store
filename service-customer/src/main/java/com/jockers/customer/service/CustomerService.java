package com.jockers.customer.service;

import com.jockers.customer.entity.Customer;
import com.jockers.customer.entity.Region;

import java.util.List;

public interface CustomerService {

    List<Customer> findCustomerAll();

    List<Customer> finCustomerByRegion(Region region);

    Customer createCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    Customer deleteCustomer(Customer customer);
    Customer getCustomer(Long id);
}
