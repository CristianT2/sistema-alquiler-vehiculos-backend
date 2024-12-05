package ar.edu.unju.fi.alquilervehiculos.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name = "postal_code")
    private String postalCode;

    @OneToMany(mappedBy = "city")
    private List<User> users;

    public City(String name, String postalCode) {
        this.name = name;
        this.postalCode = postalCode;
    }
}
