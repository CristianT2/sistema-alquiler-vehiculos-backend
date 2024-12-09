package ar.edu.unju.fi.alquilervehiculos.repository;

import ar.edu.unju.fi.alquilervehiculos.entities.Rent;
import ar.edu.unju.fi.alquilervehiculos.entities.StateRent;
import ar.edu.unju.fi.alquilervehiculos.entities.User;
import ar.edu.unju.fi.alquilervehiculos.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent, Integer> {

    // Método para encontrar todas las rentas de un usuario específico
    List<Rent> findByUser(User user);

    // Método para encontrar todas las rentas de un vehículo específico
    List<Rent> findByVehicle(Vehicle vehicle);

    // Método para encontrar todas las rentas que se encuentran en un estado específico
    List<Rent> findByStateRent(StateRent stateRent);

    // Método para encontrar todas las rentas que se han realizado en un rango de fechas específico
    List<Rent> findByStartDateBetween(LocalDate startDate, LocalDate endDate);

    // Método para encontrar todas las rentas que se han devuelto en un rango de fechas específico
    List<Rent> findByReturnDateBetween(LocalDate startDate, LocalDate endDate);
}
