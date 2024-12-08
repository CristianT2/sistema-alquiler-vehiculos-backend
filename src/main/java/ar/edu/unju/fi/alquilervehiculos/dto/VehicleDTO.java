package ar.edu.unju.fi.alquilervehiculos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleDTO {

    private CategoryDTO category;
    private BrandDTO brand;
    private ModelDTO model;
    private ColorDTO color;
    private StateVehicleDTO stateVehicle;
    private int year;
    private double mileage;
    private String tuition;
    private double price;
    private String description;
    private byte[] photo;
}
