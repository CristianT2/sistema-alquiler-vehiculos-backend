package ar.edu.unju.fi.alquilervehiculos.controller;

import ar.edu.unju.fi.alquilervehiculos.dto.RentDTO;
import ar.edu.unju.fi.alquilervehiculos.exceptions.CustomeException;
import ar.edu.unju.fi.alquilervehiculos.service.interfaces.IRentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rent")
public class RentController {

    private final IRentService rentService;

    @Autowired
    public RentController(IRentService rentService) {
        this.rentService = rentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RentDTO> createRent(@RequestBody RentDTO rentDTO) {
        try{
            return ResponseEntity.ok(rentService.createRent(rentDTO));
        } catch(Exception e){
            throw new CustomeException("Error al crear el alquiler");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentDTO> updateRent(@PathVariable Integer id, @RequestBody RentDTO rentDTO) {
        try{
            return ResponseEntity.ok(rentService.updateRent(id, rentDTO));
        } catch(Exception e){
            throw new CustomeException("Error al actualizar el alquiler");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRent(@PathVariable Integer id) {
        try{
            rentService.deleteRent(id);
        } catch(Exception e){
            throw new CustomeException("Error al eliminar el alquiler");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<RentDTO>> getAllRents(){
        try{
            List<RentDTO> rents = rentService.getAllRents();
            return ResponseEntity.ok(rents);
        } catch(Exception e){
            throw new CustomeException("Error al obtener los alquileres");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentDTO> getRentById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok(rentService.getRentById(id));
        } catch(Exception e){
            throw new CustomeException("Error al obtener el alquiler");
        }
    }
}
