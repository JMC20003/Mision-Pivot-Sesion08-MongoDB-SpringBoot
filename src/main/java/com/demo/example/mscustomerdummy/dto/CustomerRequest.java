package com.demo.example.mscustomerdummy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {

  private String nombre;
  private String apellido;
  private String correo;
  private String dni;

}
