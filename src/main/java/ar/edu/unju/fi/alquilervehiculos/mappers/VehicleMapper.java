package ar.edu.unju.fi.alquilervehiculos.mappers;

import ar.edu.unju.fi.alquilervehiculos.dto.VehicleDTO;
import ar.edu.unju.fi.alquilervehiculos.entities.Vehicle;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class VehicleMapper {

    private ModelMapper modelMapper;

    public VehicleMapper(){
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    /**
     * Convierte un objeto Vehicle a un objeto VehicleDTO
     * @param vehicle
     * @return
     */
    public VehicleDTO toDTO(Vehicle vehicle){
        return modelMapper.map(vehicle, VehicleDTO.class);
    }

    /**
     * Convierte un objeto VehicleDTO a un objeto Vehicle
     * @param vehicleDTO
     * @return
     */
    public Vehicle toEntity(VehicleDTO vehicleDTO){
        return modelMapper.map(vehicleDTO, Vehicle.class);
    }
}
