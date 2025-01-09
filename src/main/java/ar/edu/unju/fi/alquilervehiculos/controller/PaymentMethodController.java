package ar.edu.unju.fi.alquilervehiculos.controller;

import ar.edu.unju.fi.alquilervehiculos.dto.PaymentMethodDTO;
import ar.edu.unju.fi.alquilervehiculos.exceptions.CustomeException;
import ar.edu.unju.fi.alquilervehiculos.service.interfaces.IPaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/paymentMethod")
public class PaymentMethodController {

    private final IPaymentMethodService paymentMethodService;

    @Autowired
    public PaymentMethodController(IPaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PaymentMethodDTO> createPaymentMethod(@RequestBody PaymentMethodDTO paymentMethodDTO) {
        try{
            return ResponseEntity.ok(paymentMethodService.createPaymentMethod(paymentMethodDTO));
        } catch(Exception e){
            throw new CustomeException("Error al crear el metodo de pago");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentMethodDTO> updatePaymentMethod(@PathVariable Integer id, @RequestBody PaymentMethodDTO paymentMethodDTO) {
        try{
            return ResponseEntity.ok(paymentMethodService.updatePaymentMethod(id, paymentMethodDTO));
        } catch(Exception e){
            throw new CustomeException("Error al actualizar el metodo de pago");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePaymentMethod(@PathVariable Integer id) {
        try{
            paymentMethodService.deletePaymentMethod(id);
        } catch(Exception e){
            throw new CustomeException("Error al eliminar el metodo de pago");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<PaymentMethodDTO>> getAllPaymentMethods() {
        try{
            List<PaymentMethodDTO> paymentMethods = paymentMethodService.getAllPaymentMethods();
            return ResponseEntity.ok(paymentMethods);
        } catch(Exception e){
            throw new CustomeException("Error al obtener los metodos de pago");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethodDTO> getPaymentMethodById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok(paymentMethodService.getPaymentMethodById(id));
        } catch(Exception e){
            throw new CustomeException("Error al obtener el metodo de pago");
        }
    }
}
