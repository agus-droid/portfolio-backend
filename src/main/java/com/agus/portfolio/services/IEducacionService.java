package com.agus.portfolio.services;

import com.agus.portfolio.entities.Educacion;

import java.util.List;

public interface IEducacionService {

    public List<Educacion> getEducaciones();

    public void saveEducacion(Educacion educacion);

    public void deleteEducacion(Long id);

    public Educacion findEducacion(Long id);
}
