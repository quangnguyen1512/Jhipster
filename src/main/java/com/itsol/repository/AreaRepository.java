package com.itsol.repository;

import com.itsol.domain.Area;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Area entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {

    @Query(nativeQuery = true,
        value = "select * from area where area_name like '%' || :area_name || '%'")
    List<Area> cFindByName(@Param("area_name") String areaName);

}
