package ar.edu.unju.fi.alquilervehiculos.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "models")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotEmpty
    @Length(min = 3, max = 20)
    @Column(nullable = false, length = 20)
    private String name;

    @Column(length = 100)
    private String description;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    public Model(String name, String description, Brand brand) {
        this.name = name;
        this.description = description;
        this.brand = brand;
    }
}
