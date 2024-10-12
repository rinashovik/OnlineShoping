package com.simple_world.eShop.data;

import com.simple_world.eShop.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

//@Repository
public interface CustomerRepository extends CrudRepository <Customer, Integer>{
    Optional<Customer> findById (Integer Id);

}
