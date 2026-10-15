package com.demo.example.mscustomerdummy.service;

import com.demo.example.mscustomerdummy.dto.CustomerRequest;
import com.demo.example.mscustomerdummy.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
  public Flux<Customer> listCustomers();
  public Mono<Customer> getCustomer(String id);
  public Mono<Customer> createCustomer(CustomerRequest customerRequest);
  public Mono<Customer> updateCustomer(String id, CustomerRequest customerRequest);
  public Mono<Void> deleteCustomer(String id);

}
