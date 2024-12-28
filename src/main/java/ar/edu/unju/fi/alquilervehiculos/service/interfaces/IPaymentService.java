package ar.edu.unju.fi.alquilervehiculos.service.interfaces;

import ar.edu.unju.fi.alquilervehiculos.dto.PaymentDTO;

import java.util.List;

public interface IPaymentService {

    PaymentDTO createPayment(PaymentDTO paymentDTO);
    PaymentDTO updatePayment(Integer id, PaymentDTO paymentDTO);
    void deletePayment(Integer id);
    List<PaymentDTO> getAllPayments();
    PaymentDTO getPaymentById(Integer id);
}
