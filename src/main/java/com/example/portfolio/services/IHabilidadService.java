package com.example.portfolio.services;

import com.example.portfolio.models.Habilidad;

import java.util.List;

public interface IHabilidadService {

    public List<Habilidad> getHabilidades();

    public void saveHabilidad(Habilidad habilidad);

    public void deleteHabilidad(Long id);

    public Habilidad findHabilidad(Long id);
}
