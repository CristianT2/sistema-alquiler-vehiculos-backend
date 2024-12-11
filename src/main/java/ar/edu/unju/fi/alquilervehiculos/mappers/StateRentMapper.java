package ar.edu.unju.fi.alquilervehiculos.mappers;

import ar.edu.unju.fi.alquilervehiculos.dto.StateRentDTO;
import ar.edu.unju.fi.alquilervehiculos.entities.StateRent;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class StateRentMapper {

    private ModelMapper modelMapper;

    public StateRentMapper() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    /**
     * Convierte un objeto StateRent a un objeto StateRentDTO
     * @param stateRent
     * @return
     */
    public StateRentDTO toDTO(StateRent stateRent) {
        return modelMapper.map(stateRent, StateRentDTO.class);
    }

    /**
     * Convierte un objeto StateRentDTO a un objeto StateRent
     * @param stateRentDTO
     * @return
     */
    public StateRent toEntity(StateRentDTO stateRentDTO) {
        return modelMapper.map(stateRentDTO, StateRent.class);
    }
}
