package ar.edu.unju.fi.alquilervehiculos.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotEmpty
    @Length(min = 3, max = 20)
    @Column(nullable = false, length = 20)
    private String username;

    @NotNull
    @NotEmpty
    @Length(min = 3, max = 20)
    @Column(nullable = false, length = 20)
    private String password;

    @NotNull
    @NotEmpty
    @Length(min = 3, max = 50)
    @Column(nullable = false, length = 50)
    private String name;

    @NotNull
    @NotEmpty
    @Length(min = 3, max = 50)
    @Column(nullable = false, length = 50)
    private String lastname;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^\\d{7,8}$", message = "El DNI debe tener 7 u 8 dígitos")
    @Column(nullable = false, length = 8, unique = true)
    private String dni;

    @NotNull
    @NotEmpty
    @Email
    @Column(nullable = false, length = 50)
    private String email;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^\\d{10}$", message = "El número de celular debe tener 10 dígitos")
    @Column(nullable = false, length = 10, name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @NotNull
    @NotEmpty
    private String address;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Lob
    @Column(name = "photo")
    private byte[] photo;
}
