package com.miguel.gm.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity  //Convertimos la Clase en una entidad JPA
@Table(name = "persona")//indicamos q la Clase mapea la Tabla 'persona' en la BD
public class Persona implements Serializable{
   private static final long serialVersionUID = 1;
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long idPersona; 
   
   @NotEmpty
   private String nombre;
   
   @NotEmpty
   private String apellido;
   
   @NotEmpty
   @Email
   private String email;
   
   private String telefono;
   
   @NotNull
   private Double saldo;
  
}
