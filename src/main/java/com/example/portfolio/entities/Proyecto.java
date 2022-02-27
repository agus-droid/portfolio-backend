package com.example.portfolio.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter @Setter
@Entity
public class Proyecto {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    private String titulo;
    private String descripcion;
    private String url;
}