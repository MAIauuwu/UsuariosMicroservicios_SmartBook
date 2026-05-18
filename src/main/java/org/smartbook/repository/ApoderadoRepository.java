package org.smartbook.repository;

import org.smartbook.model.Apoderado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApoderadoRepository extends JpaRepository<Apoderado, Integer> {
    Optional<Apoderado> findByDocumentoIdentidad(String documentoIdentidad);
    List<Apoderado> findByParentesco(String parentesco);
    boolean existsByDocumentoIdentidad(String documentoIdentidad);
}
