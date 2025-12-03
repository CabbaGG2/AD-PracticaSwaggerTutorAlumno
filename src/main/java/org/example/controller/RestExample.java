package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.model.Persoa;
import org.example.repository.PersoaRepository;
import org.example.service.PersoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(RestExample.MAPPING)
public class RestExample {

    public static final String MAPPING = "/base";

    @Autowired
    private PersoaRepository persoaRepository;
    @Autowired
    private PersoaService persoaService;


    @Operation(summary = "Método que saúda")
    @PostMapping("/saudo")
    public String saudo() {
        return "Boas";
    }

    @Operation(summary = "Crear unha nova persoa")
    @PostMapping("/persoa")
    public Persoa crearPersoa(@RequestBody Persoa persoa) {
        return persoaService.crearOuActualizarPersoa(persoa);
    }

    @Operation(summary = "Obter todas as persoas")
    @GetMapping("/persoas")
    public List<Persoa> obterPersoas() {
        return persoaService.obterTodasAsPersoas();
    }

    @Operation(summary = "Obter persoa por ID")
    @GetMapping("/persoa/{id}")
    public ResponseEntity<Persoa> obterPersoaPorId(@PathVariable Long id) {
        Optional<Persoa> persoa = persoaService.obterPersoaPorId(id);
        return persoa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Actualizar unha persoa")
    @PutMapping("/persoa/{id}")
    public ResponseEntity<Persoa> actualizarPersoa(@PathVariable Long id, @RequestBody Persoa persoaDetails) {
        Optional<Persoa> persoaOptional = persoaService.obterPersoaPorId(id);
        if (persoaOptional.isPresent()) {
            Persoa persoa = persoaOptional.get();
            persoa.setNome(persoaDetails.getNome());
            persoa.setIdade(persoaDetails.getIdade());
            persoa.setEmail(persoaDetails.getEmail());
            Persoa persoaActualizada = persoaService.crearOuActualizarPersoa(persoa);
            return ResponseEntity.ok(persoaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar unha persoa")
    @DeleteMapping("/persoa/{id}")
    public ResponseEntity<Void> eliminarPersoa(@PathVariable Long id) {
        if (persoaRepository.existsById(id)) {
            persoaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
