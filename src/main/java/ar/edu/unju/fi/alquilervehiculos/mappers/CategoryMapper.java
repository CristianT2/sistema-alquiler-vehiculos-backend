package ar.edu.unju.fi.alquilervehiculos.mappers;

import ar.edu.unju.fi.alquilervehiculos.dto.CategoryDTO;
import ar.edu.unju.fi.alquilervehiculos.entities.Category;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    private ModelMapper modelMapper;

    public CategoryMapper(){
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    /**
     * Convierte un objeto Category a un objeto CategoryDTO
     * @param category
     * @return
     */
    public CategoryDTO toDTO(Category category){
        return modelMapper.map(category, CategoryDTO.class);
    }

    /**
     * Convierte un objeto CategoryDTO a un objeto Category
     * @param dto
     * @return
     */
    public Category toEntity(CategoryDTO dto){
        return modelMapper.map(dto, Category.class);
    }
}
