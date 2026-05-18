package org.smartbook.repository;

import org.smartbook.model.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, Integer> {
    Optional<Evaluacion> findByNombre(String nombre);
    List<Evaluacion> findByCursoId(Integer cursoId);
}

