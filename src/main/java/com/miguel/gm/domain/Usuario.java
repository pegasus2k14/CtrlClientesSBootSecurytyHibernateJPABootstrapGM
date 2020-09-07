
package com.miguel.gm.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity    //indicamos que esta es una clase entidad
@Data     //utilizamos lombok
@Table(name = "usuario")  //indicamos el nombre de la tabla en la BS que mapea
public class Usuario implements Serializable {
    
    private static final long serialVersionUID =1L;
    
    //Atributos
    @Id  //indicamos q este atributo representa la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    
    @NotEmpty //indicamos q este campo no puede estar vacio
    private String username;
    
    @NotEmpty
    private String password;
    
    //Indicamos mapeo de la relacion entre las Tablas 'rol' y 'usuario'
    @OneToMany  //indicamos el tipo de relacion
    @JoinColumn(name = "id_usuario") //indicamos la columna de union de la relacion en la tabla Rol
    private List<Rol> roles;
}
