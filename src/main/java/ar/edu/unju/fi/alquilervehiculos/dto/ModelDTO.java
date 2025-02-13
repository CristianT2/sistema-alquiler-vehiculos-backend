package ar.edu.unju.fi.alquilervehiculos.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@NoArgsConstructor
//@AllArgsConstructor
//@Data
public class ModelDTO {

    private Integer id;
    private String name;
    private String description;
    @JsonBackReference
    private BrandDTO brand;


    public ModelDTO() {
    }

    public ModelDTO(Integer id, String name, String description, BrandDTO brand) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.brand = brand;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BrandDTO getBrand() {
        return brand;
    }

    public void setBrand(BrandDTO brand) {
        this.brand = brand;
    }

}
