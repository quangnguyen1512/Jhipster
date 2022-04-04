package com.itsol.service;

import com.itsol.domain.Area;
import com.itsol.repository.AreaRepository;
import com.itsol.service.dto.AreaDTO;
import com.itsol.service.dto.AreaSearchDTO;
import com.itsol.service.mapper.AreaMapper;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Area}.
 */
@Service
@Transactional
public class AreaService {

    private final Logger log = LoggerFactory.getLogger(AreaService.class);

    private final AreaRepository areaRepository;

    private final AreaMapper areaMapper;

    public AreaService(AreaRepository areaRepository, AreaMapper areaMapper) {
        this.areaRepository = areaRepository;
        this.areaMapper = areaMapper;
    }

    /**
     * Save a area.
     *
     * @param areaDTO the entity to save.
     * @return the persisted entity.
     */
    public AreaDTO save(AreaDTO areaDTO) {
        log.debug("Request to save Area : {}", areaDTO);
        Area area = areaMapper.toEntity(areaDTO);
        area = areaRepository.save(area);
        return areaMapper.toDto(area);
    }

    /**
     * Get all the areas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<AreaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Areas");
        return areaRepository.findAll(pageable)
            .map(areaMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<AreaDTO> findBySearch(AreaSearchDTO dto) {
        log.debug("Request to get all Areas");
        List<Area> areas = areaRepository.cFindBySearch(dto.getName(),
            dto.getCode(),
            dto.getRegion());
        return areas.stream().map(areaMapper::toDto).collect(Collectors.toList());
    }


    /**
     * Get one area by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AreaDTO> findOne(Long id) {
        log.debug("Request to get Area : {}", id);
        return areaRepository.findById(id)
            .map(areaMapper::toDto);
    }

    /**
     * Delete the area by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Area : {}", id);
        areaRepository.deleteById(id);
    }
}
