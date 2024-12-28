package ar.edu.unju.fi.alquilervehiculos.service.implementations;

import ar.edu.unju.fi.alquilervehiculos.dto.CityDTO;
import ar.edu.unju.fi.alquilervehiculos.entities.City;
import ar.edu.unju.fi.alquilervehiculos.exceptions.CustomeException;
import ar.edu.unju.fi.alquilervehiculos.mappers.CityMapper;
import ar.edu.unju.fi.alquilervehiculos.repository.CityRepository;
import ar.edu.unju.fi.alquilervehiculos.service.interfaces.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImp implements ICityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Autowired
    public CityServiceImp(CityRepository cityRepository, CityMapper cityMapper){
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    /**
     * Crea una ciudad
     * @param cityDTO 
     * @return
     */
    @Override
    public CityDTO createCity(CityDTO cityDTO) {
        try{
            if(cityRepository.existsByName(cityDTO.getName())){
                throw new CustomeException("La ciudad ya existe");
            }
            City city = cityMapper.toEntity(cityDTO);
            return cityMapper.toDTO(cityRepository.save(city));
        }catch (Exception e){
            throw new CustomeException("Error al crear la ciudad");
        }
    }

    /**
     * Actualiza la ciudad seleccionada por su id
     * @param id 
     * @param cityDTO
     * @return
     */
    @Override
    public CityDTO updateCity(Integer id, CityDTO cityDTO) {
        try{
            City city = cityRepository.findById(id).orElseThrow(() -> new CustomeException("La ciudad no existe"));
            cityMapper.toEntity(cityDTO);
            city.setId(id);
            return cityMapper.toDTO(cityRepository.save(city));
        }catch (Exception e){
            throw new CustomeException("Error al actualizar la ciudad");
        }
    }

    /**
     * Elimina una ciudad
     * @param id 
     */
    @Override
    public void deleteCity(Integer id) {
        try{
            City city = cityRepository.findById(id).orElseThrow(() -> new CustomeException("La ciudad no existe"));
            cityRepository.delete(city);
        }catch (Exception e){
            throw new CustomeException("Error al eliminar la ciudad");
        }
    }

    /**
     * Obtiene todos las ciudades
     * @return 
     */
    @Override
    public List<CityDTO> getAllCities() {
        try{
            List<City> cities = cityRepository.findAll();
            return cities.stream()
                    .map(cityMapper::toDTO)
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw new CustomeException("Error al obtener las ciudades");
        }
    }

    /**
     * Obtiene la ciudad sellecionada por su id
     * @param id 
     * @return
     */
    @Override
    public CityDTO getCityById(Integer id) {
        try{
            City city = cityRepository.findById(id).orElseThrow(() -> new CustomeException("La ciudad no existe"));
            return cityMapper.toDTO(city);
        }catch (Exception e){
            throw new CustomeException("Error al obtener la ciudad");
        }
    }
}
