package io.toprate.si.repository;

import io.toprate.si.models.Doctor;
import io.toprate.si.models.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    Page<Patient> findAll(Specification<Patient> patientSpecification, Pageable pageable);
}
