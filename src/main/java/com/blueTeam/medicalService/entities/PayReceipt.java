package com.blueTeam.medicalService.entities;

import com.blueTeam.medicalService.entities.enums.PaymentMethod;
import com.blueTeam.medicalService.entities.enums.PaymentType;
import com.blueTeam.medicalService.entities.enums.ReceiptStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "pay_receipt")
public class PayReceipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_doctor_id", referencedColumnName = "id")
    private DoctorsAppointment doctorsAppointment;

    private BigDecimal value;

    private ReceiptStatus status;

    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    @Column(name = "type")
    private PaymentType paymentType;
}
