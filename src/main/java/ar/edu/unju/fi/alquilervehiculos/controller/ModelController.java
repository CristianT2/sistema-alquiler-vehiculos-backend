package ar.edu.unju.fi.alquilervehiculos.controller;

import ar.edu.unju.fi.alquilervehiculos.dto.ModelDTO;
import ar.edu.unju.fi.alquilervehiculos.exceptions.CustomeException;
import ar.edu.unju.fi.alquilervehiculos.service.interfaces.IModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/model")
public class ModelController {

    private final IModelService modelService;

    @Autowired
    public ModelController(IModelService modelService) {
        this.modelService = modelService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ModelDTO> createModel(@RequestBody ModelDTO modelDTO) {
        try{
            return ResponseEntity.ok(modelService.createModel(modelDTO));
        } catch(Exception e){
            throw new CustomeException("Error al crear el modelo");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModelDTO> updateModel(@PathVariable Integer id, @RequestBody ModelDTO modelDTO) {
        try{
            return ResponseEntity.ok(modelService.updateModel(id, modelDTO));
        } catch(Exception e){
            throw new CustomeException("Error al actualizar el modelo");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteModel(@PathVariable Integer id) {
        try{
            modelService.deleteModel(id);
        } catch(Exception e){
            throw new CustomeException("Error al eliminar el modelo");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List< ModelDTO>> getAllModels() {
        try{
            List<ModelDTO> models = modelService.getAllModels();
            return ResponseEntity.ok(models);
        } catch(Exception e){
            throw new CustomeException("Error al obtener los modelos");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelDTO> getModelById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok(modelService.getModelById(id));
        } catch(Exception e){
            throw new CustomeException("Error al obtener el modelo");
        }
    }
}
