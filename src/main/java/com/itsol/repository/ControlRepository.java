package com.itsol.repository;

import com.itsol.domain.Control;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Area entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ControlRepository extends JpaRepository<Control, Long> {
}
