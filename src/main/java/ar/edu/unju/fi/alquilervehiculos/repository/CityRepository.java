package ar.edu.unju.fi.alquilervehiculos.repository;

import ar.edu.unju.fi.alquilervehiculos.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
}
