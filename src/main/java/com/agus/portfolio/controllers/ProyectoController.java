package com.agus.portfolio.controllers;

import com.agus.portfolio.services.IProyectoService;
import com.agus.portfolio.entities.Proyecto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ProyectoController {

    private final IProyectoService interfaceProyecto;

    public ProyectoController(IProyectoService interfaceProyecto) {
        this.interfaceProyecto = interfaceProyecto;
    }


    @GetMapping("/proyecto")
    public List<Proyecto> getProyectos(){
        return interfaceProyecto.getProyectos();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/proyecto/nuevo")
    public String createProyecto(@RequestBody Proyecto proyecto){
        interfaceProyecto.saveProyecto(proyecto);
        return "Proyecto registrado correctamente";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/proyecto/borrar/{id}")
    public String deleteProyecto(@PathVariable Long id){
        interfaceProyecto.deleteProyecto(id);
        return "Proyecto eliminado correctamente";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/proyecto/editar/{id}")
    public Proyecto editProyecto(@PathVariable Long id,
                                 @RequestBody Proyecto proyectoNuevo){

        Proyecto proyecto = interfaceProyecto.findProyecto(id);

        proyecto.setTitulo(proyectoNuevo.getTitulo());
        proyecto.setDescripcion(proyectoNuevo.getDescripcion());
        proyecto.setUrl(proyectoNuevo.getUrl());

        interfaceProyecto.saveProyecto(proyecto);

        return proyecto;
    }
}