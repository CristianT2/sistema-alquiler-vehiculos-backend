package ar.edu.unju.fi.alquilervehiculos.repository;

import ar.edu.unju.fi.alquilervehiculos.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
}
