package com.itsol.service;

import com.itsol.domain.Control;
import com.itsol.repository.ControlRepository;
import com.itsol.service.dto.ControlDTO;
import com.itsol.service.mapper.ControlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;


@Service
@Transactional
public class ControlService {

    private final Logger log = LoggerFactory.getLogger(ControlService.class);

    private final ControlRepository controlRepository;

    private final ControlMapper controlMapper;

    public ControlService(ControlRepository controlRepository, ControlMapper controlMapper){
        this.controlRepository = controlRepository;
        this.controlMapper = controlMapper;
    }

    public ControlDTO save(ControlDTO controlDTO){
        log.debug("Request to save Control : {}", controlDTO);
        Control control = controlMapper.toEntity(controlDTO);
        control = controlRepository.save(control);
        return controlMapper.toDto(control);
    }

    /**
     * Get all the areas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ControlDTO> findAll(Pageable pageable){
        log.debug("Request to get all Control");
        return controlRepository.findAll(pageable)
            .map(controlMapper::toDto);
    }

    /**
     * Get one area by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ControlDTO> findOne(Long id) {
        log.debug("Request to get Control : {}", id);
        return controlRepository.findById(id)
            .map(controlMapper::toDto);
    }

    /**
     * Delete the area by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Control : {}", id);
        controlRepository.deleteById(id);
    }


}
