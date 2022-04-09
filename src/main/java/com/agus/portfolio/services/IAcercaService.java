package com.agus.portfolio.services;

import com.agus.portfolio.entities.Acerca;

import java.util.List;

public interface IAcercaService {

    public List<Acerca> getAcercas();

    public void saveAcerca(Acerca acerca);

    public void deleteAcerca(Long id);

    public Acerca findAcerca(Long id);
}
