package com.agus.portfolio.controllers;

import com.agus.portfolio.entities.Educacion;
import com.agus.portfolio.services.IEducacionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class EducacionController {

    private final IEducacionService interfaceEducacion;

    public EducacionController(IEducacionService interfaceEducacion) {
        this.interfaceEducacion = interfaceEducacion;
    }


    @GetMapping("/educacion")
    public List<Educacion> getEducaciones(){
        return interfaceEducacion.getEducaciones();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/educacion/nueva")
    public String createEducacion(@RequestBody Educacion educacion){
        interfaceEducacion.saveEducacion(educacion);
        return "Habilidad registrada correctamente";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/educacion/borrar/{id}")
    public String deleteEducacion(@PathVariable Long id){
        interfaceEducacion.deleteEducacion(id);
        return "Educacion eliminada correctamente";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/educacion/editar/{id}")
    public Educacion editEducacion(@PathVariable Long id,
                                   @RequestParam("titulo") String titulo,
                                   @RequestParam("descripcion") String descripcion,
                                   @RequestParam("imagen") String imagen,
                                   @RequestParam("fecha") String fecha){

        Educacion educacion = interfaceEducacion.findEducacion(id);

        educacion.setTitulo(titulo);
        educacion.setDescripcion(descripcion);
        educacion.setImagen(imagen);
        educacion.setFecha(fecha);

        interfaceEducacion.saveEducacion(educacion);

        return educacion;
    }
}