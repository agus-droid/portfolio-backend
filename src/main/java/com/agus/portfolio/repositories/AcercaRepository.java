package com.agus.portfolio.repositories;

import com.agus.portfolio.entities.Acerca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcercaRepository extends JpaRepository<Acerca, Long> {
}
