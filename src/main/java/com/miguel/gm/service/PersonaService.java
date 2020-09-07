package com.miguel.gm.service;

import com.miguel.gm.domain.Persona;
import java.util.List;

public interface PersonaService {
    //firmas de metodos
    public List<Persona> listarPersonas();
    
    public void guardar(Persona persona);
    
    public void eliminar(Persona persona);
    
    public Persona encontrarPerrsona(Persona persona);
}
