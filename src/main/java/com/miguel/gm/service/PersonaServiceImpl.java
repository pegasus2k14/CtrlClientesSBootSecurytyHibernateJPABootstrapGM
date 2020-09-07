package com.miguel.gm.service;

import com.miguel.gm.dao.PersonaDao;
import com.miguel.gm.domain.Persona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service //indicamos que es una Clase de Servicio de Spring
public class PersonaServiceImpl implements PersonaService{
  //inyectamos una instancia de la interfaz PersonaDao.java
    @Autowired
    private PersonaDao personaDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Persona> listarPersonas() {
        return (List<Persona>) personaDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Persona persona) {
        personaDao.save(persona);
    }

    @Override
    @Transactional
    public void eliminar(Persona persona) {
      personaDao.delete(persona);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona encontrarPerrsona(Persona persona) {
       //return personaDao.findById(persona.getIdPersona()).get();
       return personaDao.findById(persona.getIdPersona()).orElse(null);
    }
    
}
