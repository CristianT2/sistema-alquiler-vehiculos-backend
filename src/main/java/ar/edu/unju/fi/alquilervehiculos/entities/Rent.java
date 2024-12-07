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
}
