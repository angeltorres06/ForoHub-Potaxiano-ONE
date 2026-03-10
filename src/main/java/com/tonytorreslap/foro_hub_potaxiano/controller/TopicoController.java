package com.tonytorreslap.foro_hub_potaxiano.controller;

import com.tonytorreslap.foro_hub_potaxiano.domain.curso.Curso;
import com.tonytorreslap.foro_hub_potaxiano.domain.curso.CursoRepository;
import com.tonytorreslap.foro_hub_potaxiano.domain.topico.*;
import com.tonytorreslap.foro_hub_potaxiano.domain.usuario.Usuario;
import com.tonytorreslap.foro_hub_potaxiano.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<String> registrarTopico(@RequestBody @Valid DatosRegistroTopico datos) {

        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            return ResponseEntity.badRequest().body("Errxr: Ya existe un tópicx con el mismo títulx y mensajx.");
        }

        Usuario autor = usuarioRepository.findById(datos.idAutor())
                .orElseThrow(() -> new IllegalArgumentException("Autxr no encontrade"));
        Curso curso = cursoRepository.findById(datos.idCurso())
                .orElseThrow(() -> new IllegalArgumentException("Cursx no encontrade"));

        Topico nuevoTopico = new Topico(
                null,
                datos.titulo(),
                datos.mensaje(),
                LocalDateTime.now(),
                StatusTopico.NO_RESPONDIDO,
                autor,
                curso
        );

        topicoRepository.save(nuevoTopico);

        return ResponseEntity.ok("¡Tópicx registrade exitosamentx!");
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopicos(
            @PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable paginacion) {

        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosListadoTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoTopico> detallarTopico(@PathVariable Long id) {

        var topicoOpcional = topicoRepository.findById(id);

        if (topicoOpcional.isPresent()) {
            var topico = topicoOpcional.get();
            return ResponseEntity.ok(new DatosListadoTopico(topico));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @jakarta.transaction.Transactional
    public ResponseEntity<DatosListadoTopico> actualizarTopico(
            @PathVariable Long id,
            @RequestBody DatosActualizarTopico datosActualizar) {

        var topicoOpcional = topicoRepository.findById(id);

        if (topicoOpcional.isPresent()) {
            var topico = topicoOpcional.get();
            topico.actualizarDatos(datosActualizar);

            return ResponseEntity.ok(new DatosListadoTopico(topico));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @jakarta.transaction.Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {

        var topicoOpcional = topicoRepository.findById(id);

        if (topicoOpcional.isPresent()) {
            topicoRepository.deleteById(id);
            // Retornamos 204 No Content como pide la regla de negocio
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
