package org.example.repository;

import org.example.model.Persoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersoaRepository extends JpaRepository<Persoa, Long> {
}
