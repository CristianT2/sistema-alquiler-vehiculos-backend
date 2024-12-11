package ar.edu.unju.fi.alquilervehiculos.mappers;

import ar.edu.unju.fi.alquilervehiculos.dto.RentDTO;
import ar.edu.unju.fi.alquilervehiculos.entities.Rent;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class RentMapper {

    private ModelMapper modelMapper;

    public RentMapper(){
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    /**
     * Convierte un objeto Rent a un objeto RentDTO
     * @param rent
     * @return
     */
    public RentDTO toDTO(Rent rent){
        return modelMapper.map(rent, RentDTO.class);
    }

    /**
     * Convierte un objeto RentDTO a un objeto Rent
     * @param rentDTO
     * @return
     */
    public Rent toEntity(RentDTO rentDTO){
        return modelMapper.map(rentDTO, Rent.class);
    }
}
