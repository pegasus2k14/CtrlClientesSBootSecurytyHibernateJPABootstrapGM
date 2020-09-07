package com.miguel.gm.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity    //indicamos que esta es una clase entidad
@Data     //utilizamos lombok
@Table(name = "rol")  //indicamos el nombre de la tabla en la BS que mapea
public class Rol implements Serializable{
    private static final long serialVersionUID = 1L;
    
    //Atributos
    @Id  //indicamos q este campo representa la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRol;
    
    @NotEmpty //validamos que este campo no pueda ser vacio
    private String nombre;
    
}
