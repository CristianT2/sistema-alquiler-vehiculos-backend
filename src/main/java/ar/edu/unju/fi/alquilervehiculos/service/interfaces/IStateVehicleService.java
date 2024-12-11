package ar.edu.unju.fi.alquilervehiculos.service.interfaces;

import ar.edu.unju.fi.alquilervehiculos.dto.StateVehicleDTO;

import java.util.List;

public interface IStateVehicleService {

    StateVehicleDTO createStateVehicle(StateVehicleDTO stateVehicleDTO);
    StateVehicleDTO updateStateVehicle(Integer id, StateVehicleDTO stateVehicleDTO);
    void deleteStateVehicle(Integer id);
    List<StateVehicleDTO> getAllStateVehicles();
    StateVehicleDTO getStateVehicleById(Integer id);
}
