package ar.edu.unju.fi.alquilervehiculos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {

    private String username;
    private String password;
    private String name;
    private String lastname;
    private String dni;
    private String email;
    private String phoneNumber;
    private CityDTO city;
    private String address;
    private RoleDTO role;
    private byte[] photo;
}
