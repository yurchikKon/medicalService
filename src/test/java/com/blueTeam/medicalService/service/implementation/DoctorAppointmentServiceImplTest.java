package com.blueTeam.medicalService.service.implementation;

import com.blueTeam.medicalService.dto.user.doctor.DoctorRepresentationDto;
import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentRepresentationDto;
import com.blueTeam.medicalService.entity.Doctor;
import com.blueTeam.medicalService.entity.DoctorAppointment;
import com.blueTeam.medicalService.entity.DoctorTimetable;
import com.blueTeam.medicalService.entity.Patient;
import com.blueTeam.medicalService.entity.enums.Status;
import com.blueTeam.medicalService.exception.InvalidStateException;
import com.blueTeam.medicalService.exception.ResourceAlreadyExistException;
import com.blueTeam.medicalService.mapper.DoctorAppointmentMapper;
import com.blueTeam.medicalService.mapper.DoctorMapper;
import com.blueTeam.medicalService.repository.DoctorAppointmentRepository;
import com.blueTeam.medicalService.repository.DoctorRepository;
import com.blueTeam.medicalService.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static com.blueTeam.medicalService.entity.enums.Notification.PLANED;
import static com.blueTeam.medicalService.entity.enums.Status.*;
import static java.time.LocalTime.of;
import static java.util.List.of;
import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DoctorAppointmentServiceImplTest {
    private static final LocalDate DATE = LocalDate.now().plusDays(1);
    private static final LocalDateTime DATE_TIME = LocalDateTime.of(LocalDate.now().plusDays(1),
            LocalTime.of(13, 30, 0));
    private static final DoctorAppointmentRepresentationDto FREE_APPOINTMENT_DTO = createAppointmentDto(1L, DATE_TIME);
    private static final DoctorAppointmentRepresentationDto SCHEDULED_APPOINTMENT_DTO = createAppointmentDto(1L,
            1L, DATE_TIME, SCHEDULED);
    private static final DoctorAppointmentRepresentationDto CANCELED_APPOINTMENT_DTO = createAppointmentDto(1L,
            1L, null, CANCELED);
    private static final Doctor DOCTOR = createDoctor(1L, DATE);
    private static final Patient PATIENT = createPatient(1L);
    private static final DoctorAppointment APPOINTMENT = createAppointment(1L, SCHEDULED);
    private static final DoctorAppointment APPOINTMENT_CANCELED = createAppointment(1L, CANCELED);
    private static final DoctorAppointment APPOINTMENT_COMPLETED = createAppointment(1L, COMPLETED);
    private static final DoctorAppointment APPOINTMENT_TIMED_SCHEDULED = new DoctorAppointment(null, DOCTOR, PATIENT,
            LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(13, 30, 0)), SCHEDULED,
            PLANED, null, null, null, null, null,
            null, null, null);
    private static final DoctorAppointment APPOINTMENT_TIMED = createAppointment(1L,
            LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(11, 0, 0)),
            null, null);
    private static final Long DOCTOR_ID = 1L;
    private static final Long PATIENT_ID = 1L;
    private static final Long APPOINTMENT_ID = 1L;
    private static final List<DoctorAppointment> APPOINTMENT_LIST = fillAppointmentList(DATE);

    @InjectMocks
    DoctorAppointmentServiceImpl doctorAppointmentService;

    @Mock
    DoctorAppointmentRepository doctorAppointmentRepository;

    @Mock
    DoctorRepository doctorRepository;

    @Mock
    PatientRepository patientRepository;

    @Mock
    DoctorAppointmentMapper doctorAppointmentMapper;

    @Mock
    DoctorMapper doctorMapper;

    @Test
    void findAllScheduledByDoctorIdAndDate_doctorExist() {
        when(doctorRepository.existsById(DOCTOR_ID)).thenReturn(true);
        when(doctorAppointmentRepository.findAllScheduledByDoctorIdAndDate(DOCTOR_ID, DATE)).thenReturn(of(APPOINTMENT));
        when(doctorAppointmentMapper.mapToDto(APPOINTMENT)).thenReturn(SCHEDULED_APPOINTMENT_DTO);

        assertEquals(of(SCHEDULED_APPOINTMENT_DTO), doctorAppointmentService.findAllScheduledByDoctorIdAndDate(DOCTOR_ID, DATE));
    }

    @Test
    void findAllScheduledByDoctorIdAndDate_doctorNotExist() {
        when(doctorRepository.existsById(DOCTOR_ID)).thenReturn(false);

        assertThrows(EntityNotFoundException.class,
                () -> doctorAppointmentService.findAllScheduledByDoctorIdAndDate(DOCTOR_ID, DATE));
    }

    @Test
    void findAllFreeAppointmentsByDoctorIdAndDate() {
        when(doctorRepository.findById(DOCTOR_ID)).thenReturn(Optional.of(DOCTOR));
        when(doctorAppointmentRepository.findAllScheduledByDoctorIdAndDate(DOCTOR_ID, DATE)).thenReturn(APPOINTMENT_LIST);

        assertEquals(of(FREE_APPOINTMENT_DTO), doctorAppointmentService.findAllFreeAppointmentsByDoctorIdAndDate(DOCTOR_ID, DATE));
    }

    @Test
    void createAppointment_validData() {
        when(doctorRepository.findById(DOCTOR_ID)).thenReturn(Optional.of(DOCTOR));
        when(patientRepository.findById(PATIENT_ID)).thenReturn(Optional.of(PATIENT));
        when(doctorAppointmentRepository.findAllScheduledByDoctorIdAndDate(DOCTOR_ID, DATE)).thenReturn(of(APPOINTMENT_TIMED));
        when(doctorAppointmentMapper.mapToDto(APPOINTMENT_TIMED_SCHEDULED)).thenReturn(SCHEDULED_APPOINTMENT_DTO);

        assertEquals(SCHEDULED_APPOINTMENT_DTO, doctorAppointmentService.createAppointment(PATIENT_ID, DOCTOR_ID, DATE_TIME));
    }

    @Test
    void createAppointment_doctorNotFound() {
        when(doctorRepository.findById(DOCTOR_ID)).thenReturn(empty());

        assertThrows(EntityNotFoundException.class,
                () -> doctorAppointmentService.createAppointment(PATIENT_ID, DOCTOR_ID, DATE_TIME));
    }

    @Test
    void createAppointment_patientNotFound() {
        when(doctorRepository.findById(DOCTOR_ID)).thenReturn(Optional.of(DOCTOR));
        when(patientRepository.findById(PATIENT_ID)).thenReturn(empty());

        assertThrows(EntityNotFoundException.class,
                () -> doctorAppointmentService.createAppointment(PATIENT_ID, DOCTOR_ID, DATE_TIME));
    }

    @Test
    void createAppointment_alreadyScheduledTime() {
        when(doctorRepository.findById(DOCTOR_ID)).thenReturn(Optional.of(DOCTOR));
        when(patientRepository.findById(PATIENT_ID)).thenReturn(Optional.of(PATIENT));
        when(doctorAppointmentRepository.findAllScheduledByDoctorIdAndDate(DOCTOR_ID, DATE)).thenReturn(of(APPOINTMENT_TIMED_SCHEDULED));

        assertThrows(ResourceAlreadyExistException.class,
                () -> doctorAppointmentService.createAppointment(PATIENT_ID, DOCTOR_ID, DATE_TIME));
    }

    @Test
    void cancelAppointment_appointmentFound() {
        when(doctorAppointmentRepository.findById(APPOINTMENT_ID)).thenReturn(Optional.of(APPOINTMENT));
        when(doctorAppointmentMapper.mapToDto(APPOINTMENT_CANCELED)).thenReturn(CANCELED_APPOINTMENT_DTO);

        assertEquals(CANCELED_APPOINTMENT_DTO, doctorAppointmentService.cancelAppointment(APPOINTMENT_ID));
    }

    @Test
    void cancelAppointment_AppointmentNotFound() {
        when(doctorAppointmentRepository.findById(APPOINTMENT_ID)).thenReturn(empty());

        assertThrows(EntityNotFoundException.class,
                () -> doctorAppointmentService.cancelAppointment(APPOINTMENT_ID));
    }

    @Test
    void cancelAppointment_completedAppointment() {
        when(doctorAppointmentRepository.findById(APPOINTMENT_ID)).thenReturn(Optional.of(APPOINTMENT_COMPLETED));

        assertThrows(InvalidStateException.class,
                () -> doctorAppointmentService.cancelAppointment(APPOINTMENT_ID));
    }

    private static List<DoctorAppointment> fillAppointmentList(LocalDate date) {
        List<DoctorAppointment> dtoList = new ArrayList<>();
        LocalTime time = of(9, 0, 0);
        for (int i = 0; i < 9; i++) {
            dtoList.add(createAppointment(DOCTOR_ID, LocalDateTime.of(date, time.plusMinutes(30 * i)), DOCTOR, PATIENT));
        }

        return dtoList;
    }

    private static DoctorRepresentationDto createDoctorDto(Long id) {
        return DoctorRepresentationDto.builder()
                .id(id)
                .build();
    }

    private static DoctorAppointment createAppointment(Long id, Status status) {
        return DoctorAppointment.builder()
                .id(id)
                .status(status)
                .build();
    }

    private static DoctorAppointment createAppointment(Long id, LocalDateTime dateTime, Doctor doctor, Patient patient) {
        return DoctorAppointment.builder()
                .id(id)
                .doctor(doctor)
                .patient(patient)
                .dateTime(dateTime)
                .build();
    }

    private static DoctorTimetable createTimetable(LocalDate date) {
        return DoctorTimetable.builder()
                .dayOfWeek(date.getDayOfWeek())
                .timeStart(of(9, 0, 0))
                .timeEnd(of(14, 0, 0))
                .build();
    }

    private static Doctor createDoctor(Long id, LocalDate date) {
        Doctor doctor = new Doctor();
        doctor.setTimetable(of(createTimetable(date)));
        doctor.setId(id);

        return doctor;
    }

    private static Patient createPatient(Long id) {
        Patient patient = new Patient();
        patient.setId(id);

        return patient;
    }

    private static DoctorAppointmentRepresentationDto createAppointmentDto(Long doctorId, LocalDateTime dateTime) {
        return DoctorAppointmentRepresentationDto.builder()
                .dateTime(dateTime)
                .build();
    }

    private static DoctorAppointmentRepresentationDto createAppointmentDto(Long doctorId,
                                                                           Long id,
                                                                           LocalDateTime dateTime,
                                                                           Status status) {
        return DoctorAppointmentRepresentationDto.builder()
                .doctorDto(createDoctorDto(doctorId))
                .id(id)
                .dateTime(dateTime)
                .status(status)
                .build();
    }
}