package ar.edu.unju.fi.alquilervehiculos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RentDTO {

    private UserDTO user;
    private VehicleDTO vehicle;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate returnDate;
    private double price;
    private StateRentDTO stateRent;
}
