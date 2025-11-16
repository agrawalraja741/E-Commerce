package com.raja.paymentservice.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Payment extends  BaseEntity{

    private PaymentStatus paymentStatus;

}
