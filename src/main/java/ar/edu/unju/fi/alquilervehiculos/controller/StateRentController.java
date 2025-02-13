package ar.edu.unju.fi.alquilervehiculos.controller;

import ar.edu.unju.fi.alquilervehiculos.dto.StateRentDTO;
import ar.edu.unju.fi.alquilervehiculos.exceptions.CustomeException;
import ar.edu.unju.fi.alquilervehiculos.service.interfaces.IStateRentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stateRent")
public class StateRentController {

    private final IStateRentService stateRentService;

    @Autowired
    public StateRentController(IStateRentService stateRentService) {
        this.stateRentService = stateRentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<StateRentDTO> createStateRent(@RequestBody StateRentDTO stateRentDTO) {
        try{
            return ResponseEntity.ok(stateRentService.createStateRent(stateRentDTO));
        } catch(Exception e){
            throw new CustomeException("Error al crear el estado de alquiler");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<StateRentDTO> updateStateRent(@PathVariable Integer id, @RequestBody StateRentDTO stateRentDTO) {
        try{
            return ResponseEntity.ok(stateRentService.updateStateRent(id, stateRentDTO));
        } catch(Exception e){
            throw new CustomeException("Error al actualizar el estado de alquiler");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStateRent(@PathVariable Integer id) {
        try{
            stateRentService.deleteStateRent(id);
        } catch(Exception e){
            throw new CustomeException("Error al eliminar el estado de alquiler");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<StateRentDTO>> getAllStateRents(){
        try{
            List<StateRentDTO> states = stateRentService.getAllStateRents();
            return ResponseEntity.ok(states);
        } catch(Exception e){
            throw new CustomeException("Error al obtener los estados de alquiler");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<StateRentDTO> getStateRentById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok(stateRentService.getStateRentById(id));
        } catch (Exception e) {
            throw new CustomeException("Error al obtner el estado de alquiler");
        }
    }
}
