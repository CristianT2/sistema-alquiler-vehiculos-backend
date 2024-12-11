package ar.edu.unju.fi.alquilervehiculos.service.interfaces;

import ar.edu.unju.fi.alquilervehiculos.dto.UserDTO;

import java.util.List;

public interface IUserService {

    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(Integer id, UserDTO userDTO);
    void deleteUser(Integer id);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Integer id);
}
