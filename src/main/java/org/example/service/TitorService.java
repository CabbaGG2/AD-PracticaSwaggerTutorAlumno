package org.example.service;


import jakarta.transaction.Transactional;
import org.example.model.Titor;
import org.example.repository.TitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TitorService {

    private final TitorRepository titorRepository;

    @Autowired
    public TitorService(TitorRepository titorRepository) {
        this.titorRepository = titorRepository;
    }

    @Transactional
    public Titor crearOActualizarTitor(Titor titor) {
        return titorRepository.save(titor);
    }

    public List<Titor> obtenerTodosLosTitores() {
        return titorRepository.findAll();
    }

    public Optional<Titor> obtenerTitorPorId(Long id) {
        return titorRepository.findById(id);
    }

    public void eliminarTitorPorId(Long id) {
        titorRepository.deleteById(id);
    }
}
