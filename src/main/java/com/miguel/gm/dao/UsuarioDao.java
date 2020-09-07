package com.miguel.gm.dao;

import com.miguel.gm.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDao extends JpaRepository<Usuario, Long>{
    //firma de metodo personalizada
     Usuario findByUsername(String username);
}
