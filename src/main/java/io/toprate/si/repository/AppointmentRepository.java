package io.toprate.si.repository;

import io.toprate.si.models.Appointment;
import io.toprate.si.models.Cart;
import io.toprate.si.models.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    Page<Appointment> findAll(Specification<Appointment> documentAppoinmentSpecification, Pageable pageable);
}
