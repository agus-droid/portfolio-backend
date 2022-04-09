package com.agus.portfolio.services;

import com.agus.portfolio.repositories.AcercaRepository;
import com.agus.portfolio.entities.Acerca;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcercaService implements IAcercaService {

    private final AcercaRepository acercaRepository;

    public AcercaService(AcercaRepository acercaRepository) {
        this.acercaRepository = acercaRepository;
    }

    @Override
    public List<Acerca> getAcercas(){
        return acercaRepository.findAll();
    }

    @Override
    public void saveAcerca(Acerca acerca){
        acercaRepository.save(acerca);
    }

    @Override
    public void deleteAcerca(Long id){
        acercaRepository.deleteById(id);
    }

    @Override
    public Acerca findAcerca(Long id){
        return acercaRepository.findById(id).orElse(null);
    }
}
