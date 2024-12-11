package ar.edu.unju.fi.alquilervehiculos.mappers;

import ar.edu.unju.fi.alquilervehiculos.dto.BrandDTO;
import ar.edu.unju.fi.alquilervehiculos.entities.Brand;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class BrandMapper {

    private ModelMapper modelMapper;

    public BrandMapper() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    /**
     * Convierte un objeto Brand o un objeto BrandDTO
     * @param brand
     * @return
     */
    public BrandDTO toDTO(Brand brand) {
        return modelMapper.map(brand, BrandDTO.class);
    }

    /**
     * Convierte un objeto BrandDTO o un objeto Brand
     * @param brandDTO
     * @return
     */
    public Brand toEntity(BrandDTO brandDTO) {
        return modelMapper.map(brandDTO, Brand.class);
    }
}
