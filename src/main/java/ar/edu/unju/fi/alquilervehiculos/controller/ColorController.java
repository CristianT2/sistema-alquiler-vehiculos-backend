package ar.edu.unju.fi.alquilervehiculos.controller;

import ar.edu.unju.fi.alquilervehiculos.dto.ColorDTO;
import ar.edu.unju.fi.alquilervehiculos.exceptions.CustomeException;
import ar.edu.unju.fi.alquilervehiculos.service.interfaces.IColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/color")
public class ColorController {

    private final IColorService colorService;

    @Autowired
    public ColorController(IColorService colorService) {
        this.colorService = colorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ColorDTO> createColor(@RequestBody ColorDTO colorDTO) {
        try{
            return ResponseEntity.ok(colorService.createColor(colorDTO));
        } catch(Exception e){
            throw new CustomeException("Error al crear el color");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColorDTO> updateColor(@PathVariable Integer id, @RequestBody ColorDTO colorDTO) {
        try{
            return ResponseEntity.ok(colorService.updateColor(id, colorDTO));
        } catch(Exception e){
            throw new CustomeException("Error al actualizar el color");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteColor(@PathVariable Integer id) {
        try{
            colorService.deleteColor(id);
        } catch(Exception e){
            throw new CustomeException("Error al eliminar el color");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ColorDTO>> getAllColors() {
        try{
             List<ColorDTO> colors = colorService.getAllColors();
             return ResponseEntity.ok(colors);
        } catch(Exception e){
            throw new CustomeException("Error al obtener los colores");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColorDTO> getColorById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok(colorService.getColor(id));
        } catch(Exception e){
            throw new CustomeException("Error al obtener el color");
        }
    }
}
