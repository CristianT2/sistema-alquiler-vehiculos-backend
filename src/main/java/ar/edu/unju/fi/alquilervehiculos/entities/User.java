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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
