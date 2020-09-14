package com.miguel.gm.web;

import com.miguel.gm.domain.Persona;
import com.miguel.gm.service.PersonaService;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//Indicamos que la Clase sera un controlador de Spring MVC
@Controller
@Slf4j  //Agregamos Log a la Clase
public class ControladorInicio {
    
    //Inyectamos una instancia de la interfaz de servicio
    @Autowired
    private PersonaService personaService;

    //Indicamos el Path para acceder al metodo
    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user) {
         
        //Mensaje de Log
        log.info("ejecutando el controlador Spring MVC");
        log.info("Usuario que ha iniciado sesion: "+user.getUsername());
        //Recuperamos la Lista de Personas
        List<Persona> listPersona = personaService.listarPersonas();
        
        //Calculando el Saldo Total
        Double saldoTotal = 0D;
        for(Persona persona:listPersona){
            saldoTotal += persona.getSaldo();
        }

        //Agregamos la lista de instancias de Persona.java al Model
        model.addAttribute("personas", listPersona);
        //Agregando Saldo Total al Model
        model.addAttribute("saldoTotal", saldoTotal);
        //Agregamos el total de clientes registrados
        model.addAttribute("totalClientes", listPersona.size());
        
        //Retornamos el nombre de la vista a desplegar
        return "index";
    }
    
    //Direccionar a Agregar una nueva Persona
    @GetMapping("/agregar")
    public String agregar(Persona persona){
     
        return "modificar";
    }
    
    //Direccionar a modificar una persona
    @GetMapping("/editar/{idPersona}")
    public String modificar(Persona persona, Model model){
        //Recuperamos la Persona a modificar desde la BD
        persona = personaService.encontrarPerrsona(persona);
     
        //Agregamos la Persona obtenida al model
        model.addAttribute("persona", persona);
        return "modificar";
    }
    
    //Agregar/Modificar una Persona
    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors errors){
        if(errors.hasErrors()){
            return "modificar";
        }    
        personaService.guardar(persona);
        return "redirect:/";
    }
    
    @GetMapping("/eliminar")
    public String eliminar(Persona persona){
        personaService.eliminar(persona);
        //Direccionamos al Path de inicio
        return "redirect:/";
    }
    
}

