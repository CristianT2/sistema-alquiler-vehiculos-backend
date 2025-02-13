package ar.edu.unju.fi.alquilervehiculos.controller;

import ar.edu.unju.fi.alquilervehiculos.dto.UserDTO;
import ar.edu.unju.fi.alquilervehiculos.exceptions.CustomeException;
import ar.edu.unju.fi.alquilervehiculos.service.interfaces.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        try{
            return ResponseEntity.ok(userService.createUser(userDTO));
        } catch(Exception e){
            throw new CustomeException("Error al crear el usuario");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @Valid @RequestBody UserDTO userDTO) {
        try{
            return ResponseEntity.ok(userService.updateUser(id, userDTO));
        } catch(Exception e){
            throw new CustomeException("Error al actualizar el usuario");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        try{
            userService.deleteUser(id);
        } catch(Exception e){
            throw new CustomeException("Error al eliminar el usuario");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        try{
            List<UserDTO> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch(Exception e){
            throw new CustomeException("Error al obtener los usuarios");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok(userService.getUserById(id));
        } catch(Exception e) {
            throw new CustomeException("Error al obtener el usuario");
        }
    }
}
