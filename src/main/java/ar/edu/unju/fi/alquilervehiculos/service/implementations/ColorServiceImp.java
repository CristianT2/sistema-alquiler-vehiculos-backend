package ar.edu.unju.fi.alquilervehiculos.service.implementations;

import ar.edu.unju.fi.alquilervehiculos.dto.ColorDTO;
import ar.edu.unju.fi.alquilervehiculos.entities.Color;
import ar.edu.unju.fi.alquilervehiculos.exceptions.CustomeException;
import ar.edu.unju.fi.alquilervehiculos.mappers.ColorMapper;
import ar.edu.unju.fi.alquilervehiculos.repository.ColorRepository;
import ar.edu.unju.fi.alquilervehiculos.service.interfaces.IColorService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//@Slf4j
@Service
public class ColorServiceImp implements IColorService {

    private final ColorRepository colorRepository;
    private final ColorMapper colorMapper;

    @Autowired
    public ColorServiceImp(ColorRepository colorRepository, ColorMapper colorMapper) {
        this.colorRepository = colorRepository;
        this.colorMapper = colorMapper;
    }

    /**
     * Crea un color
     * @param colorDTO
     * @return
     */
    @Transactional
    @Override
    public ColorDTO createColor(ColorDTO colorDTO) {
        //log.info("Creando un color");
        try{
            if (colorRepository.existsByName(colorDTO.getName())){
                throw new CustomeException("El color ya existe");
            }
            Color color = colorMapper.toEntity(colorDTO);
            //log.info("Color creado");
            return colorMapper.toDTO(colorRepository.save(color));
        } catch (Exception e) {
            //log.error("Error al intentar crear el color");
            throw new CustomeException("Error al intentar crear el color");
        }
    }

    /**
     * Actualiza un color
     * @param id
     * @param colorDTO
     * @return
     */
    @Transactional
    @Override
    public ColorDTO updateColor(Integer id, ColorDTO colorDTO) {
        //log.info("Actualizando el color con id: {}", id);
        try{
            colorRepository.findById(id).orElseThrow(() -> new CustomeException("El color no existe"));
            Color color = colorMapper.toEntity(colorDTO);
            color.setId(id);

            //log.info("Color modificado");
            return colorMapper.toDTO(colorRepository.save(color));
        }catch (Exception e) {
            //log.error("Error al intentar actualizar el color");
            throw new CustomeException("Error al intentar actualizar el color");
        }
    }

    /**
     * Elimina un color seleccionado por su id
     * @param id
     */
    @Transactional
    @Override
    public void deleteColor(Integer id) {
        //log.info("Eliminando el color con id: {}", id);
        try{
            Color color = colorRepository.findById(id).orElseThrow(() -> new CustomeException("El color no existe"));
            colorRepository.delete(color);
            //log.info("Color eliminado");
        }catch (Exception e) {
            //log.error("Error al intentar eliminar el color");
            throw new CustomeException("Error al intentar eliminar el color");
        }
    }

    /**
     * Obtiene todos los colores
     * @return
     */
    @Override
    public List<ColorDTO> getAllColors() {
        //log.info("Obteniendo todos los colores");
        try{
            List<Color> colors = colorRepository.findAll();
            return colors.stream()
                    .map(colorMapper::toDTO)
                    .collect(Collectors.toList());
        }catch (Exception e) {
            //log.error("Error al intentar obtener los colores");
            throw new CustomeException("Error al intentar obtener los colores");
        }
    }

    /**
     * Obtiene un color seleccionado por su id
     * @param id
     * @return
     */
    @Override
    public ColorDTO getColor(Integer id) {
        //log.info("Obteniendo el color con id: {}", id);
        try{
            Color color = colorRepository.findById(id).orElseThrow(() -> new CustomeException("El color no existe"));
            return colorMapper.toDTO(color);
        }catch (Exception e) {
            //log.error("Error al intentar obtener el color");
            throw new CustomeException("Error al intentar obtener el color");
        }
    }
}
