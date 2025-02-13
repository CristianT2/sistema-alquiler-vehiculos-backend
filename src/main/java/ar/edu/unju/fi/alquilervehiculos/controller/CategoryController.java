package ar.edu.unju.fi.alquilervehiculos.controller;

import ar.edu.unju.fi.alquilervehiculos.dto.CategoryDTO;
import ar.edu.unju.fi.alquilervehiculos.exceptions.CustomeException;
import ar.edu.unju.fi.alquilervehiculos.service.interfaces.ICategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        try{
            return ResponseEntity.ok(categoryService.createCategory(categoryDTO));
        } catch(Exception e){
            throw new CustomeException("Error al crear la categoria");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Integer id, @Valid @RequestBody CategoryDTO categoryDTO) {
        try{
            return ResponseEntity.ok(categoryService.updateCategory(id, categoryDTO));
        } catch(Exception e){
            throw new CustomeException("Error al actualizar la categoria");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Integer id) {
        try{
            categoryService.deleteCategory(id);
        } catch(Exception e){
            throw new CustomeException("Error al eliminar la categoria");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        try{
            List<CategoryDTO> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(categories);
        } catch(Exception e){
            throw new CustomeException("Error al obtener las catorias");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok(categoryService.getCategoryById(id));
        } catch(Exception e){
            throw new CustomeException("Error al obtener la categoria");
        }
    }
}
