package ar.edu.unju.fi.alquilervehiculos.mappers;

import ar.edu.unju.fi.alquilervehiculos.dto.ModelDTO;
import ar.edu.unju.fi.alquilervehiculos.entities.Model;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class ModeloMapper {

    private ModelMapper modelMapper;

    public ModeloMapper(){
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    /**
     * Convierte un objeto Model a un objeto ModelDTO
     * @param model
     * @return
     */
    public ModelDTO toDTO (Model model){
        return modelMapper.map(model, ModelDTO.class);
    }

    /**
     * Convierte un objeto Model a un objeto ModelDTO
     * @param modelDTO
     * @return
     */
    public Model toEntity(ModelDTO modelDTO){
        return modelMapper.map(modelDTO, Model.class);
    }
}
