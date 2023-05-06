package io.toprate.si.repository;

import io.toprate.si.models.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    Page<Doctor> findAll(Specification<Doctor> doctorSpecification, Pageable pageable);
}
