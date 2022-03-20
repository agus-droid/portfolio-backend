package com.agus.portfolio.controllers;

import com.agus.portfolio.entities.Habilidad;
import com.agus.portfolio.services.IHabilidadService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class HabilidadController {

    private final IHabilidadService interfaceHabilidad;

    public HabilidadController(IHabilidadService interfaceHabilidad) {
        this.interfaceHabilidad = interfaceHabilidad;
    }


    @GetMapping("/habilidad")
    public List<Habilidad> getHabilidades(){
        return interfaceHabilidad.getHabilidades();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/habilidad/nueva")
    public String createHabilidad(@RequestBody Habilidad habilidad){
        interfaceHabilidad.saveHabilidad(habilidad);
        return "Habilidad registrada correctamente";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/habilidad/borrar/{id}")
    public String deleteHabilidad(@PathVariable Long id){
        interfaceHabilidad.deleteHabilidad(id);
        return "Habilidad eliminada correctamente";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/habilidad/editar/{id}")
    public Habilidad editHabilidad(@PathVariable Long id,
                                   @RequestParam("titulo") String titulo,
                                   @RequestParam("porcentaje") double porcentaje){

        Habilidad habilidad = interfaceHabilidad.findHabilidad(id);

        habilidad.setTitulo(titulo);
        habilidad.setPorcentaje(porcentaje);

        interfaceHabilidad.saveHabilidad(habilidad);

        return habilidad;
    }
}
