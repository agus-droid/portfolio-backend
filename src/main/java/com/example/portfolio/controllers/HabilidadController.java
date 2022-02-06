package com.example.portfolio.controllers;

import com.example.portfolio.models.Habilidad;
import com.example.portfolio.services.IHabilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HabilidadController {

    @Autowired
    private IHabilidadService interfaceHabilidad;

    @GetMapping("/habilidades")
    public List<Habilidad> getHabilidades(){
        return interfaceHabilidad.getHabilidades();
    }

    @PostMapping("/habilidades/nueva")
    public String createHabilidad(@RequestBody Habilidad habilidad){
        interfaceHabilidad.saveHabilidad(habilidad);
        return "Habilidad registrada correctamente";
    }

    @DeleteMapping("/habilidades/borrar/{id}")
    public String deleteHabilidad(@PathVariable Long id){
        interfaceHabilidad.deleteHabilidad(id);
        return "Habilidad eliminada correctamente";
    }

    @PutMapping("/habilidades/editar/{id}")
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
