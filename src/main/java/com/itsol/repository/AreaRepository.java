package com.itsol.repository;

import com.itsol.domain.Area;
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
public interface AreaRepository extends JpaRepository<Area, Long> {

    @Query(nativeQuery = true,
        value = "select *\n" +
            "from area u\n" +
            "where (:name is null or UPPER(:name) like UPPER('%' | u.area_name | '%'))\n" +
            "    and (:code is null or UPPER(:code) like UPPER('%' | u.area_code | '%'))\n" +
            "    and (:region is null or UPPER(:region) like UPPER('%' | u.region_code | '%'))")
    List<Area> cFindBySearch(@Param("name") String name,
                             @Param("code") String code,
                             @Param("region") String region);
}
