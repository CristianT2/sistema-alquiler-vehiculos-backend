package ar.edu.unju.fi.alquilervehiculos.repository;

import ar.edu.unju.fi.alquilervehiculos.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    // Método para encontrar un vehículo por su matrícula
    Optional<Vehicle> findByTuition(String tuition);

    // Método para encontrar todos los vehículos de una categoría específica
    List<Vehicle> findByCategory(Category category);

    // Método para encontrar todos los vehículos de una marca específica
    List<Vehicle> findByBrand(Brand brand);

    // Método para encontrar todos los vehículos de un modelo específico
    List<Vehicle> findByModel(Model model);

    // Método para encontrar todos los vehículos de un color específico
    List<Vehicle> findByColor(Color color);

    // Método para encontrar todos los vehículos de un estado específico
    List<Vehicle> findByStateVehicle(StateVehicle stateVehicle);
}
