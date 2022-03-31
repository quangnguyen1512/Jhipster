package com.itsol.repository;

import com.itsol.domain.Area;
import com.itsol.domain.Control;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Area entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ControlRepository extends JpaRepository<Control, Long> {

    @Query(nativeQuery = true,
        value = "select * from area where area_name like '%' || :control_name || '%'")
    List<Area> cFindByName(@Param("control_name") String controlName);
}
