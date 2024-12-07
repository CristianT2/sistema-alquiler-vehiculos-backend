package ar.edu.unju.fi.alquilervehiculos.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private StateVehicle stateVehicle;

    @NotEmpty
    @Column(nullable = false)
    private int year;

    @NotEmpty
    @Column(nullable = false)
    @Positive
    private double mileage;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[A-Z0-9]{6,7}$")
    @Column(nullable = false, length = 6)
    private String tuition;

    @NotEmpty
    @Positive
    @Column(nullable = false)
    private double price;

    @Column(length = 100)
    private String description;

    @Lob
    @Column(name = "photo")
    private byte[] photo;


    public Vehicle(Category category, Brand brand, Model model, Color color, StateVehicle stateVehicle, int year, double mileage, String tuition, double price, String description, byte[] photo) {
        this.category = category;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.stateVehicle = stateVehicle;
        this.year = year;
        this.mileage = mileage;
        this.tuition = tuition;
        this.price = price;
        this.description = description;
        this.photo = photo;
    }
}
