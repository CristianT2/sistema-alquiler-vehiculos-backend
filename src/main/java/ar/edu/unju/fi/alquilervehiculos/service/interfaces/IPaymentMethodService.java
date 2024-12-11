package ar.edu.unju.fi.alquilervehiculos.service.interfaces;

import ar.edu.unju.fi.alquilervehiculos.dto.PaymentDTO;
import ar.edu.unju.fi.alquilervehiculos.dto.PaymentMethodDTO;

import java.util.List;

public interface IPaymentMethodService {

    PaymentMethodDTO createPaymentMethod(PaymentMethodDTO paymentMethodDTO);
    PaymentMethodDTO updatePaymentMethod(Integer id, PaymentMethodDTO paymentMethodDTO);
    void deletePaymentMethod(Integer id);
    List<PaymentMethodDTO> getAllPaymentMethods();
    PaymentMethodDTO getPaymentMethodById(Integer id);
}
