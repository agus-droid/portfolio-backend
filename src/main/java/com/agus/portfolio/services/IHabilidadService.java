package com.agus.portfolio.services;

import com.agus.portfolio.entities.Habilidad;

import java.util.List;

public interface IHabilidadService {

    public List<Habilidad> getHabilidades();

    public void saveHabilidad(Habilidad habilidad);

    public void deleteHabilidad(Long id);

    public Habilidad findHabilidad(Long id);
}
