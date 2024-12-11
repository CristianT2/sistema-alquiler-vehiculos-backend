package ar.edu.unju.fi.alquilervehiculos.mappers;

import ar.edu.unju.fi.alquilervehiculos.dto.UserDTO;
import ar.edu.unju.fi.alquilervehiculos.entities.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class UserMapper {

    private ModelMapper modelMapper;

    public UserMapper() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    /**
     * Convierte un objeto User a un objeto UserdTO
     * @param user
     * @return
     */
    public UserDTO toDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    /**
     * Convierte un objeto UserDTO a un objeto User
     * @param userDTO
     * @return
     */
    public User toEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
