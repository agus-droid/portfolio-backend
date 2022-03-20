package com.agus.portfolio.services;

import com.agus.portfolio.entities.Proyecto;
import com.agus.portfolio.repositories.ProyectoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProyectoService implements IProyectoService {

    private final ProyectoRepository proyectoRepository;

    public ProyectoService(ProyectoRepository proyectoRepository) {
        this.proyectoRepository = proyectoRepository;
    }

    @Override
    public List<Proyecto> getProyectos(){
        return proyectoRepository.findAll();
    }

    @Override
    public void saveProyecto(Proyecto proyecto){
        proyectoRepository.save(proyecto);
    }

    @Override
    public void deleteProyecto(Long id){
        proyectoRepository.deleteById(id);
    }

    @Override
    public Proyecto findProyecto(Long id){
        return proyectoRepository.findById(id).orElse(null);
    }
}
