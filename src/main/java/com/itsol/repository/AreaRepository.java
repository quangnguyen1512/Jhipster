package com.itsol.repository;

import com.itsol.domain.Area;

import com.itsol.service.dto.AreaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query(value="select * from area u where u.area_name like %:keySearch% or u.area_code like %:keySearch% or u.region_code like %:keySearch%", nativeQuery=true)
    List<Area> cFindBySearch(@Param("keySearch") String keySearch);

    @Query(value="select * from area u where u.area_name like %:keySearch% or u.area_code like %:keySearch% or u.region_code like %:keySearch%", nativeQuery=true)
    Page<Area> cFindBySearch(@Param("keySearch") String keySearch, Pageable pageable);
}
