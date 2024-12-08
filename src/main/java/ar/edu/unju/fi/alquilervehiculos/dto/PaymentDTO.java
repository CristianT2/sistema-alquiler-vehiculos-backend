package ar.edu.unju.fi.alquilervehiculos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentDTO {

    private RentDTO rent;
    private UserDTO user;
    private VehicleDTO vehicle;
    private LocalDateTime date;
    private double amount;
    private PaymentDTO paymentMethod;
}
