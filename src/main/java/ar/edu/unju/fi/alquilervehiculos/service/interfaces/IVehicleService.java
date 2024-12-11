package ar.edu.unju.fi.alquilervehiculos.service.interfaces;

import ar.edu.unju.fi.alquilervehiculos.dto.VehicleDTO;

import java.util.List;

public interface IVehicleService {

    VehicleDTO createVehicle(VehicleDTO vehicleDTO);
    VehicleDTO updateVehicle(Integer id, VehicleDTO vehicleDTO);
    void deleteVehicle(Integer id);
    List<VehicleDTO> getAllVehicles();
    VehicleDTO getVehicleById(Integer id);
}
