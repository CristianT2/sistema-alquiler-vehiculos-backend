package ar.edu.unju.fi.alquilervehiculos.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

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

    @NotNull
    @NotEmpty
    @Length(min = 3, max = 20)
    @Column(nullable = false, length = 20)
    private String name;

    @NotNull
    @NotEmpty
    @Length(min = 3, max = 20)
    @Column(nullable = false, length = 20, name = "postal_code")
    private String postalCode;

    @OneToMany(mappedBy = "city")
    private List<User> users;

    public City(String name, String postalCode) {
        this.name = name;
        this.postalCode = postalCode;
    }
}
