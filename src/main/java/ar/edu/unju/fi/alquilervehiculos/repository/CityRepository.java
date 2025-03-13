package ar.edu.unju.fi.alquilervehiculos.repository;

import ar.edu.unju.fi.alquilervehiculos.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    boolean existsByName(String name);
    boolean existsByPostalCode(String postalCode);
    boolean existsByPostalCodeAndIdNot(String postalCode, Integer id);
    Optional<City> findByPostalCode(String postalCode);
}
