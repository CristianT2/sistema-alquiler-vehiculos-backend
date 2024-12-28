package ar.edu.unju.fi.alquilervehiculos.mappers;

import ar.edu.unju.fi.alquilervehiculos.dto.PaymentMethodDTO;
import ar.edu.unju.fi.alquilervehiculos.entities.PaymentMethod;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class PaymentMethodMapper {

    private ModelMapper modelMapper;

    public PaymentMethodMapper(){
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    /**
     * Convierte un objeto PaymentMethod a un objeto PaymentMethodDTO
     * @param paymentMethod
     * @return
     */
    public PaymentMethodDTO toDTO(PaymentMethod paymentMethod){
        return modelMapper.map(paymentMethod, PaymentMethodDTO.class);
    }

    /**
     * Convierte un objeto PaymentMethodDTO a un objeto PaymentMethod
     * @param paymentMethodDTO
     * @return
     */
    public PaymentMethod toEntity(PaymentMethodDTO paymentMethodDTO){
        return modelMapper.map(paymentMethodDTO, PaymentMethod.class);
    }
}
