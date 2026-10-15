package com.demo.example.mscustomerdummy.controller;

import com.demo.example.mscustomerdummy.model.Customer;
import com.demo.example.mscustomerdummy.dto.CustomerRequest;
import com.demo.example.mscustomerdummy.service.CustomerService;
import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
@Tag(name = "Customer", description = "Operaciones sobre Customers")
public class CustomerController {

  private final CustomerService customerService;

  @Operation(summary = "Lista todos los Customer", description = "Muestra todos los Customer del sistema")
  @GetMapping("/customers")
  public Flux<Customer> viewCustomers(@RequestHeader Map<String, String> headers) {
    return customerService.listCustomers();
  }

  @Operation(summary = "Busca un Customer por ID", description = "Busca un Customer del sistema por ID")
  @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
  @GetMapping("/customers/{id}")
  public Mono<Customer> viewCustomer(@RequestHeader Map<String, String> headers, @PathVariable String id) {
    return customerService.getCustomer(id)
        .doOnError(throwable -> System.out.println(throwable));
  }

  @Operation(summary = "Crea un Customer", description = "Registra un Customer en el sistema")
  @ApiResponse(responseCode = "201", description = "Customer creado")
  @PostMapping("/customers")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Customer> createCustomer(@RequestHeader Map<String, String> headers,
      @RequestBody CustomerRequest customerRequest) {
    return customerService.createCustomer(customerRequest);
  }

  @Operation(summary = "Actualiza un Customer por ID", description = "Actualiza un Customer existente")
  @ApiResponse(responseCode = "404", description = "Customer no encontrado")
  @PutMapping("/customers/{id}")
  public Mono<Customer> updateCustomer(@RequestHeader Map<String, String> headers, @PathVariable String id,
      @RequestBody CustomerRequest customerRequest) {
    return customerService.updateCustomer(id, customerRequest);
  }

  @Operation(summary = "Elimina un Customer por ID", description = "Elimina un Customer del sistema")
  @DeleteMapping("/customers/{id}")
  public Mono<Void> deleteCustomer(@RequestHeader Map<String, String> headers, @PathVariable String id) {
    return customerService.deleteCustomer(id);
  }

}
