package ar.edu.unju.fi.alquilervehiculos.service.interfaces;

import ar.edu.unju.fi.alquilervehiculos.dto.BrandDTO;

import java.util.List;

public interface IBrandService {

    BrandDTO createBrand(BrandDTO brandDTO);
    BrandDTO updateBrand(Integer id, BrandDTO brandDTO);
    void deleteBrand(Integer id);
    List<BrandDTO> getAllBrands();
    BrandDTO getBrandById(Integer id);
}
