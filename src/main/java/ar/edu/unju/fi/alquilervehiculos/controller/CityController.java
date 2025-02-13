package ar.edu.unju.fi.alquilervehiculos.controller;

import ar.edu.unju.fi.alquilervehiculos.dto.CityDTO;
import ar.edu.unju.fi.alquilervehiculos.exceptions.CustomeException;
import ar.edu.unju.fi.alquilervehiculos.service.interfaces.ICityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/city")
public class CityController {

    private final ICityService cityService;

    @Autowired
    public CityController(ICityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CityDTO> createCity(@Valid @RequestBody CityDTO cityDTO) {
        try{
            return ResponseEntity.ok(cityService.createCity(cityDTO));
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomeException("Error al crear la ciudad");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityDTO> updateCity(@PathVariable Integer id, @Valid @RequestBody CityDTO cityDTO) {
        try{
            return ResponseEntity.ok(cityService.updateCity(id, cityDTO));
        }catch (Exception e){
            throw new CustomeException("Error al actualizar la ciudad");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCity(@PathVariable Integer id) {
        try{
            cityService.deleteCity(id);
        }catch(Exception e){
            throw new CustomeException("Error al eliminar la ciudad");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<CityDTO>> getAllCities() {
        try{
            List<CityDTO> cities = cityService.getAllCities();
            return ResponseEntity.ok(cities);
        }catch(Exception e){
            throw new CustomeException("Error al obtener todas las ciudades");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> getCityById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok(cityService.getCityById(id));
        }catch(Exception e){
            throw new CustomeException("Error al obtener la ciudad");
        }
    }
}
