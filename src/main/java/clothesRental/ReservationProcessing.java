package clothesRental;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="ReservationProcessing_table")
public class ReservationProcessing {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String productId;
    private String customerNm;
    private Integer qty;
    private String productStatus;
    private String orderId;
    private String address;

    @PostPersist
    public void onPostPersist(){
        Reserved reserved = new Reserved();
        BeanUtils.copyProperties(this, reserved);
        reserved.publish();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

//        clothesRental.external.ReservationProcessing reservationProcessing = new clothesRental.external.ReservationProcessing();
//        // mappings goes here
//        Application.applicationContext.getBean(clothesRental.external.ReservationProcessingService.class)
//            .payment(reservationProcessing);
//        clothesRental.external.PaymentProcessing paymentProcessing = new clothesRental.external.PaymentProcessing();
//        Application.applicationContext.getBean(clothesRental.external.PaymentProcessingService.class)
//                .payment(paymentProcessing);



    }

    @PostUpdate
    public void onPostUpdate(){
        ReserveChanged reserveChanged = new ReserveChanged();
        BeanUtils.copyProperties(this, reserveChanged);
        reserveChanged.publish();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

//        clothesRental.external.ReservationProcessing reservationProcessing = new clothesRental.external.ReservationProcessing();
//        // mappings goes here
//        Application.applicationContext.getBean(clothesRental.external.ReservationProcessingService.class)
//            .deliveryCalcel(reservationProcessing);


    }

    @PostRemove
    public void onPostRemove(){
        ReserveCanceled reserveCanceled = new ReserveCanceled();
        BeanUtils.copyProperties(this, reserveCanceled);
        reserveCanceled.publish();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.
//
//        clothesRental.external.ReservationProcessing reservationProcessing = new clothesRental.external.ReservationProcessing();
//        // mappings goes here
//        Application.applicationContext.getBean(clothesRental.external.ReservationProcessingService.class)
//            .deliveryCalcel(reservationProcessing);


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
    public String getCustomerNm() {
        return customerNm;
    }

    public void setCustomerNm(String customerNm) {
        this.customerNm = customerNm;
    }
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }




}
