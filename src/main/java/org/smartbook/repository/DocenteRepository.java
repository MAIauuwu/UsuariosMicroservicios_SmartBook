package org.smartbook.repository;

import org.smartbook.model.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Integer> {
    Optional<Docente> findByNumeroColegiatura(String numeroColegiatura);
    List<Docente> findByEspecialidad(String especialidad);
    boolean existsByNumeroColegiatura(String numeroColegiatura);
}
