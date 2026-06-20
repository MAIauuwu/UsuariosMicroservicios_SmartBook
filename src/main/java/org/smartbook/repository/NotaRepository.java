package org.smartbook.repository;

import org.smartbook.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Integer> {
    List<Nota> findByEstudianteId(Integer estudianteId);
    List<Nota> findByEvaluacionId(Integer evaluacionId);
    Optional<Nota> findByEstudianteIdAndEvaluacionId(Integer estudianteId, Integer evaluacionId);
}

