package org.example.controller;


import io.swagger.v3.oas.annotations.Operation;
import org.example.model.Titor;
import org.example.repository.TitorRepository;
import org.example.service.TitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(TitorController.MAPPING)
public class TitorController {

    public static final String MAPPING = "/titores";

    @Autowired
    private TitorRepository titorRepository;
    @Autowired
    private TitorService titorService;

    @Operation(summary = "Crear un nuevo titor")
    @PostMapping("/crearTitor")
    public Titor crearTitor(@RequestBody Titor titor) {
        return titorService.crearOActualizarTitor(titor);
    }

    @Operation(summary = "Obtener todos los titores")
    @PostMapping("/obtenerTitores")
    public List<Titor> obtenerTitores() {
        return titorService.obtenerTodosLosTitores();
    }

    @Operation(summary = "Obtener titor por ID")
    @PostMapping("/obtenerTitor/{id}")
    public ResponseEntity<Titor> obtenerTitorporId(@PathVariable Long id) {
        Optional<Titor> titor = titorService.obtenerTitorPorId(id);
        return titor
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Actualizar un titor")
    @PutMapping("/actualizarTitor/{id}")
    public ResponseEntity<Titor> actualizarTitor(@PathVariable Long id, @RequestBody Titor titorDetails) {
        Optional<Titor> titorOptional = titorService.obtenerTitorPorId(id);
        if (titorOptional.isPresent()) {
            Titor titor = titorOptional.get();
            titor.setNome(titorDetails.getNome());
            titor.setApelidos(titorDetails.getApelidos());
            titor.setAlumnos(titorDetails.getAlumnos());
            Titor titorActualizado = titorService.crearOActualizarTitor(titor);
            return ResponseEntity.ok(titorActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un titor por ID")
    @DeleteMapping("/eliminarTitor/{id}")
    public ResponseEntity<Void> eliminarTitorPorId(@PathVariable Long id) {
        if (titorRepository.existsById(id)) {
            titorService.eliminarTitorPorId(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
