package com.miguel.gm.service;

import com.miguel.gm.dao.UsuarioDao;
import com.miguel.gm.domain.Rol;
import com.miguel.gm.domain.Usuario;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//indicamos que es una Clase de servicio de Spring
@Service("userDetailsService")   
@Slf4j   //Agregamos manejo de log
public class UsuarioService implements UserDetailsService{

    //inyectando una implementacion de UsuarioDao
    @Autowired
    private UsuarioDao usuarioDao;
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //obtenemos una instancia de Usuario en base a su atributo 'username'
        Usuario usuario = usuarioDao.findByUsername(username);
        
        if(usuario == null){
            throw new UsernameNotFoundException(username);
        }
        //Creamos una lista de objetos Interface GrantedAuthority
        List<GrantedAuthority> listRoles = new ArrayList<GrantedAuthority>();
        
        //Obtenemos los Roles del usuario recuperado y los envolvemos
        //en una instancia de SympleGrantedAuthority  que implementa la interface
        //GrantedAuthority y los agregamos a la lista listRoles
        for(Rol rol:usuario.getRoles()){
            listRoles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        
        //retornamos una instancia de User de Spring
        return new User(usuario.getUsername(), usuario.getPassword(), listRoles);
        
    }
    
}
