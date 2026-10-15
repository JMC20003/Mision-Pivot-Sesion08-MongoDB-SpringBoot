package com.demo.example.mscustomerdummy.service.impl;

import com.demo.example.mscustomerdummy.dto.CustomerRequest;
import com.demo.example.mscustomerdummy.model.Customer;
import com.demo.example.mscustomerdummy.repository.CustomerRepository;
import com.demo.example.mscustomerdummy.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  @Override
  public Flux<Customer> listCustomers() {
    return customerRepository.findAll();
  }

  @Override
  public Mono<Customer> getCustomer(String id) {
    return customerRepository.findById(id)
        .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer no encontrado")));
  }

  @Override
  public Mono<Customer> createCustomer(CustomerRequest customerRequest) {
    Customer customer = Customer.builder()
        .nombre(customerRequest.getNombre())
        .apellido(customerRequest.getApellido())
        .correo(customerRequest.getCorreo())
        .dni(customerRequest.getDni())
        .build();
    return customerRepository.insert(customer);
  }

  @Override
  public Mono<Customer> updateCustomer(String id, CustomerRequest customerRequest) {
    return customerRepository.findById(id)
        .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer no encontrado")))
        .flatMap(existingCustomer -> {
          // Los campos omitidos o nulos conservan su valor actual.
          if (customerRequest.getNombre() != null) {
            existingCustomer.setNombre(customerRequest.getNombre());
          }
          if (customerRequest.getApellido() != null) {
            existingCustomer.setApellido(customerRequest.getApellido());
          }
          if (customerRequest.getCorreo() != null) {
            existingCustomer.setCorreo(customerRequest.getCorreo());
          }
          if (customerRequest.getDni() != null) {
            existingCustomer.setDni(customerRequest.getDni());
          }
          return customerRepository.save(existingCustomer);
        });
  }

  @Override
  public Mono<Void> deleteCustomer(String id) {
    return customerRepository.deleteById(id);
  }

}
