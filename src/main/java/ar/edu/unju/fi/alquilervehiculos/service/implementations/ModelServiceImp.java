package ar.edu.unju.fi.alquilervehiculos.service.implementations;

import ar.edu.unju.fi.alquilervehiculos.dto.ModelDTO;
import ar.edu.unju.fi.alquilervehiculos.entities.Brand;
import ar.edu.unju.fi.alquilervehiculos.entities.Model;
import ar.edu.unju.fi.alquilervehiculos.exceptions.CustomeException;
import ar.edu.unju.fi.alquilervehiculos.mappers.ModeloMapper;
import ar.edu.unju.fi.alquilervehiculos.repository.BrandRepository;
import ar.edu.unju.fi.alquilervehiculos.repository.ModelRepository;
import ar.edu.unju.fi.alquilervehiculos.service.interfaces.IModelService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//@Slf4j
@Service
public class ModelServiceImp implements IModelService {

    private final ModelRepository modelRepository;
    private final ModeloMapper modeloMapper;
    private final BrandRepository brandRepository;

    @Autowired
    public ModelServiceImp(ModelRepository modelRepository, ModeloMapper modeloMapper, BrandRepository brandRepository) {
        this.modelRepository = modelRepository;
        this.modeloMapper = modeloMapper;
        this.brandRepository = brandRepository;
    }

    /**
     * Crea un modelo
     * @param modelDTO
     * @return
     */
    @Transactional
    @Override
    public ModelDTO createModel(ModelDTO modelDTO) {
        //log.info("Creando un modelo");
        try{
            if (modelRepository.existsByName(modelDTO.getName())){
                throw new CustomeException("El modelo ya existe");
            }
            Brand brand = brandRepository.findById(modelDTO.getBrand().getId()).orElseThrow(() -> new CustomeException("La marca no existe"));
            Model model = modeloMapper.toEntity(modelDTO);
            model.setBrand(brand);

            //log.info("Modelo creado correctamente");
            return modeloMapper.toDTO(modelRepository.save(model));
        }catch(Exception e){
            //log.error("Error al intentar crear un modelo");
            throw new CustomeException("Error al intentar crear un modelo");
        }
    }

    /**
     * Actualiza un modelo
     * @param id
     * @param modelDTO
     * @return
     */
    @Transactional
    @Override
    public ModelDTO updateModel(Integer id, ModelDTO modelDTO) {
        //log.info("Modificando un modelo con id: {}", id);
        try{
            Model model = modelRepository.findById(id).orElseThrow(() -> new CustomeException("El model no existe"));
            Brand brand = brandRepository.findById(modelDTO.getBrand().getId()).orElseThrow(() -> new CustomeException("La marca no existe"));

            modeloMapper.toEntity(modelDTO);
            model.setBrand(brand);
            model.setId(id);

            //log.info("Modelo actualizado correctamente");
            return modeloMapper.toDTO(modelRepository.save(model));
        }catch(Exception e){
            //log.error("Error al intentar actualizar el modelo");
            throw new CustomeException("Error al intentar actualizar el modelo");
        }
    }

    /**
     * Elimina un modelo seleccionado por su id
     * @param id
     */
    @Transactional
    @Override
    public void deleteModel(Integer id) {
        //log.info("Eliminando Modelo con id: {}", id);
        try{
            Model model = modelRepository.findById(id).orElseThrow(() -> new CustomeException("El model no existe"));
            modelRepository.delete(model);
            //log.info("Modelo eliminado");
        }catch(Exception e){
            //log.error("Error al intentar eliminar el modelo");
            throw new CustomeException("Error al intentar eliminar el modelo");
        }

    }

    /**
     * Obtiene todos los modelos
     * @return
     */
    @Override
    public List<ModelDTO> getAllModels() {
        //log.info("Obteniendo todos los modelos");
        try{
            List<Model> model = modelRepository.findAll();
            return  model.stream()
                    .map(modeloMapper::toDTO)
                    .collect(Collectors.toList());
        }catch(Exception e){
            //log.error("Error al intentar obtener los modelos");
            throw new CustomeException("Error al intentar obtener los modelos");
        }
    }

    /**
     * Obtiene el modelo seleccionado por su id
     * @param id
     * @return
     */
    @Override
    public ModelDTO getModelById(Integer id) {
        //log.info("Obteniendo un modelo con id: {}", id);
        try{
            Model model = modelRepository.findById(id).orElseThrow(() -> new CustomeException("El model no existe"));
            return modeloMapper.toDTO(model);
        }catch(Exception e){
            //log.error("Error al intentar obtener el modelo");
            throw new CustomeException("Error al intentar obtener el modelo");
        }
    }
}
