package com.blueTeam.medicalService.models;

import com.blueTeam.medicalService.enums.PaymentMethod;
import com.blueTeam.medicalService.enums.PaymentType;
import com.blueTeam.medicalService.enums.ReceiptStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "pay_receipt")
public class PayReceipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "appointment_doctor_id", referencedColumnName = "id")
    private DoctorsAppointment doctorsAppointment;

    private long value;

    private ReceiptStatus status;

    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    @Column(name = "type")
    private PaymentType paymentType;
}
