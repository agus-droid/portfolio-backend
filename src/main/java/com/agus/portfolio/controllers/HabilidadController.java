package com.agus.portfolio.controllers;

import com.agus.portfolio.dto.Mensaje;
import com.agus.portfolio.entities.Habilidad;
import com.agus.portfolio.services.IHabilidadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> createHabilidad(@RequestBody Habilidad habilidad){
        interfaceHabilidad.saveHabilidad(habilidad);
        return new ResponseEntity(new Mensaje("Habilidad registrada correctamente"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/habilidad/borrar/{id}")
    public ResponseEntity<?> deleteHabilidad(@PathVariable Long id){
        interfaceHabilidad.deleteHabilidad(id);
        return new ResponseEntity(new Mensaje("Habilidad eliminada correctamente"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/habilidad/editar/{id}")
    public Habilidad editHabilidad(@PathVariable Long id,
                                   @RequestBody Habilidad habilidadNueva){

        Habilidad habilidad = interfaceHabilidad.findHabilidad(id);

        habilidad.setTitulo(habilidadNueva.getTitulo());
        habilidad.setPorcentaje(habilidadNueva.getPorcentaje());

        interfaceHabilidad.saveHabilidad(habilidad);

        return habilidad;
    }
}
