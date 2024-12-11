package ar.edu.unju.fi.alquilervehiculos.service.interfaces;

import ar.edu.unju.fi.alquilervehiculos.dto.RoleDTO;

import java.util.List;

public interface IRoleSerice {

    RoleDTO createRole(RoleDTO role);
    RoleDTO updateRole(Integer id, RoleDTO role);
    void deleteRole(Integer id);
    List<RoleDTO> getAllRoles();
    RoleDTO getRoleById(Integer id);
}
