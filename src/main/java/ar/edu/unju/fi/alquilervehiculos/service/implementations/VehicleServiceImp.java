package ar.edu.unju.fi.alquilervehiculos.service.implementations;

import ar.edu.unju.fi.alquilervehiculos.dto.VehicleDTO;
import ar.edu.unju.fi.alquilervehiculos.entities.*;
import ar.edu.unju.fi.alquilervehiculos.exceptions.CustomeException;
import ar.edu.unju.fi.alquilervehiculos.mappers.VehicleMapper;
import ar.edu.unju.fi.alquilervehiculos.repository.*;
import ar.edu.unju.fi.alquilervehiculos.service.interfaces.IVehicleService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//@Slf4j
@Service
public class VehicleServiceImp implements IVehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final ColorRepository colorRepository;
    private final StateVehicleRepository stateVehicleRepository;

    @Autowired
    public VehicleServiceImp(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper,
                             CategoryRepository categoryRepository, BrandRepository brandRepository,
                             ModelRepository modelRepository, ColorRepository colorRepository,
                             StateVehicleRepository stateVehicleRepository) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleMapper = vehicleMapper;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.colorRepository = colorRepository;
        this.stateVehicleRepository = stateVehicleRepository;
    }


    /**
     * Crea un vehiculo
     * @param vehicleDTO 
     * @return
     */
    @Transactional
    @Override
    public VehicleDTO createVehicle(VehicleDTO vehicleDTO) {
        //log.info("Creando un vehiculo");
        try{
            Vehicle vehicle = validateAndMapVehicle(vehicleDTO);
            //log.info("Vehiculo creado");
            return vehicleMapper.toDTO(vehicleRepository.save(vehicle));
        }catch (Exception e){
            //log.error("Error creando el vehiculo", e.getMessage());
            throw new CustomeException("Error creando el vehiculo");
        }
    }

    /**
     * Actualiza el vehiculo seleccionado por su id
     * @param id 
     * @param vehicleDTO
     * @return
     */
    @Transactional
    @Override
    public VehicleDTO updateVehicle(Integer id, VehicleDTO vehicleDTO) {
        //log.info("Actualizando vehiculo con id: {}", id);
        try{
            vehicleRepository.findById(id).orElseThrow(() -> new CustomeException("El vehiculo no existe"));
            Vehicle vehicle = validateAndMapVehicle(vehicleDTO);
            vehicle.setId(id);

            //log.info("Vehiculo actualizado correctamente");
            return vehicleMapper.toDTO(vehicleRepository.save(vehicle));
        }catch (Exception e){
            //log.error("Error actualizando el vehiculo", e.getMessage());
            throw new CustomeException("Error actualizando el vehiculo");
        }
    }

    /**
     * Elimina el vehiculo seleccionado por su id
     * @param id 
     */
    @Transactional
    @Override
    public void deleteVehicle(Integer id) {
        //log.info("Eliminando vehiculo con id: {}", id);
        try{
            Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new CustomeException("El vehiculo no existe"));
            vehicleRepository.delete(vehicle);
            //log.info("Vehiculo eliminado");
        }catch (Exception e){
            //log.error("Error eliminando el vehiculo", e.getMessage());
            throw new CustomeException("Error eliminando el vehiculo");
        }
    }

    /**
     * Obtiene todos los vehiculos
     * @return 
     */
    @Override
    public List<VehicleDTO> getAllVehicles() {
        //log.info("Obteniendo todos los vehiculos");
        try{
            List<Vehicle> vehicles = vehicleRepository.findAll();
            return vehicles.stream()
                    .map(vehicleMapper::toDTO)
                    .collect(Collectors.toList());
        }catch (Exception e){
            //log.error("Error obtener los vehiculos", e.getMessage());
            throw new CustomeException("Error obteniendo los vehiculos");
        }
    }

    /**
     * Obtiene el vehiculo sellecionado por su id
     * @param id 
     * @return
     */
    @Override
    public VehicleDTO getVehicleById(Integer id) {
        //log.info("Obteniendo vehiculo con id: {}", id);
        try{
            Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new CustomeException("El vehiculo no existe"));
            return vehicleMapper.toDTO(vehicle);
        }catch (Exception e){
            //log.error("Error obteniendo el vehiculo", e.getMessage());
            throw new CustomeException("Error obteniendo el vehiculo");
        }
    }

    /**
     * Valida y mapea los objetos que componen al vehiculo
     * @param vehicleDTO
     * @return
     */
    private Vehicle validateAndMapVehicle(VehicleDTO vehicleDTO) {
        Category category = categoryRepository.findById(vehicleDTO.getCategory().getId()).orElseThrow(() -> new CustomeException("La categoria no existe"));
        Brand brand = brandRepository.findById(vehicleDTO.getBrand().getId()).orElseThrow(() -> new CustomeException("La marca no existe"));
        Model model = modelRepository.findById(vehicleDTO.getModel().getId()).orElseThrow(() -> new CustomeException("El modelo no existe"));
        Color color = colorRepository.findById(vehicleDTO.getColor().getId()).orElseThrow(() -> new CustomeException("El color no existe"));
        StateVehicle stateVehicle = stateVehicleRepository.findById(vehicleDTO.getStateVehicle().getId()).orElseThrow(() -> new CustomeException("El estado de vehiculo no existe"));

        Vehicle vehicle = vehicleMapper.toEntity(vehicleDTO);
        vehicle.setCategory(category);
        vehicle.setBrand(brand);
        vehicle.setModel(model);
        vehicle.setColor(color);
        vehicle.setStateVehicle(stateVehicle);

        return vehicle;
    }
}
