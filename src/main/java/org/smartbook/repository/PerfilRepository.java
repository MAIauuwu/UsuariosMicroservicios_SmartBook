package org.smartbook.repository;

import org.smartbook.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Integer> {
    Optional<Perfil> findByUserId(Integer userId);
    boolean existsByUserId(Integer userId);
}
