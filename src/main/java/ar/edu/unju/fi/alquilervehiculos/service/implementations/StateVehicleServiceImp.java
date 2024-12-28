package ar.edu.unju.fi.alquilervehiculos.service.implementations;

import ar.edu.unju.fi.alquilervehiculos.dto.StateVehicleDTO;
import ar.edu.unju.fi.alquilervehiculos.entities.StateVehicle;
import ar.edu.unju.fi.alquilervehiculos.exceptions.CustomeException;
import ar.edu.unju.fi.alquilervehiculos.mappers.StateVehicleMapper;
import ar.edu.unju.fi.alquilervehiculos.repository.StateVehicleRepository;
import ar.edu.unju.fi.alquilervehiculos.service.interfaces.IStateVehicleService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//@Slf4j
@Service
public class StateVehicleServiceImp implements IStateVehicleService {

    private final StateVehicleRepository stateVehicleRepository;
    private final StateVehicleMapper stateVehicleMapper;

    @Autowired
    public StateVehicleServiceImp(StateVehicleRepository stateVehicleRepository, StateVehicleMapper stateVehicleMapper) {
        this.stateVehicleRepository = stateVehicleRepository;
        this.stateVehicleMapper = stateVehicleMapper;
    }

    /**
     * Crea un estado de vehiculo
     * @param stateVehicleDTO
     * @return
     */
    @Transactional
    @Override
    public StateVehicleDTO createStateVehicle(StateVehicleDTO stateVehicleDTO) {
        //log.info("Creando un esatdo de vehiculo");
        try{
            if (stateVehicleRepository.existsByName(stateVehicleDTO.getName())){
                throw new CustomeException("El vehiculo ya existe");
            }
            StateVehicle stateVehicle = stateVehicleMapper.toEntity(stateVehicleDTO);

            //log.info("Estado creado correctamente");
            return stateVehicleMapper.toDTO(stateVehicleRepository.save(stateVehicle));
        }catch (Exception e){
            //log.error("Error al crear el estado de vehiculo", e.getMessage());
            throw new CustomeException("Error al crear el estado de vehiculo");
        }
    }

    /**
     * Actualiza el estado de vehiculo seleccionado por id
     * @param id
     * @param stateVehicleDTO
     * @return
     */
    @Transactional
    @Override
    public StateVehicleDTO updateStateVehicle(Integer id, StateVehicleDTO stateVehicleDTO) {
        //log.info("Actualizando estado de vehiculo con id: {}", id);
        try{
            stateVehicleRepository.findById(id).orElseThrow(() -> new CustomeException("El esatdo de vehiculo no existe"));
            StateVehicle stateVehicle = stateVehicleMapper.toEntity(stateVehicleDTO);
            stateVehicle.setId(id);

            //log.info("Estado de vehiculo actualizado");
            return stateVehicleMapper.toDTO(stateVehicleRepository.save(stateVehicle));
        }catch (Exception e){
            //log.error("Error al actualizar el estado de vehiculo", e.getMessage());
            throw new CustomeException("Error al actualizar el estado de vehiculo");
        }
    }

    /**
     * Elimina el estado de vehiculo seleccionado por su id
     * @param id
     */
    @Transactional
    @Override
    public void deleteStateVehicle(Integer id) {
        //log.info("Eliminando estado de vehiculo con id: {}", id);
        try{
            StateVehicle stateVehicle = stateVehicleRepository.findById(id).orElseThrow(() -> new CustomeException("El estado de vehiculo no existe"));
            stateVehicleRepository.delete(stateVehicle);
            //log.info("Estado de vehiculo eliminado");
        }catch (Exception e){
            //log.error("Error al eliminar el estado de vehiculo", e.getMessage());
            throw new CustomeException("Error al eliminar el estado de vehiculo");
        }
    }

    /**
     * Obtiene todos los estados de vehiculo
     * @return
     */
    @Override
    public List<StateVehicleDTO> getAllStateVehicles() {
        //log.info("Obteniendo todos los estados de vehiculo");
        try{
            List<StateVehicle> states = stateVehicleRepository.findAll();
            return states.stream()
                    .map(stateVehicleMapper::toDTO)
                    .collect(Collectors.toList());
        }catch (Exception e){
            //log.error("Error al obtener los estados de vehiculo", e.getMessage());
            throw new CustomeException("Error al obtener los estados de vehiculo");
        }
    }

    /**
     * Obtiene el estado de vehiculo seleccionado por su id
     * @param id
     * @return
     */
    @Override
    public StateVehicleDTO getStateVehicleById(Integer id) {
        //log.info("Obteniendo estado de vehiculo con id: {}", id);
        try{
            StateVehicle stateVehicle = stateVehicleRepository.findById(id).orElseThrow(() -> new CustomeException("El estado de vehiculo no existe"));
            return stateVehicleMapper.toDTO(stateVehicle);
        }catch (Exception e){
            //log.error("Error al obtener el estado de vehiculo", e.getMessage());
            throw new CustomeException("Error al obtener el estado de vehiculo");
        }
    }
}
