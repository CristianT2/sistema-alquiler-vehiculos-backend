package ar.edu.unju.fi.alquilervehiculos.controller;

import ar.edu.unju.fi.alquilervehiculos.dto.BrandDTO;
import ar.edu.unju.fi.alquilervehiculos.exceptions.CustomeException;
import ar.edu.unju.fi.alquilervehiculos.service.interfaces.IBrandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brand")
public class BrandController {

    private final IBrandService brandService;

    @Autowired
    public BrandController(IBrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BrandDTO> createBrand(@Valid @RequestBody BrandDTO brandDTO) {
        try{
            return ResponseEntity.ok(brandService.createBrand(brandDTO));
        } catch(Exception e){
            throw new CustomeException("Error al crear la marca");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandDTO> updateBrand(@PathVariable Integer id, @Valid @RequestBody BrandDTO brandDTO) {
        try{
            return ResponseEntity.ok(brandService.updateBrand(id, brandDTO));
        } catch(Exception e){
            throw new CustomeException("Error al actualizar la marca");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBrand(@PathVariable Integer id) {
        try{
            brandService.deleteBrand(id);
        } catch(Exception e){
            throw new CustomeException("Error al eliminar la marca");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<BrandDTO>> getAllBrands() {
        try{
            List<BrandDTO> brands = brandService.getAllBrands();
            return ResponseEntity.ok(brands);
        } catch(Exception e){
            throw new CustomeException("Error al obtener las marcas");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDTO> getBrandById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok(brandService.getBrandById(id));
        } catch(Exception e){
            throw new CustomeException("Error al obtener la marca");
        }
    }
}
