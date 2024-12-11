package ar.edu.unju.fi.alquilervehiculos.service.interfaces;

import ar.edu.unju.fi.alquilervehiculos.dto.CityDTO;

import java.util.List;

public interface ICityService {

    CityDTO createCity(CityDTO cityDTO);
    CityDTO updateCity(Integer id, CityDTO cityDTO);
    void deleteCity(Integer id);
    List<CityDTO> getAllCities();
    CityDTO getCityById(Integer id);
}
