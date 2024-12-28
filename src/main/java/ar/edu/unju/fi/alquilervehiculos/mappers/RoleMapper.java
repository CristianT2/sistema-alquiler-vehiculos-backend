package ar.edu.unju.fi.alquilervehiculos.mappers;

import ar.edu.unju.fi.alquilervehiculos.dto.RoleDTO;
import ar.edu.unju.fi.alquilervehiculos.entities.Role;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    private ModelMapper modelMapper;

    public RoleMapper() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    /**
     * Convierte un objeto Role en un objeto RoleDTO.
     * @param role
     * @return
     */
    public RoleDTO toDTO(Role role){
        return modelMapper.map(role, RoleDTO.class);
    }

    /**
     * Convierte un objeto RoleDTO a un objeto Role
     * @param roleDTO
     * @return
     */
    public Role toEntity(RoleDTO roleDTO){
        return modelMapper.map(roleDTO, Role.class);
    }
}
