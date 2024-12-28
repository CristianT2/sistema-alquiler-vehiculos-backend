package ar.edu.unju.fi.alquilervehiculos.service.implementations;

import ar.edu.unju.fi.alquilervehiculos.dto.RentDTO;
import ar.edu.unju.fi.alquilervehiculos.entities.Rent;
import ar.edu.unju.fi.alquilervehiculos.entities.StateRent;
import ar.edu.unju.fi.alquilervehiculos.entities.User;
import ar.edu.unju.fi.alquilervehiculos.entities.Vehicle;
import ar.edu.unju.fi.alquilervehiculos.exceptions.CustomeException;
import ar.edu.unju.fi.alquilervehiculos.mappers.RentMapper;
import ar.edu.unju.fi.alquilervehiculos.repository.RentRepository;
import ar.edu.unju.fi.alquilervehiculos.repository.StateRentRepository;
import ar.edu.unju.fi.alquilervehiculos.repository.UserRepository;
import ar.edu.unju.fi.alquilervehiculos.repository.VehicleRepository;
import ar.edu.unju.fi.alquilervehiculos.service.interfaces.IRentService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//@Slf4j
@Service
public class RentServiceImp implements IRentService {

    private final RentRepository rentRepository;
    private final RentMapper rentMapper;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;
    private final StateRentRepository stateRentRepository;

    @Autowired
    public RentServiceImp(RentRepository rentRepository, RentMapper rentMapper, UserRepository userRepository,
                          VehicleRepository vehicleRepository, StateRentRepository stateRentRepository){
        this.rentRepository = rentRepository;
        this.rentMapper = rentMapper;
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
        this.stateRentRepository = stateRentRepository;
    }

    /**
     * Crea un alquiler
     * @param rentDTO 
     * @return
     */
    @Transactional
    @Override
    public RentDTO createRent(RentDTO rentDTO) {
        //log.info("Creando un alquiler");
        try{
            Rent rent = validateAndMapRent(rentDTO);
            //log.info("Alquiler creado");
            return rentMapper.toDTO(rentRepository.save(rent));
        }catch (Exception e){
            //log.error("Error al crear el alquiler", e);
            throw new CustomeException("Error al crear el alquiler");
        }
    }

    /**
     * Actualizar alquiler seleccionado por su id
     * @param id 
     * @param rentDTO
     * @return
     */
    @Transactional
    @Override
    public RentDTO updateRent(Integer id, RentDTO rentDTO) {
        //log.info("Actualizando alquiler con id: {}", id);
        try{
            rentRepository.findById(id).orElseThrow(() -> new CustomeException("El aquiler no existe"));
            Rent rent = validateAndMapRent(rentDTO);
            rent.setId(id);

            //log.info("Alquiler actualizado");
            return rentMapper.toDTO(rentRepository.save(rent));
        }catch (Exception e){
            //log.error("Error al actualizar el alquiler", e);
            throw new CustomeException("Error al actualizar el alquiler");
        }
    }

    /**
     * Elimina un alquiler seleccionado por su id
     * @param id 
     */
    @Transactional
    @Override
    public void deleteRent(Integer id) {
        //log.info("Eliminando alquiler con id: {}", id);
        try{
            Rent rent = rentRepository.findById(id).orElseThrow(() -> new CustomeException("El aquiler no existe"));
            rentRepository.delete(rent);
            //log.info("Alquiler eliminado");
        } catch (Exception e) {
            //log.error("Error a eliminar el alquiler");
            throw new CustomeException("Error a eliminar el alquiler");
        }
    }

    /**
     * Obtiene todos los alquileres
     * @return 
     */
    @Override
    public List<RentDTO> getAllRents() {
        //log.info("Obteniendo los alquileres");
        try{
            List<Rent> rents = rentRepository.findAll();
            return rents.stream()
                    .map(rentMapper::toDTO)
                    .collect(Collectors.toList());
        }catch (Exception e) {
            //log.error("Error a obtener los alquileres");
            throw new CustomeException("Error a obtener los alquileres");
        }
    }

    /**
     * Obtiene el alquiler seleccionado por su id
     * @param id 
     * @return
     */
    @Override
    public RentDTO getRentById(Integer id) {
        //log.info("Obteniendo alquiler con id: {}", id);
        try{
            Rent rent = rentRepository.findById(id).orElseThrow(() -> new CustomeException("El aquiler no existe"));
            return rentMapper.toDTO(rent);
        }catch (Exception e) {
            //log.error("Error a obtener el alquiler");
            throw new CustomeException("Error a obtener el alquiler");
        }
    }

    /**
     * Valida y mapea los atributos delobjeto alquiler
     * @param rentDTO
     * @return
     */
    private Rent validateAndMapRent(RentDTO rentDTO){
        User user = userRepository.findById(rentDTO.getUser().getId()).orElseThrow(() -> new CustomeException("El usuario no existe"));
        Vehicle vehicle = vehicleRepository.findById(rentDTO.getVehicle().getId()).orElseThrow(() -> new CustomeException("El vehiculo no existe"));
        StateRent stateRent = stateRentRepository.findById(rentDTO.getStateRent().getId()).orElseThrow(() -> new CustomeException("El estado no existe"));

        Rent rent = rentMapper.toEntity(rentDTO);
        rent.setUser(user);
        rent.setVehicle(vehicle);
        rent.setStateRent(stateRent);

        return rent;
    }
}
