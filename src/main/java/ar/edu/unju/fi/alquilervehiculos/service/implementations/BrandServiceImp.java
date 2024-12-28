package ar.edu.unju.fi.alquilervehiculos.service.implementations;

import ar.edu.unju.fi.alquilervehiculos.dto.BrandDTO;
import ar.edu.unju.fi.alquilervehiculos.entities.Brand;
import ar.edu.unju.fi.alquilervehiculos.entities.Category;
import ar.edu.unju.fi.alquilervehiculos.exceptions.CustomeException;
import ar.edu.unju.fi.alquilervehiculos.mappers.BrandMapper;
import ar.edu.unju.fi.alquilervehiculos.repository.BrandRepository;
import ar.edu.unju.fi.alquilervehiculos.repository.CategoryRepository;
import ar.edu.unju.fi.alquilervehiculos.service.interfaces.IBrandService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//@Slf4j
@Service
public class BrandServiceImp implements IBrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;
    private final CategoryRepository categoryRepository;

    @Autowired
    public BrandServiceImp(BrandRepository brandRepository, BrandMapper brandMapper, CategoryRepository categoryRepository) {
        this.brandRepository = brandRepository;
        this.brandMapper = brandMapper;
        this.categoryRepository = categoryRepository;
    }

    /**
     * Crea una marca
     * @param brandDTO
     * @return
     */
    @Transactional
    @Override
    public BrandDTO createBrand(BrandDTO brandDTO) {
        //log.info("Creando una marca");
        try{
            if (brandRepository.existsByName(brandDTO.getName())){
                throw new CustomeException("La marca ya existe");
            }
            Category category = categoryRepository.findById(brandDTO.getCategory().getId()).orElseThrow(() -> new CustomeException("No existe la categoria"));
            Brand brand = brandMapper.toEntity(brandDTO);
            brand.setCategory(category);

            //log.info("Marca creada correctamente");
            return brandMapper.toDTO(brandRepository.save(brand));
        }catch (Exception e){
            //log.error("Error al intentar crear una marca", e.getMessage());
            throw new CustomeException("Error al intentar crear una marca");
        }
    }

    /**
     * Actualiza una marca seleccionada por su id
     * @param id
     * @param brandDTO
     * @return
     */
    @Transactional
    @Override
    public BrandDTO updateBrand(Integer id, BrandDTO brandDTO) {
        //log.info("Actualizando marca con id: {}", id);
        try{
            Brand brand = brandRepository.findById(id).orElseThrow(() -> new CustomeException("No existe la marca"));
            Category category = categoryRepository.findById(brandDTO.getCategory().getId()).orElseThrow(() -> new CustomeException("No existe la categoria"));

            brandMapper.toEntity(brandDTO);
            brand.setCategory(category);
            brand.setId(id);

            //log.info("Marca actualizada correctamente");
            return brandMapper.toDTO(brandRepository.save(brand));
        }catch (Exception e){
            //log.error("Error al intentar actualizar la marca", e.getMessage());
            throw new CustomeException("Error al intentar actualizar la marca");
        }
    }

    /**
     * Elimina una marca seleccionada por su id
     * @param id
     */
    @Transactional
    @Override
    public void deleteBrand(Integer id) {
        //log.info("Eliminando una marca por ID: {}", id);
        try{
            Brand brand = brandRepository.findById(id).orElseThrow(() -> new CustomeException("La marca no existe"));
            brandRepository.delete(brand);
            //log.info("Marca eliminada");
        }catch (Exception e){
            //log.error("Error al intentar eliminar la marca", e.getMessage());
            throw new CustomeException("Error al intentar eliminar la marca");
        }
    }

    /**
     * Obtiene todas las marcas
     * @return
     */
    @Override
    public List<BrandDTO> getAllBrands() {
        //log.info("Obteniendo todas las marcas");
        try{
            List<Brand> brands = brandRepository.findAll();
            return brands.stream()
                    .map(brandMapper::toDTO)
                    .collect(Collectors.toList());
        }catch (Exception e){
            //log.error("Error al intentar obtener las marcas", e.getMessage());
            throw new CustomeException("Error al intentar obtener las marcas");
        }
    }

    /**
     * Obtiene una marca seleccionada por su id
     * @param id
     * @return
     */
    @Override
    public BrandDTO getBrandById(Integer id) {
        //log.info("Obteniendo marca con id: {}", id);
        try{
            Brand brand = brandRepository.findById(id).orElseThrow(() -> new CustomeException("La marca no existe"));
            return brandMapper.toDTO(brand);
        }catch (Exception e){
            //log.error("Error al intentar obtener la marca", e.getMessage());
            throw new CustomeException("Error al intentar obtener la marca");
        }
    }
}

