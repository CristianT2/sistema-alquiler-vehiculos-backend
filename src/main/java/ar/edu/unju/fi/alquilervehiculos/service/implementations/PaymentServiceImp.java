package ar.edu.unju.fi.alquilervehiculos.service.implementations;

import ar.edu.unju.fi.alquilervehiculos.dto.PaymentDTO;
import ar.edu.unju.fi.alquilervehiculos.entities.*;
import ar.edu.unju.fi.alquilervehiculos.exceptions.CustomeException;
import ar.edu.unju.fi.alquilervehiculos.mappers.PaymentMapper;
import ar.edu.unju.fi.alquilervehiculos.repository.*;
import ar.edu.unju.fi.alquilervehiculos.service.interfaces.IPaymentService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//@Slf4j
@Service
public class PaymentServiceImp implements IPaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final RentRepository rentRepository;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    @Autowired
    public PaymentServiceImp(PaymentRepository paymentRepository, PaymentMapper paymentMapper,
                             RentRepository rentRepository, UserRepository userRepository, VehicleRepository vehicleRepository,
                             PaymentMethodRepository paymentMethodRepository) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
        this.rentRepository = rentRepository;
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
        this.paymentMethodRepository = paymentMethodRepository;
    }

    /**
     * Crea el pago
     * @param paymentDTO
     * @return
     */
    @Transactional
    @Override
    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        //log.info("Creando pago");
        try{
            Payment payment = validateAndMapPayment(paymentDTO);
            //log.info("Pago creado correctamente");
            return paymentMapper.toDTO(paymentRepository.save(payment));
        }catch(Exception e){
            //log.error("Error al crear el pago", e.getMessage());
            throw new CustomeException("Error al crear el pago");
        }
    }

    /**
     * Actualiza el pago seleccionado por su id
     * @param id
     * @param paymentDTO
     * @return
     */
    @Transactional
    @Override
    public PaymentDTO updatePayment(Integer id, PaymentDTO paymentDTO) {
        //log.info("Actualizando pago con id: {}", id);
        try{
            paymentRepository.findById(id).orElseThrow(() -> new CustomeException("El pago no existe"));
            Payment payment = validateAndMapPayment(paymentDTO);
            payment.setId(id);

            //log.info("Pago actualizado");
            return paymentMapper.toDTO(paymentRepository.save(payment));
        }catch(Exception e){
            //log.error("Error al actualizar el pago", e.getMessage());
            throw new CustomeException("Error al actualizar el pago");
        }
    }

    /**
     * Elimina el pago seleccionado por su id
     * @param id
     */
    @Transactional
    @Override
    public void deletePayment(Integer id) {
        //log.info("Eliminando pago con id: {}", id);
        try{
            Payment payment = paymentRepository.findById(id).orElseThrow(() -> new CustomeException("El pago no existe"));
            paymentRepository.delete(payment);
            //log.info("Pago eliminado");
        }catch(Exception e){
            //log.error("Error al eliminar el pago", e.getMessage());
            throw new CustomeException("Error al eliminar el pago");
        }
    }

    /**
     * Obtiene la lista de pagos
     * @return
     */
    @Override
    public List<PaymentDTO> getAllPayments() {
        //log.info("Obteniendo los pagos");
        try{
            List<Payment> payments = paymentRepository.findAll();
            return payments.stream()
                    .map(paymentMapper::toDTO)
                    .collect(Collectors.toList());
        }catch(Exception e){
            //log.error("Error al obtener los pagos", e.getMessage());
            throw new CustomeException("Error al obtener los pagos");
        }
    }

    /**
     * Obtiene el pago seleccionado por su id
     * @param id
     * @return
     */
    @Override
    public PaymentDTO getPaymentById(Integer id) {
        //log.info("Obteniendo pago con id: {}", id);
        try{
            Payment payment = paymentRepository.findById(id).orElseThrow(() -> new CustomeException("El pago no existe"));
            return paymentMapper.toDTO(payment);
        }catch(Exception e){
            //log.error("Error al obtener el pago", e.getMessage());
            throw new CustomeException("Error al obtener el pago");
        }
    }

    /**
     * Valida y mapea los atributos de Pago
     * @param paymentDTO
     * @return
     */
    private Payment validateAndMapPayment(PaymentDTO paymentDTO){
        Rent rent = rentRepository.findById(paymentDTO.getRent().getId()).orElseThrow(() -> new CustomeException("El alquiler no existe"));
        User user = userRepository.findById(paymentDTO.getUser().getId()).orElseThrow(() -> new CustomeException("El usuario no existe"));
        Vehicle vehicle = vehicleRepository.findById(paymentDTO.getVehicle().getId()).orElseThrow(() -> new CustomeException("El vehiculo no existe"));
        PaymentMethod paymentMethod = paymentMethodRepository.findById(paymentDTO.getPaymentMethod().getId()).orElseThrow(() -> new CustomeException("El metodo de pago no existe"));

        Payment payment = paymentMapper.toEntity(paymentDTO);
        payment.setRent(rent);
        payment.setUser(user);
        payment.setVehicle(vehicle);
        payment.setPaymentMethod(paymentMethod);

        return payment;
    }
}
