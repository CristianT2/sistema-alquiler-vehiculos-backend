package ar.edu.unju.fi.alquilervehiculos.service.interfaces;

import ar.edu.unju.fi.alquilervehiculos.dto.RentDTO;

import java.util.List;

public interface IRentService {

    RentDTO createRent(RentDTO rentDTO);
    RentDTO updateRent(Integer id, RentDTO rentDTO);
    void deleteRent(Integer id);
    List<RentDTO> getAllRents();
    RentDTO getRentById(Integer id);
}
