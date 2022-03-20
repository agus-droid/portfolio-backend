package com.agus.portfolio.services;

import com.agus.portfolio.entities.Experiencia;

import java.util.List;

public interface IExperienciaService {

    public List<Experiencia> getExperiencias();

    public void saveExperiencia(Experiencia habilidad);

    public void deleteExperiencia(Long id);

    public Experiencia findExperiencia(Long id);
}
