package com.demo.example.mscustomerdummy.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@Document(collection = "customers")
public class Customer {

  @Id
  private String id;
  private String nombre;
  private String apellido;
  private String correo;
  private String dni;

}
