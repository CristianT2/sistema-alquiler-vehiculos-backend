package ar.edu.unju.fi.alquilervehiculos.service.interfaces;

import ar.edu.unju.fi.alquilervehiculos.dto.ColorDTO;

import java.util.List;

public interface IColorService {

    ColorDTO createColor(ColorDTO colorDTO);
    ColorDTO updateColor(Integer id, ColorDTO colorDTO);
    void deleteColor(Integer id);
    List<ColorDTO> getAllColors();
    ColorDTO getColor(Integer id);
}
