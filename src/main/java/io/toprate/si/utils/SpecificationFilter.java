package io.toprate.si.utils;


import io.toprate.si.models.Appointment;
import io.toprate.si.models.Doctor;
import io.toprate.si.models.Medicine;
import io.toprate.si.models.Patient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;

@Component
public class SpecificationFilter {



    public Specification<Medicine> create(String name) {
        return (root, cq, cb) -> {
            Predicate p = cb.conjunction();

            if (!StringUtils.isEmpty(name)) {
                p = cb.and(p, cb.like(root.get("name"), "%" + name + "%"));
            }
            return p;
        };
    }

    public Specification<Doctor> doctorSpecification(String name , String gender) {
        return (root, cq, cb) -> {
            Predicate p = cb.conjunction();

            if (!StringUtils.isEmpty(name)) {
                p = cb.and(p, cb.like(root.get("name"), "%" + name + "%"));

            }
            if (!StringUtils.isEmpty(gender)) {
                p = cb.and(p, cb.equal(root.get("gender"), "%" + gender + "%"));
            }
            return p;
        };
    }

    public Specification<Patient> patientSpecification(String name ) {
        return (root, cq, cb) -> {
            Predicate p = cb.conjunction();
            if (!StringUtils.isEmpty(name)) {
                p = cb.and(p, cb.like(root.get("name"), "%" + name + "%"));

            }
            return p;
        };
    }

    public Specification<Appointment> appoinmentSpecification(Integer idDoctor,Integer idPatient) {
        return (root, cq, cb) -> {
            Predicate p = cb.conjunction();

            if (idDoctor!=0) {
                p = cb.and(p, cb.equal(root.get("idDoctor"), idDoctor));
            }
            if (idPatient!=0) {
                p = cb.and(p, cb.equal(root.get("idPatient"), idPatient));
            }
            return p;
        };
    }
}

