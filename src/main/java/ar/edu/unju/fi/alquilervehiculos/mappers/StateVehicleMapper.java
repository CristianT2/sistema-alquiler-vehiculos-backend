package ar.edu.unju.fi.alquilervehiculos.mappers;

import ar.edu.unju.fi.alquilervehiculos.dto.StateVehicleDTO;
import ar.edu.unju.fi.alquilervehiculos.entities.StateVehicle;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class StateVehicleMapper {

    private ModelMapper modelMapper;

    public StateVehicleMapper(){
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    /**
     * Convierte un objeto StateVehicle a un objeto StateVehicleDTO
     * @param stateVehicle
     * @return
     */
    public StateVehicleDTO toDTO(StateVehicle stateVehicle){
        return modelMapper.map(stateVehicle, StateVehicleDTO.class);
    }

    /**
     * Convierte un objeto StateVehicleDTO a un objeto StateVehicle
     * @param stateVehicleDTO
     * @return
     */
    public StateVehicle toEntity(StateVehicleDTO stateVehicleDTO){
        return modelMapper.map(stateVehicleDTO, StateVehicle.class);
    }
}
