package com.blueTeam.medicalService.entities;

import com.blueTeam.medicalService.entities.enums.PaymentMethod;
import com.blueTeam.medicalService.entities.enums.PaymentType;
import com.blueTeam.medicalService.entities.enums.ReceiptStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
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

    @Enumerated
    private ReceiptStatus status;

    @Column(name = "payment_method")
    @Enumerated
    private PaymentMethod paymentMethod;

    @Column(name = "type")
    @Enumerated
    private PaymentType paymentType;
}
