package ar.edu.unju.fi.alquilervehiculos.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private int year;
    private double mileage;
    private String tuition;
    private double price;
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
