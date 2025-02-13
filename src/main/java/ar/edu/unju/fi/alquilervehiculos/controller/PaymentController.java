package ar.edu.unju.fi.alquilervehiculos.controller;

import ar.edu.unju.fi.alquilervehiculos.dto.PaymentDTO;
import ar.edu.unju.fi.alquilervehiculos.exceptions.CustomeException;
import ar.edu.unju.fi.alquilervehiculos.service.interfaces.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    private final IPaymentService paymentService;

    @Autowired
    public PaymentController(IPaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody PaymentDTO paymentDTO){
        try{
            return ResponseEntity.ok(paymentService.createPayment(paymentDTO));
        } catch(Exception e){
            throw new CustomeException("Error al crear el pago");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDTO> updatePayment(@PathVariable Integer id, @RequestBody PaymentDTO paymentDTO){
        try{
            return ResponseEntity.ok(paymentService.updatePayment(id, paymentDTO));
        } catch(Exception e){
            throw new CustomeException("Error al actualizar el pago");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePayment(@PathVariable Integer id){
        try{
            paymentService.deletePayment(id);
        } catch(Exception e){
            throw new CustomeException("Error al eliminar el pago");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<PaymentDTO>> getAllPayments(){
        try{
            List<PaymentDTO> payments = paymentService.getAllPayments();
            return ResponseEntity.ok(payments);
        } catch(Exception e){
            throw new CustomeException("Error al obtener los pagos");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable Integer id){
        try{
            return ResponseEntity.ok(paymentService.getPaymentById(id));
        } catch(Exception e){
            throw new CustomeException("Error al obtener el pago");
        }
    }
}
