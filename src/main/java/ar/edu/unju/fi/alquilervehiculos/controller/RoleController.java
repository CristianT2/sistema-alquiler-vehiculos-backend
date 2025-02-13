package ar.edu.unju.fi.alquilervehiculos.controller;

import ar.edu.unju.fi.alquilervehiculos.dto.RoleDTO;
import ar.edu.unju.fi.alquilervehiculos.exceptions.CustomeException;
import ar.edu.unju.fi.alquilervehiculos.service.interfaces.IRoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {

    private final IRoleService roleService;

    @Autowired
    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RoleDTO> createRole(@Valid @RequestBody RoleDTO roleDTO) {
        try{
            return ResponseEntity.ok(roleService.createRole(roleDTO));
        }catch (Exception e){
            throw new CustomeException("Error al crear el rol");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable Integer id, @Valid @RequestBody RoleDTO roleDTO) {
        try{
            return ResponseEntity.ok(roleService.updateRole(id, roleDTO));
        }catch (Exception e){
            throw new CustomeException("Error al actualizar el rol");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRole(@PathVariable Integer id) {
        try{
            roleService.deleteRole(id);
        }catch (Exception e){
            throw new CustomeException("Error al eliminar el rol");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        try{
            List<RoleDTO> roles = roleService.getAllRoles();
            return ResponseEntity.ok(roles);
        }catch(Exception e){
            throw new CustomeException("Error al obtener los roles");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok(roleService.getRoleById(id));
        }catch(Exception e){
            throw new CustomeException("Error al obtener el rol");
        }
    }
}
