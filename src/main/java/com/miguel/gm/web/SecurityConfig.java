package com.miguel.gm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration  //indicamos que esta es una Clase de configuracion de Spring
@EnableWebSecurity  //habilitamos seguridad Web de Spring

//extendemos  WebSecurityConfigurerAdapter para habilitar la
//seguridad HTTP en Spring
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    //inyectamos una implmentacion de UsuarioService
    @Autowired
    private UserDetailsService userDetailsService;
    
    //Definimos este metodo como Bean y retornara un objeto BCryptPasswordEncoder
    //y estara disponible en el contenedor de Spring
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    
    //SOBRE ESCRIBIENDO METODOS
    
    //hacemos uso de la anotacion @Authowired para poder obtener el Bean
    //AuthenticationManagerBuilder auth y poder definir el tipo de autenticacion 
    //para la aplicacion
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
   
    
    @Override
    protected void configure(HttpSecurity https) throws Exception{
       https.authorizeRequests() //indicamos que el path de editar, agregar y eliminar estaran restringido
               .antMatchers("/editar/**","/agregar/**","/eliminar")
                 .hasRole("ADMIN")
               .antMatchers("/") //indicamos quien puede acceder al path raiz
                 .hasAnyRole("USER","ADMIN")
               .and()
               .formLogin().loginPage("/login")
               .and()
                 .exceptionHandling().accessDeniedPage("/errores/403")
               ;
    }
}
