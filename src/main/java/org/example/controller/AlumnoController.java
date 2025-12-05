package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.model.Alumno;
import org.example.repository.AlumnoRepository;
import org.example.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(AlumnoController.MAPPING)
public class AlumnoController {

    public static final String MAPPING = "/alumnos";

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private AlumnoService alumnoService;

    @Operation(summary = "Crear un nuevo alumno")
    @PostMapping("/crearAlumno")
    public Alumno crearAlumno(@RequestBody Alumno alumno) {
        return alumnoService.crearOuActualizarAlumno(alumno);
    }

    @Operation(summary = "Obtener todos los alumnos")
    @PostMapping("/obtenerAlumnos")
    public List<Alumno> obtenerAlumnos() {
        return alumnoService.obtenerTodosLosAlumnos();
    }

    @Operation(summary = "Obtener alumno por ID")
    @PostMapping("/obtenerAlumno/{id}")
    public ResponseEntity<Alumno> obtenerAlumnoporId(@PathVariable Long id) {
        Optional<Alumno> alumno = alumnoService.obtenerAlumnoPorId(id);
        return alumno
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary="Actualizar un alumno")
    @PutMapping("/actualizarAlumno/{id}")
    public ResponseEntity<Alumno> actualizarAlumno(@PathVariable Long id, @RequestBody Alumno alumnoDetails) {
        Optional<Alumno> alumnoOptional = alumnoService.obtenerAlumnoPorId(id);
        if (alumnoOptional.isPresent()) {
            Alumno alumno = alumnoOptional.get();
            alumno.setNome(alumnoDetails.getNome());
            alumno.setApelidos(alumnoDetails.getApelidos());
            alumno.setTitor(alumnoDetails.getTitor());
            Alumno alumnoActualizado = alumnoService.crearOuActualizarAlumno(alumno);
            return ResponseEntity.ok(alumnoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "eliminar un alumno por ID")
    @DeleteMapping("/eliminarAlumno/{id}")
    public ResponseEntity<Void> eliminarAlumnoPorId(@PathVariable Long id) {
        if(alumnoRepository.existsById(id)){
            alumnoService.eliminarAlumnoPorId(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
