package com.agus.portfolio.controllers;

import com.agus.portfolio.dto.Mensaje;
import com.agus.portfolio.entities.Acerca;
import com.agus.portfolio.services.IAcercaService;
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
public class AcercaController {

    private final IAcercaService interfaceAcerca;

    public AcercaController(IAcercaService interfaceAcerca) {
        this.interfaceAcerca = interfaceAcerca;
    }


    @GetMapping("/acerca")
    public List<Acerca> getAcercas(){
        return interfaceAcerca.getAcercas();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/acerca/nueva")
    public ResponseEntity<?> createAcerca(@RequestParam("nombre") String nombre,
                                          @RequestParam("titulo") String titulo,
                                          @RequestParam("foto") MultipartFile foto,
                                          @RequestParam("banner") MultipartFile banner,
                                          @RequestParam("resumen") String resumen) throws IOException {

        Acerca acerca = new Acerca();
        String fileNameFoto = StringUtils.cleanPath(Objects.requireNonNull(foto.getOriginalFilename()));
        String fileCodeFoto = FileUploadUtil.saveFile(fileNameFoto, foto);
        String fileNameBanner = StringUtils.cleanPath(Objects.requireNonNull(banner.getOriginalFilename()));
        String fileCodeBanner = FileUploadUtil.saveFile(fileNameBanner, banner);
        acerca.setTitulo(titulo);
        acerca.setNombre(nombre);
        acerca.setFoto(fileCodeFoto);
        acerca.setBanner(fileCodeBanner);
        acerca.setResumen(resumen);
        interfaceAcerca.saveAcerca(acerca);
        return new ResponseEntity(new Mensaje("Acerca registrada correctamente"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/acerca/borrar/{id}")
    public ResponseEntity<?> deleteAcerca(@PathVariable Long id){
        interfaceAcerca.deleteAcerca(id);
        return new ResponseEntity(new Mensaje("Acerca eliminada correctamente"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/acerca/editar/{id}")
    public Acerca editAcerca(@PathVariable Long id,
                             @RequestParam("nombre") String nombre,
                             @RequestParam("titulo") String titulo,
                             @RequestParam("foto") MultipartFile foto,
                             @RequestParam("banner") MultipartFile banner,
                             @RequestParam("resumen") String resumen) throws IOException {

        Acerca acerca = interfaceAcerca.findAcerca(id);
        String fileNameFoto = StringUtils.cleanPath(Objects.requireNonNull(foto.getOriginalFilename()));
        String fileCodeFoto = FileUploadUtil.saveFile(fileNameFoto, foto);
        String fileNameBanner = StringUtils.cleanPath(Objects.requireNonNull(banner.getOriginalFilename()));
        String fileCodeBanner = FileUploadUtil.saveFile(fileNameBanner, banner);
        acerca.setTitulo(titulo);
        acerca.setNombre(nombre);
        acerca.setFoto(fileCodeFoto);
        acerca.setBanner(fileCodeBanner);
        acerca.setResumen(resumen);
        interfaceAcerca.saveAcerca(acerca);
        return acerca;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/nombre/editar/{id}")
    public Acerca editNombre(@PathVariable Long id,
                             @RequestParam("nombre") String nombre) {

        Acerca acerca = interfaceAcerca.findAcerca(id);
        acerca.setNombre(nombre);
        interfaceAcerca.saveAcerca(acerca);
        return acerca;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/titulo/editar/{id}")
    public Acerca editTitulo(@PathVariable Long id,
                             @RequestParam("titulo") String titulo) {
        Acerca acerca = interfaceAcerca.findAcerca(id);
        acerca.setTitulo(titulo);
        interfaceAcerca.saveAcerca(acerca);
        return acerca;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/foto/editar/{id}")
    public Acerca editFoto(@PathVariable Long id,
                           @RequestParam("foto") MultipartFile foto) throws IOException {

        Acerca acerca = interfaceAcerca.findAcerca(id);
        String fileNameFoto = StringUtils.cleanPath(Objects.requireNonNull(foto.getOriginalFilename()));
        String fileCodeFoto = FileUploadUtil.saveFile(fileNameFoto, foto);
        acerca.setFoto(fileCodeFoto);
        interfaceAcerca.saveAcerca(acerca);
        return acerca;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/banner/editar/{id}")
    public Acerca editBanner(@PathVariable Long id,
                             @RequestParam("banner") MultipartFile banner) throws IOException {

        Acerca acerca = interfaceAcerca.findAcerca(id);
        String fileNameBanner = StringUtils.cleanPath(Objects.requireNonNull(banner.getOriginalFilename()));
        String fileCodeBanner = FileUploadUtil.saveFile(fileNameBanner, banner);
        acerca.setBanner(fileCodeBanner);
        interfaceAcerca.saveAcerca(acerca);
        return acerca;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/resumen/editar/{id}")
    public Acerca editResumen(@PathVariable Long id,
                             @RequestParam("resumen") String resumen) {

        Acerca acerca = interfaceAcerca.findAcerca(id);
        acerca.setResumen(resumen);
        interfaceAcerca.saveAcerca(acerca);
        return acerca;
    }
}