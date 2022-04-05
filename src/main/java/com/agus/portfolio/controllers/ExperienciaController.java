package com.agus.portfolio.controllers;

import com.agus.portfolio.entities.Experiencia;
import com.agus.portfolio.services.IExperienciaService;
import com.agus.portfolio.utils.FileUploadUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

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
    public String createExperiencia(@RequestParam("titulo") String titulo,
                                    @RequestParam("descripcion") String descripcion,
                                    @RequestParam("imagen") MultipartFile imagen,
                                    @RequestParam("fecha") String fecha) throws IOException {

        Experiencia experiencia = new Experiencia();
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(imagen.getOriginalFilename()));
        String fileCode = FileUploadUtil.saveFile(fileName, imagen);
        experiencia.setTitulo(titulo);
        experiencia.setDescripcion(descripcion);
        experiencia.setImagen(fileCode);
        experiencia.setFecha(fecha);
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
                                       @RequestParam("imagen") MultipartFile imagen,
                                       @RequestParam("fecha") String fecha) throws IOException {

        Experiencia experiencia = interfaceExperiencia.findExperiencia(id);
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(imagen.getOriginalFilename()));
        String fileCode = FileUploadUtil.saveFile(fileName, imagen);
        experiencia.setTitulo(titulo);
        experiencia.setDescripcion(descripcion);
        experiencia.setImagen(fileCode);
        experiencia.setFecha(fecha);
        interfaceExperiencia.saveExperiencia(experiencia);
        return experiencia;
    }
}
