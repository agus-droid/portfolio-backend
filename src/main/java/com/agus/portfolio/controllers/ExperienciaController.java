package com.agus.portfolio.controllers;

import com.agus.portfolio.entities.Experiencia;
import com.agus.portfolio.services.IExperienciaService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ExperienciaController {

    private final IExperienciaService interfaceExperiencia;

    public ExperienciaController(IExperienciaService interfaceExperiencia) {
        this.interfaceExperiencia = interfaceExperiencia;
    }


    @GetMapping("/experiencia")
    public List<Experiencia> getExperiencias(){
        return interfaceExperiencia.getExperiencias();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/experiencia/nueva")
    public String createExperiencia(@RequestBody Experiencia experiencia){
        interfaceExperiencia.saveExperiencia(experiencia);
        return "Experiencia registrada correctamente";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/experiencia/borrar/{id}")
    public String deleteExperiencia(@PathVariable Long id){
        interfaceExperiencia.deleteExperiencia(id);
        return "Experiencia eliminada correctamente";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/experiencia/editar/{id}")
    public Experiencia editExperiencia(@PathVariable Long id,
                                       @RequestParam("titulo") String titulo,
                                       @RequestParam("descripcion") String descripcion,
                                       @RequestParam("imagen") String imagen,
                                       @RequestParam("fecha") String fecha){

        Experiencia experiencia = interfaceExperiencia.findExperiencia(id);

        experiencia.setTitulo(titulo);
        experiencia.setDescripcion(descripcion);
        experiencia.setImagen(imagen);
        experiencia.setFecha(fecha);

        interfaceExperiencia.saveExperiencia(experiencia);

        return experiencia;
    }
}
