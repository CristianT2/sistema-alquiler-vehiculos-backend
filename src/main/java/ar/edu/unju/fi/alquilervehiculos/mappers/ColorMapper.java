package ar.edu.unju.fi.alquilervehiculos.mappers;

import ar.edu.unju.fi.alquilervehiculos.dto.ColorDTO;
import ar.edu.unju.fi.alquilervehiculos.entities.Color;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class ColorMapper {

    private ModelMapper modelMapper;

    public ColorMapper(){
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    /**
     * Convierte un objeto Color a un objeto ColorDTO
     * @param color
     * @return
     */
    public ColorDTO toDTO(Color color){
        return modelMapper.map(color, ColorDTO.class);
    }

    /**
     * Convierte un objeto ColorDTO a un objeto Color
     * @param colorDTO
     * @return
     */
    public Color toEntity(ColorDTO colorDTO){
        return modelMapper.map(colorDTO, Color.class);
    }
}
