package ar.edu.unju.fi.alquilervehiculos.controller;

import ar.edu.unju.fi.alquilervehiculos.dto.StateVehicleDTO;
import ar.edu.unju.fi.alquilervehiculos.exceptions.CustomeException;
import ar.edu.unju.fi.alquilervehiculos.service.interfaces.IStateVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stateVehicle")
public class StateVehicleController {

    private final IStateVehicleService stateVehicleService;

    @Autowired
    public StateVehicleController(IStateVehicleService stateVehicleService) {
        this.stateVehicleService = stateVehicleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<StateVehicleDTO> createStateVehicle(@RequestBody StateVehicleDTO stateVehicleDTO){
        try{
            return ResponseEntity.ok(stateVehicleService.createStateVehicle(stateVehicleDTO));
        } catch(Exception e){
            throw new CustomeException("Error al crear el estado de vehiculo");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<StateVehicleDTO> updateStateVehicle(@PathVariable Integer id, @RequestBody StateVehicleDTO stateVehicleDTO){
        try{
            return ResponseEntity.ok(stateVehicleService.updateStateVehicle(id, stateVehicleDTO));
        } catch(Exception e){
            throw new CustomeException("Error al actualizar el estado de vehiculo");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStateVehicle(@PathVariable Integer id){
        try{
            stateVehicleService.deleteStateVehicle(id);
        } catch(Exception e){
            throw new CustomeException("Error al eliminar el estado de vehiculo");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<StateVehicleDTO>> getAllStateVehicles(){
        try{
            List<StateVehicleDTO> states = stateVehicleService.getAllStateVehicles();
            return ResponseEntity.ok(states);
        } catch(Exception e){
            throw new CustomeException("Error al obtener los estados del vehiculo");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<StateVehicleDTO> getStateVehicleById(@PathVariable Integer id){
        try{
            return ResponseEntity.ok(stateVehicleService.getStateVehicleById(id));
        } catch(Exception e){
            throw new CustomeException("Error al obtener el estado de vehiculo");
        }
    }
}
