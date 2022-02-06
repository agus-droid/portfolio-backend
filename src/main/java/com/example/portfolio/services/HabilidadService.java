package com.example.portfolio.services;

import com.example.portfolio.models.Habilidad;
import com.example.portfolio.repositories.HabilidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabilidadService implements IHabilidadService {

    @Autowired
    private HabilidadRepository habilidadRepository;

    @Override
    public List<Habilidad> getHabilidades(){
        List<Habilidad> Habilidades = habilidadRepository.findAll();
        return Habilidades;
    }

    @Override
    public void saveHabilidad(Habilidad habilidad){
        habilidadRepository.save(habilidad);
    }

    @Override
    public void deleteHabilidad(Long id){
        habilidadRepository.deleteById(id);
    }

    @Override
    public Habilidad findHabilidad(Long id){
        Habilidad habilidad = habilidadRepository.findById(id).orElse(null);
        return habilidad;
    }
}
