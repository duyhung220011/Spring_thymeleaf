package io.toprate.si.repository;

import io.toprate.si.models.Cart;
import io.toprate.si.models.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Integer> {
}
