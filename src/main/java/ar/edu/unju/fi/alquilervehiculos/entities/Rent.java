package ar.edu.unju.fi.alquilervehiculos.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "rents")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @NotEmpty
    @Column(nullable = false, name = "start_date")
    private LocalDate startDate;

    @NotEmpty
    @Column(nullable = false, name = "end_date")
    private LocalDate endDate;

    @NotEmpty
    @Column(nullable = false, name = "return_date")
    private LocalDate returnDate;

    @NotEmpty
    @Positive
    @Column(nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "state_rent_id")
    private StateRent stateRent;


    public Rent(User user, Vehicle vehicle, LocalDate startDate, LocalDate endDate, LocalDate returnDate, double price, StateRent stateRent) {
        this.user = user;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.returnDate = returnDate;
        this.price = price;
        this.stateRent = stateRent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public StateRent getStateRent() {
        return stateRent;
    }

    public void setStateRent(StateRent stateRent) {
        this.stateRent = stateRent;
    }
}
