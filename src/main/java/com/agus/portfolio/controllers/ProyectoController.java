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
                                   @RequestParam("titulo") String titulo,
                                   @RequestParam("descripcion") String descripcion,
                                   @RequestParam("url") String url){

        Proyecto proyecto = interfaceProyecto.findProyecto(id);

        proyecto.setTitulo(titulo);
        proyecto.setDescripcion(descripcion);
        proyecto.setUrl(url);

        interfaceProyecto.saveProyecto(proyecto);

        return proyecto;
    }
}