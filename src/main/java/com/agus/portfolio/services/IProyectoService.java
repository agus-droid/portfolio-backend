package com.agus.portfolio.services;

import com.agus.portfolio.entities.Proyecto;

import java.util.List;

public interface IProyectoService {

    public List<Proyecto> getProyectos();

    public void saveProyecto(Proyecto proyecto);

    public void deleteProyecto(Long id);

    public Proyecto findProyecto(Long id);
}

