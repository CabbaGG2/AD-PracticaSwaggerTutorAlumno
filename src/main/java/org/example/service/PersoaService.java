package org.example.service;

import jakarta.transaction.Transactional;
import org.example.model.Persoa;
import org.example.repository.PersoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersoaService {

    private final PersoaRepository persoaRepository;

    @Autowired
    public PersoaService(PersoaRepository persoaRepository) {
        this.persoaRepository = persoaRepository;
    }
    @Transactional
    public Persoa crearOuActualizarPersoa(Persoa persoa) {
        return persoaRepository.save(persoa);
    }

    public List<Persoa> obterTodasAsPersoas() {
        return persoaRepository.findAll();
    }

    public Optional<Persoa> obterPersoaPorId(Long id) { // Cambiado a Long
        return persoaRepository.findById(id);
    }
}
