package com.itsol.web.rest;

import com.itsol.domain.Control;
import com.itsol.service.ControlService;
import com.itsol.service.dto.ControlDTO;
import com.itsol.web.rest.errors.BadRequestAlertException;

import com.zaxxer.hikari.pool.HikariProxyConnection;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.sql.*;

/**
 * REST controller for managing {@link Control}.
 */
@RestController
@RequestMapping("/api")
public class ControlResource {

    private final Logger log = LoggerFactory.getLogger(AreaResource.class);

    private static final String ENTITY_NAME = "control";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ControlService controlService;

    public ControlResource(ControlService controlService) {
        this.controlService = controlService;
    }

    /**
     * {@code POST  /areas} : Create a new area.
     *
     * @param controlDTO the areaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new areaDTO, or with status {@code 400 (Bad Request)} if the area has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/controls")
    public ResponseEntity<ControlDTO> CreateControl(@RequestBody ControlDTO controlDTO) throws URISyntaxException {
        log.debug("REST request to save Control : {}", controlDTO);
        if (controlDTO.getId() != null) {
            throw new BadRequestAlertException("A new control cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ControlDTO result = controlService.save(controlDTO);
        return ResponseEntity.created(new URI("/api/controls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /areas} : Updates an existing area.
     *
     * @param controlDTO the areaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated areaDTO,
     * or with status {@code 400 (Bad Request)} if the areaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the areaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/controls")
    public ResponseEntity<ControlDTO> updateControl(@RequestBody ControlDTO controlDTO) throws URISyntaxException {
        log.debug("REST request to update Control : {}", controlDTO);
        if (controlDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ControlDTO result = controlService.save(controlDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, controlDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /areas} : get all the areas.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of areas in body.
     */
    @GetMapping("/controls")
    public ResponseEntity<List<ControlDTO>> getAllControls(Pageable pageable) {
        log.debug("REST request to get a page of Controls");
        Page<ControlDTO> page = controlService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /areas/:id} : get the "id" area.
     *
     * @param id the id of the areaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the areaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/controls/{id}")
    public ResponseEntity<ControlDTO> getControl(@PathVariable Long id) {
        log.debug("REST request to get Control : {}", id);
        Optional<ControlDTO> controlDTO = controlService.findOne(id);
        return ResponseUtil.wrapOrNotFound(controlDTO);
    }

    /**
     * {@code DELETE  /areas/:id} : delete the "id" area.
     *
     * @param id the id of the areaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/controls/{id}")
    public ResponseEntity<Void> deleteControl(@PathVariable Long id) {
        log.debug("REST request to delete Control : {}", id);
        controlService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

}
