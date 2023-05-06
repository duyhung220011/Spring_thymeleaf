package io.toprate.si.repository;

import io.toprate.si.models.Medicine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
    Page<Medicine> findAll(Specification<Medicine> productSpecification, Pageable pageable);
}
