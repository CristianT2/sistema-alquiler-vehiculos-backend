package ar.edu.unju.fi.alquilervehiculos.service.implementations;

import ar.edu.unju.fi.alquilervehiculos.dto.CategoryDTO;
import ar.edu.unju.fi.alquilervehiculos.entities.Category;
import ar.edu.unju.fi.alquilervehiculos.exceptions.CustomeException;
import ar.edu.unju.fi.alquilervehiculos.mappers.CategoryMapper;
import ar.edu.unju.fi.alquilervehiculos.repository.CategoryRepository;
import ar.edu.unju.fi.alquilervehiculos.service.interfaces.ICategoryService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//@Slf4j
@Service
public class CategoryServiceImp implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImp(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    /**
     * Crea una Categoria
     * @param categoryDTO
     * @return
     */
    @Transactional
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        //log.info("Create category: {}", categoryDTO);
        try{
            if (categoryRepository.existsByName(categoryDTO.getName())){
                throw new CustomeException("La categoria ya existe");
            }
            Category category = categoryMapper.toEntity(categoryDTO);
            //log.info("Categoria creada exitosamente: {}", category);

            return categoryMapper.toDTO(categoryRepository.save(category));
        }catch (Exception e){
            //log.error("Error al intentar crear la categoria",e.getMessage());
            throw new CustomeException("Error al intentar crear la categoria");
        }
    }

    /**
     * Actualiza una categoria seleccionada por su id
     * @param id
     * @param categoryDTO
     * @return
     */
    @Transactional
    @Override
    public CategoryDTO updateCategory(Integer id, CategoryDTO categoryDTO) {
        //log.info("Actualizando categoria con id: {}", id);
        try{
            categoryRepository.findById(id).orElseThrow(() -> new CustomeException("La categoria no existe"));
            Category category = categoryMapper.toEntity(categoryDTO);
            category.setId(id);

            //log.info("Categoria actualizada");
            return categoryMapper.toDTO(categoryRepository.save(category));
        }catch (Exception e){
            //log.error("Error al intentar actualizar la categoria",e.getMessage());
            throw new CustomeException("Error al intentar actualizar la categoria");
        }
    }

    /**
     * Elimina una categoria seleccionadapor su id
     * @param id
     */
    @Transactional
    @Override
    public void deleteCategory(Integer id) {
        //log.info("Eliminando categoria con id: {}", id);
        try{
            Category category = categoryRepository.findById(id).orElseThrow(() -> new CustomeException("La categoria no existe"));
            categoryRepository.delete(category);
            //log.info("Categoria eliminada");
        }catch (Exception e){
            //log.error("Error al intentar eliminar la categoria",e.getMessage());
            throw new CustomeException("Error al intentar eliminar la categoria");
        }
    }

    /**
     * Obtiene todas las categorias
     * @return
     */
    @Override
    public List<CategoryDTO> getAllCategories() {
        //log.info("Obteniendo todas las categorias");
        try{
            List<Category> categories = categoryRepository.findAll();
            return categories.stream()
                    .map(categoryMapper::toDTO)
                    .collect(Collectors.toList());
        }catch (Exception e){
            //log.error("Error al intentar obtener las categorias",e.getMessage());
            throw new CustomeException("Error al intentar obtener las categorias");
        }
    }

    /**
     * Obtiene la categoria seleccionada por su id
     * @param id
     * @return
     */
    @Override
    public CategoryDTO getCategoryById(Integer id) {
        //log.info("Buscando categoria por ID: {}", id);
        try{
            Category category = categoryRepository.findById(id).orElseThrow(() -> new CustomeException("La categoria no existe"));
            return categoryMapper.toDTO(category);
        }catch (Exception e){
            //log.error("Error al intentar obtener la categoria",e.getMessage());
            throw new CustomeException("Error al intentar obtener la categoria");
        }
    }
}
