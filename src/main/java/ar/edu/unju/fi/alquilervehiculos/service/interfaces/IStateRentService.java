package ar.edu.unju.fi.alquilervehiculos.service.interfaces;

import ar.edu.unju.fi.alquilervehiculos.dto.StateRentDTO;

import java.util.List;

public interface IStateRentService {

    StateRentDTO createStateRent(StateRentDTO stateRentDTO);
    StateRentDTO updateStateRent(Integer id, StateRentDTO stateRentDTO);
    void deleteStateRent(Integer id);
    List<StateRentDTO> getAllStateRents();
    StateRentDTO getStateRentById(Integer id);
}
