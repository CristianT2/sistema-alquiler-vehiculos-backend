package ar.edu.unju.fi.alquilervehiculos.mappers;

import ar.edu.unju.fi.alquilervehiculos.dto.PaymentDTO;
import ar.edu.unju.fi.alquilervehiculos.entities.Payment;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    private ModelMapper modelMapper;

    public PaymentMapper() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    /**
     * Convierte un ojeto Payment en un objeto PaymentDTO
     * @param payment
     * @return
     */
    public PaymentDTO toDTO(Payment payment) {
        return modelMapper.map(payment, PaymentDTO.class);
    }

    /**
     * Convierte un ojeto PaymentDTO en un objeto Payment
     * @param paymentDTO
     * @return
     */
    public Payment toEntity(PaymentDTO paymentDTO) {
        return modelMapper.map(paymentDTO, Payment.class);
    }
}
