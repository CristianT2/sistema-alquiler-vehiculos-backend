package ar.edu.unju.fi.alquilervehiculos.service.implementations;

import ar.edu.unju.fi.alquilervehiculos.dto.PaymentMethodDTO;
import ar.edu.unju.fi.alquilervehiculos.entities.PaymentMethod;
import ar.edu.unju.fi.alquilervehiculos.exceptions.CustomeException;
import ar.edu.unju.fi.alquilervehiculos.mappers.PaymentMethodMapper;
import ar.edu.unju.fi.alquilervehiculos.repository.PaymentMethodRepository;
import ar.edu.unju.fi.alquilervehiculos.service.interfaces.IPaymentMethodService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//@Slf4j
@Service
public class PaymentMethodServiceImp implements IPaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentMethodMapper paymentMethodMapper;

    @Autowired
    public PaymentMethodServiceImp(PaymentMethodRepository paymentMethodRepository, PaymentMethodMapper paymentMethodMapper) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.paymentMethodMapper = paymentMethodMapper;
    }

    /**
     * Crea un metodo de pago
     * @param paymentMethodDTO 
     * @return
     */
    @Transactional
    @Override
    public PaymentMethodDTO createPaymentMethod(PaymentMethodDTO paymentMethodDTO) {
        //log.info("Creando metodo de pago");
        try{
            if (paymentMethodRepository.existsByName(paymentMethodDTO.getName())){
                throw new CustomeException("El metodo de pago ya existe");
            }
            PaymentMethod paymentMethod = paymentMethodMapper.toEntity(paymentMethodDTO);
            //log.info("Metodo de pago creado");
            return paymentMethodMapper.toDTO(paymentMethodRepository.save(paymentMethod));
        }catch (Exception e){
            //log.error("Error al crear el metodo de pago");
            throw new CustomeException("Error al crear el metodo de pago");
        }
    }

    /**
     * Actualiza el metodo de pago seleccionado por su id
     * @param id 
     * @param paymentMethodDTO
     * @return
     */
    @Transactional
    @Override
    public PaymentMethodDTO updatePaymentMethod(Integer id, PaymentMethodDTO paymentMethodDTO) {
        //log.info("Actualizando metodo de pago con id: {}", id);
        try{
            paymentMethodRepository.findById(id).orElseThrow(() -> new CustomeException("El metodo de pago no existe"));
            PaymentMethod paymentMethod = paymentMethodMapper.toEntity(paymentMethodDTO);
            paymentMethod.setId(id);

            //log.info("Metodo de pago actualizado");
            return paymentMethodMapper.toDTO(paymentMethodRepository.save(paymentMethod));
        }catch (Exception e){
            //log.error("Error al actualizar el metodo de pago");
            throw new CustomeException("Error al actualizar el metodo de pago");
        }
    }

    /**
     * Elimina el metodo de pago seleccionado por su id
     * @param id 
     */
    @Transactional
    @Override
    public void deletePaymentMethod(Integer id) {
        //log.info("Eliminando metodo de pago con id: {}", id);
        try{
            PaymentMethod paymentMethod = paymentMethodRepository.findById(id).orElseThrow(() -> new CustomeException("El metodo de pago no existe"));
            paymentMethodRepository.delete(paymentMethod);
            //log.info("Metodo de pago eliminado");
        }catch (Exception e){
            //log.error("Error al eliminar el metodo de pago");
            throw new CustomeException("Error al eliminar el metodo de pago");
        }
    }

    /**
     * Obtiene los metodos de pago
     * @return 
     */
    @Override
    public List<PaymentMethodDTO> getAllPaymentMethods() {
        //log.info("Obteniendo los metodos de pago");
        try{
            List<PaymentMethod> methods = paymentMethodRepository.findAll();
            return methods.stream()
                    .map(paymentMethodMapper::toDTO)
                    .collect(Collectors.toList());
        }catch (Exception e){
            //log.error("Error al obtener los metodos de pago");
            throw new CustomeException("Error al obtener los metodos de pago");
        }
    }

    /**
     * Obtiene el metodo de pago seleccionadp por su id
     * @param id 
     * @return
     */
    @Override
    public PaymentMethodDTO getPaymentMethodById(Integer id) {
        //log.info("Obteniendo metodo de pago con id: {}", id);
        try{
            PaymentMethod paymentMethod = paymentMethodRepository.findById(id).orElseThrow(() -> new CustomeException("El metodo de pago no existe"));
            return paymentMethodMapper.toDTO(paymentMethod);
        }catch (Exception e){
            //log.error("Error al obtener el metodo de pago");
            throw new CustomeException("Error al obtener el metodo de pago");
        }
    }
}
