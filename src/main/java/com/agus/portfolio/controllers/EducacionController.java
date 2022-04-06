package com.agus.portfolio.controllers;

import com.agus.portfolio.dto.Mensaje;
import com.agus.portfolio.entities.Educacion;
import com.agus.portfolio.services.IEducacionService;
import com.agus.portfolio.utils.FileUploadUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

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
    public ResponseEntity<?> createEducacion(@RequestParam("titulo") String titulo,
                                  @RequestParam("descripcion") String descripcion,
                                  @RequestParam("imagen") MultipartFile imagen,
                                  @RequestParam("fecha") String fecha) throws IOException {

        Educacion educacion = new Educacion();
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(imagen.getOriginalFilename()));
        String fileCode = FileUploadUtil.saveFile(fileName, imagen);
        educacion.setTitulo(titulo);
        educacion.setDescripcion(descripcion);
        educacion.setImagen(fileCode);
        educacion.setFecha(fecha);
        interfaceEducacion.saveEducacion(educacion);
        return new ResponseEntity(new Mensaje("Educacion registrada correctamente"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/educacion/borrar/{id}")
    public ResponseEntity<?> deleteEducacion(@PathVariable Long id){
        interfaceEducacion.deleteEducacion(id);
        return new ResponseEntity(new Mensaje("Educacion eliminada correctamente"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/educacion/editar/{id}")
    public Educacion editEducacion(@PathVariable Long id,
                                   @RequestParam("titulo") String titulo,
                                   @RequestParam("descripcion") String descripcion,
                                   @RequestParam("imagen") MultipartFile imagen,
                                   @RequestParam("fecha") String fecha) throws IOException {

        Educacion educacion = interfaceEducacion.findEducacion(id);
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(imagen.getOriginalFilename()));
        String fileCode = FileUploadUtil.saveFile(fileName, imagen);
        educacion.setTitulo(titulo);
        educacion.setDescripcion(descripcion);
        educacion.setImagen(fileCode);
        educacion.setFecha(fecha);
        interfaceEducacion.saveEducacion(educacion);
        return educacion;
    }
}