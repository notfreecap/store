package com.jockers.customer.repository;

import com.jockers.customer.entity.Customer;
import com.jockers.customer.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByNumberId(String numberId);

    List<Customer> findByLastName(String lastName);

    List<Customer> findByRegion(Region region);
}
