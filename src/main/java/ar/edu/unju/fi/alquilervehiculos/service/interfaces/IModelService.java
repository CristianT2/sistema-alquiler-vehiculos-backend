package ar.edu.unju.fi.alquilervehiculos.service.interfaces;

import ar.edu.unju.fi.alquilervehiculos.dto.ModelDTO;

import java.util.List;

public interface IModelService {

    ModelDTO createModel(ModelDTO modelDTO);
    ModelDTO updateModel(Integer id, ModelDTO modelDTO);
    void deleteModel(Integer id);
    List<ModelDTO> getAllModels();
    ModelDTO getModelById(Integer id);
}
