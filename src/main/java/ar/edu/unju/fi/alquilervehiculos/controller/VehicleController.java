package ar.edu.unju.fi.alquilervehiculos.controller;

import ar.edu.unju.fi.alquilervehiculos.dto.VehicleDTO;
import ar.edu.unju.fi.alquilervehiculos.exceptions.CustomeException;
import ar.edu.unju.fi.alquilervehiculos.service.interfaces.IVehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleController {

    private final IVehicleService vehicleService;

    @Autowired
    public VehicleController(IVehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<VehicleDTO> createVehicle(@Valid @RequestBody VehicleDTO vehicleDTO) {
        try{
            return ResponseEntity.ok(vehicleService.createVehicle(vehicleDTO));
        } catch(Exception e){
            e.printStackTrace();
            throw new CustomeException("Error al crear el vehiculo");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable Integer id, @Valid @RequestBody VehicleDTO vehicleDTO) {
        try{
            return ResponseEntity.ok(vehicleService.updateVehicle(id, vehicleDTO));
        } catch(Exception e){
            throw new CustomeException("Error al actualizar el vehiculo");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVehicle(@PathVariable Integer id) {
        try{
            vehicleService.deleteVehicle(id);
        } catch(Exception e){
            throw new CustomeException("Error al eliminar el vehiculo");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<VehicleDTO>> getAllVehicles() {
        try{
            List<VehicleDTO> vehicles = vehicleService.getAllVehicles();
            return ResponseEntity.ok(vehicles);
        } catch(Exception e){
            throw new CustomeException("Error al obtener los vehiculos");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok(vehicleService.getVehicleById(id));
        } catch(Exception e) {
            throw new CustomeException("Error al obtener el vehiculo");
        }
    }
}
