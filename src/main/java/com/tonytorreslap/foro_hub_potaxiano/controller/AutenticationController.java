package com.tonytorreslap.foro_hub_potaxiano.controller;

import com.tonytorreslap.foro_hub_potaxiano.domain.usuario.DatosAutenticacionUsuario;
import com.tonytorreslap.foro_hub_potaxiano.domain.usuario.DatosJWTToken;
import com.tonytorreslap.foro_hub_potaxiano.domain.usuario.Usuario;
import com.tonytorreslap.foro_hub_potaxiano.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DatosJWTToken> autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario) {

        Authentication authToken = new UsernamePasswordAuthenticationToken(
                datosAutenticacionUsuario.correoElectronico(),
                datosAutenticacionUsuario.contrasena()
        );

        // Verificamos las credenciales
        var usuarioAutenticado = authenticationManager.authenticate(authToken);

        // Si todo está bien, generamos el token
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());

        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }
}
