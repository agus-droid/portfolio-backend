package com.agus.portfolio.services;

import com.agus.portfolio.entities.Habilidad;
import com.agus.portfolio.repositories.HabilidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabilidadService implements IHabilidadService {

    private final HabilidadRepository habilidadRepository;

    public HabilidadService(HabilidadRepository habilidadRepository) {
        this.habilidadRepository = habilidadRepository;
    }

    @Override
    public List<Habilidad> getHabilidades(){
        return habilidadRepository.findAll();
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
        return habilidadRepository.findById(id).orElse(null);
    }
}
