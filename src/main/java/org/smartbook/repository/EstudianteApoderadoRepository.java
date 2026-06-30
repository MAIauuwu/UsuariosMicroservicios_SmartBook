package org.smartbook.repository;

import org.smartbook.model.EstudianteApoderado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteApoderadoRepository extends JpaRepository<EstudianteApoderado, Integer> {

    List<EstudianteApoderado> findByEstudianteId(Integer estudianteId);

    List<EstudianteApoderado> findByApoderadoId(Integer apoderadoId);

    boolean existsByEstudianteIdAndApoderadoId(Integer estudianteId, Integer apoderadoId);
}
