package ar.edu.unju.fi.alquilervehiculos.mappers;

import ar.edu.unju.fi.alquilervehiculos.dto.CityDTO;
import ar.edu.unju.fi.alquilervehiculos.entities.City;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class CityMapper {

    private ModelMapper modelMapper;

    public CityMapper() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    /**
     * Convierte un objeto City en un objeto CityDTO.
     * @param city
     * @return
     */
    public CityDTO toDTO(City city){
        return modelMapper.map(city, CityDTO.class);
    }

    /**
     * Convierte un objeto CityDTO a un objeto City
     * @param cityDTO
     * @return
     */
    public City toEntity(CityDTO cityDTO){
        return modelMapper.map(cityDTO, City.class);
    }
}
