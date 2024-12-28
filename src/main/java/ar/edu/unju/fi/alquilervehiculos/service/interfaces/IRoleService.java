package ar.edu.unju.fi.alquilervehiculos.service.interfaces;

import ar.edu.unju.fi.alquilervehiculos.dto.RoleDTO;

import java.util.List;

public interface IRoleService {

    RoleDTO createRole(RoleDTO roleDTO);
    RoleDTO updateRole(Integer id, RoleDTO roleDTO);
    void deleteRole(Integer id);
    List<RoleDTO> getAllRoles();
    RoleDTO getRoleById(Integer id);
}
