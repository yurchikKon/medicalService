package com.blueTeam.medicalService.entity;

import com.blueTeam.medicalService.entity.enums.PaymentMethod;
import com.blueTeam.medicalService.entity.enums.PaymentType;
import com.blueTeam.medicalService.entity.enums.ReceiptStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder
@Table(name = "pay_receipt")
public class PayReceipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_doctor_id", referencedColumnName = "id")
    private DoctorAppointment doctorsAppointment;

    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    private ReceiptStatus status;

    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
}
