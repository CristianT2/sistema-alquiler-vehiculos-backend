package ar.edu.unju.fi.alquilervehiculos.repository;

import ar.edu.unju.fi.alquilervehiculos.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, Integer> {
    boolean existsByName(String name);
}
