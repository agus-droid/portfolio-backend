package com.example.portfolio.security.controller;

import com.example.portfolio.dto.Mensaje;
import com.example.portfolio.security.dto.JwtDto;
import com.example.portfolio.security.dto.LoginUsuario;
import com.example.portfolio.security.dto.NuevoUsuario;
import com.example.portfolio.security.entities.Rol;
import com.example.portfolio.security.entities.Usuario;
import com.example.portfolio.security.enums.RolNombre;
import com.example.portfolio.security.jwt.JwtProvider;
import com.example.portfolio.security.service.RolService;
import com.example.portfolio.security.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.Null;

import static org.hibernate.sql.InFragment.NULL;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    final
    PasswordEncoder passwordEncoder;

    final
    AuthenticationManager authenticationManager;

    final
    UsuarioService usuarioService;

    final
    RolService rolService;

    final
    JwtProvider jwtProvider;

    public AuthController(PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager,
                          UsuarioService usuarioService,
                          RolService rolService,
                          JwtProvider jwtProvider) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.usuarioService = usuarioService;
        this.rolService = rolService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Ha ocurrido un error"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByUsername(nuevoUsuario.getUsername()))
            return new ResponseEntity(new Mensaje("Nombre de usuario no disponible"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new Mensaje("Email no disponible"), HttpStatus.BAD_REQUEST);
        Usuario usuario =
                new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getUsername(), nuevoUsuario.getEmail(),
                        passwordEncoder.encode(nuevoUsuario.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        if(nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("Usuario registrado correctamente"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Ha ocurrido un error"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}
