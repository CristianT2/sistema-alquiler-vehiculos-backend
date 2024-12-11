package ar.edu.unju.fi.alquilervehiculos.service.interfaces;

import ar.edu.unju.fi.alquilervehiculos.dto.CategoryDTO;

import java.util.List;

public interface ICategoryService {

    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(Integer id, CategoryDTO categoryDTO);
    void deleteCategory(Integer id);
    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryById(Integer id);
}
