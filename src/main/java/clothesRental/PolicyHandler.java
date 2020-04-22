package clothesRental;

import clothesRental.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{

    @Autowired
    ReservationProcessingRepository reservationProcessingRepository;
    ReservationProcessing reservationProcessing = new ReservationProcessing();

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentCanceled_ReserveCancel(@Payload PaymentCanceled paymentCanceled){


        if(paymentCanceled.isMe()){
            System.out.println("##### listener ReserveCancel : " + paymentCanceled.toJson());
            reservationProcessing.setProductStatus("Available");
            reservationProcessingRepository.save(reservationProcessing);
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDeliveryCanceled_ReserveCancel(@Payload DeliveryCanceled deliveryCanceled){


        if(deliveryCanceled.isMe()){
            reservationProcessing.setProductStatus("Available");
            reservationProcessingRepository.save(reservationProcessing);
            System.out.println("##### listener ReserveCancel : " + deliveryCanceled.toJson());
        }
    }

}
