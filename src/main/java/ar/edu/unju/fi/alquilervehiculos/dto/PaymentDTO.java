package ar.edu.unju.fi.alquilervehiculos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentDTO {

    private Integer id;
    private RentDTO rent;
    private UserDTO user;
    private VehicleDTO vehicle;
    private LocalDateTime date;
    private double amount;
    private PaymentDTO paymentMethod;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RentDTO getRent() {
        return rent;
    }

    public void setRent(RentDTO rent) {
        this.rent = rent;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public VehicleDTO getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleDTO vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentDTO getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentDTO paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
