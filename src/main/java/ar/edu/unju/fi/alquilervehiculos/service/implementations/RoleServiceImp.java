package ar.edu.unju.fi.alquilervehiculos.service.implementations;

import ar.edu.unju.fi.alquilervehiculos.dto.RoleDTO;
import ar.edu.unju.fi.alquilervehiculos.entities.Role;
import ar.edu.unju.fi.alquilervehiculos.exceptions.CustomeException;
import ar.edu.unju.fi.alquilervehiculos.mappers.RoleMapper;
import ar.edu.unju.fi.alquilervehiculos.repository.RoleRepository;
import ar.edu.unju.fi.alquilervehiculos.service.interfaces.IRoleService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//@Slf4j
@Service
public class RoleServiceImp implements IRoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Autowired
    public RoleServiceImp(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    /**
     * Crea un rol
     * @param roleDTO 
     * @return
     */
    @Transactional
    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {
        //log.info("creando un rol: {}", roleDTO);
        try{
            if (roleRepository.existsByName(roleDTO.getName())){
                throw new CustomeException("El rol ya existe");
            }
            Role role = roleMapper.toEntity(roleDTO);
            //log.info("Rol creado exitosamente");
            return roleMapper.toDTO(roleRepository.save(role));
        } catch (Exception e) {
            //log.error("Error al crear el rol", e.getMessage());
            throw new CustomeException("Error al crear el rol");
        }
    }

    /**
     * Actualiza el rol seleccionado por su id
     * @param id 
     * @param roleDTO
     * @return
     */
    @Transactional
    @Override
    public RoleDTO updateRole(Integer id, RoleDTO roleDTO) {
        //log.info("Actualizando ciudad con id: {}", id);
        try{
            roleRepository.findById(id).orElseThrow(()-> new CustomeException("el rol no existe"));
            Role role = roleMapper.toEntity(roleDTO);
            role.setId(id);
            //log.info("Rol actualizado");

            return roleMapper.toDTO(roleRepository.save(role));
        }catch (Exception e) {
            //log.error("Error al actualizar el rol", e.getMessage());
            throw new CustomeException("Error al actualizar el rol");
        }
    }

    /**
     * Elimina el rol seleccionado por su id
     * @param id 
     */
    @Transactional
    @Override
    public void deleteRole(Integer id) {
        //log.info("Eliminando rol con id: {}", id);
        try{
            Role role = roleRepository.findById(id).orElseThrow(() -> new CustomeException("El rol no existe"));
            roleRepository.delete(role);
            //log.info("Rol eliminado");
        }catch (Exception e) {
            //log.error("Error al eliminar el rol", e.getMessage());
            throw new CustomeException("Error al eliminar el rol");
        }
    }

    /**
     * Obtiene todos los roles
     * @return 
     */
    @Override
    public List<RoleDTO> getAllRoles() {
        //log.info("Obteniendo todas los roles");
        try {
            List<Role> roles = roleRepository.findAll();
            return roles.stream()
                    .map(roleMapper::toDTO)
                    .collect(Collectors.toList());
        }catch (Exception e) {
            //log.error("Error al obtener los roles", e.getMessage());
            throw new CustomeException("Error al obtener los roles");
        }
    }

    /**
     * Obtiene un rol seleccionado por su id
     * @param id 
     * @return
     */
    @Override
    public RoleDTO getRoleById(Integer id) {
        //log.info("Obteniendo rol con id: {}", id);
        try{
            Role role = roleRepository.findById(id).orElseThrow(() -> new CustomeException("El rol no existe"));
            return roleMapper.toDTO(role);
        }catch (Exception e) {
            //log.error("Error al obtener el rol", e.getMessage());
            throw new CustomeException("Error al obtener el rol");
        }
    }
}
