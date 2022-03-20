package com.agus.portfolio.services;

import com.agus.portfolio.repositories.EducacionRepository;
import com.agus.portfolio.entities.Educacion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducacionService implements IEducacionService {

    private final EducacionRepository educacionRepository;

    public EducacionService(EducacionRepository educacionRepository) {
        this.educacionRepository = educacionRepository;
    }

    @Override
    public List<Educacion> getEducaciones(){
        return educacionRepository.findAll();
    }

    @Override
    public void saveEducacion(Educacion educacion){
        educacionRepository.save(educacion);
    }

    @Override
    public void deleteEducacion(Long id){
        educacionRepository.deleteById(id);
    }

    @Override
    public Educacion findEducacion(Long id){
        return educacionRepository.findById(id).orElse(null);
    }
}
