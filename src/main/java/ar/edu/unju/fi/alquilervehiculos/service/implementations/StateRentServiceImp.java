package ar.edu.unju.fi.alquilervehiculos.service.implementations;



import ar.edu.unju.fi.alquilervehiculos.dto.StateRentDTO;
import ar.edu.unju.fi.alquilervehiculos.entities.StateRent;
import ar.edu.unju.fi.alquilervehiculos.exceptions.CustomeException;
import ar.edu.unju.fi.alquilervehiculos.mappers.StateRentMapper;
import ar.edu.unju.fi.alquilervehiculos.repository.StateRentRepository;
import ar.edu.unju.fi.alquilervehiculos.service.interfaces.IStateRentService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//@Slf4j
@Service
public class StateRentServiceImp implements IStateRentService {

    private final StateRentRepository stateRentRepository;
    private final StateRentMapper stateRentMapper;

    @Autowired
    public StateRentServiceImp(StateRentRepository stateRentRepository, StateRentMapper stateRentMapper) {
        this.stateRentRepository = stateRentRepository;
        this.stateRentMapper = stateRentMapper;
    }

    /**
     * Crea un estado de alquiler
     * @param stateRentDTO 
     * @return
     */
    @Transactional
    @Override
    public StateRentDTO createStateRent(StateRentDTO stateRentDTO) {
        //log.info("Creando estado de alquiler");
        try{
            if(stateRentRepository.existsByName(stateRentDTO.getName())){
                throw new CustomeException("El estado de alquiler ya existe");
            }
            StateRent stateRent = stateRentMapper.toEntity(stateRentDTO);
            //log.info("Estado de alquiler creado correctamente");
            return stateRentMapper.toDTO(stateRentRepository.save(stateRent));
        }catch (Exception e){
            //log.info("Error al crear el estado de alquiler");
            throw new CustomeException("Error al crear el estado de alquiler");
        }
    }

    /**
     * Actualiza un estado de alquiler
     * @param id 
     * @param stateRentDTO
     * @return
     */
    @Transactional
    @Override
    public StateRentDTO updateStateRent(Integer id, StateRentDTO stateRentDTO) {
        //log.info("Actualizando estado de alquiler con id: {}", id);
        try{
            stateRentRepository.findById(id).orElseThrow(() -> new CustomeException("El estado de alquiler no existe"));
            StateRent stateRent = stateRentMapper.toEntity(stateRentDTO);
            stateRent.setId(id);

            //log.info("Estado de alquiler actualizado");
            return stateRentMapper.toDTO(stateRentRepository.save(stateRent));
        }catch(Exception e){
            //log.error("Error al actualizar el estado de alquiler", e.getMessage());
            throw new CustomeException("Error al actualizar el estado de alquiler");
        }
    }

    /**
     * Elimina un estado de alquiler seleccionado por su id
     * @param id 
     */
    @Transactional
    @Override
    public void deleteStateRent(Integer id) {
        //log.info("Eliminando estado de alquiler con id: {}", id);
        try{
            StateRent stateRent = stateRentRepository.findById(id).orElseThrow(() -> new CustomeException("El estado de alquiler no existe"));
            stateRentRepository.delete(stateRent);
            //log.info("Estado de alquiler eliminado");
        } catch (Exception e) {
            //log.error("Error al elimniar el estado de alquiler", e.getMessage());
            throw new CustomeException("Error al elimniar el estado de alquiler");
        }
    }

    /**
     * Obbtiene la lista de Estados de alquiler
     * @return 
     */
    @Override
    public List<StateRentDTO> getAllStateRents() {
        //log.info("Obteniendo todos los estados de alquiler");
        try{
            List<StateRent> states = stateRentRepository.findAll();
            return states.stream()
                    .map(stateRentMapper::toDTO)
                    .collect(Collectors.toList());
        }catch (Exception e) {
            //log.error("Error al obtener los estados de alquiler", e.getMessage());
            throw new CustomeException("Error al obtener los estados de alquiler");
        }
    }

    /**
     * Obtiene el estado de alquiler seleccionado por su id
     * @param id 
     * @return
     */
    @Override
    public StateRentDTO getStateRentById(Integer id) {
        //log.info("Obteniendo el estado de alquiler con id: {}", id);
        try{
            StateRent stateRent = stateRentRepository.findById(id).orElseThrow(() -> new CustomeException("El estado de alquiler no existe"));
            return stateRentMapper.toDTO(stateRent);
        }catch (Exception e) {
            //log.error("Error obteniendo el estado de alquiler", e.getMessage());
            throw new CustomeException("Error obteniendo el estado de alquiler");
        }
    }
}
