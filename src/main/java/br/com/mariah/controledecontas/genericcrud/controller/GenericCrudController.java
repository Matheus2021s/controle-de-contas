package br.com.mariah.controledecontas.genericcrud.controller;

import br.com.mariah.controledecontas.genericcrud.domain.GenericEntity;
import br.com.mariah.controledecontas.genericcrud.dto.DTOResolver;
import br.com.mariah.controledecontas.genericcrud.dto.GenericDTO;
import br.com.mariah.controledecontas.genericcrud.persistence.GenericCrudPersistence;
import br.com.mariah.controledecontas.genericcrud.report.ExporterHandlerChain;
import br.com.mariah.controledecontas.genericcrud.report.GenericReportDTO;
import br.com.mariah.controledecontas.genericcrud.service.GenericCrudService;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Setter
@Log4j2
public abstract class GenericCrudController<E extends GenericEntity<ID>, ID, G extends GenericReportDTO, R extends GenericDTO<E, ID>, T extends JpaRepository<E, ID>, P extends GenericCrudPersistence<E, ID, T>> extends GenericReportable<E, ID,G, T, P> {
    private final GenericCrudService<E, ID, T, P> genericCrudService;
    private final DTOResolver<R, ID, E> dtoResolver;
    private final Class<E> entityClass;
    private final Class<R> dtoClass;

    private final Class<G> reportDTO;

    private final ExporterHandlerChain exporterHandlerChain;

    protected GenericCrudController(GenericCrudService<E, ID, T, P> genericCrudService, DTOResolver<R, ID, E> dtoResolver, Class<E> entityClass, Class<R> dtoClass, Class<G> reportDTO, ExporterHandlerChain exporterHandlerChain) {
        super(genericCrudService, entityClass,reportDTO, exporterHandlerChain);
        this.genericCrudService = genericCrudService;
        this.dtoResolver = dtoResolver;
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
        this.reportDTO = reportDTO;
        this.exporterHandlerChain = exporterHandlerChain;
    }


    @PostMapping
    public ResponseEntity<R> create(@RequestBody String createDTOValues) {
        log.info("Create request to resource : {} ", entityClass.getSimpleName());

        R genericCreateDTO = dtoResolver.create(dtoClass, createDTOValues);

        E genericEntity = this.genericCrudService.create(genericCreateDTO.toEntity());

        R response = dtoResolver.response(dtoClass, genericEntity);

        return ResponseEntity.ok(response);
    }


    @PutMapping
    public ResponseEntity<R> update(@RequestBody String createDTOValues) {
        log.info("Update request to resource : {} ", entityClass.getSimpleName());

        R genericCreateDTO = dtoResolver.update(dtoClass, createDTOValues);

        E genericEntity = this.genericCrudService.update(genericCreateDTO.toEntity());

        R response = dtoResolver.response(dtoClass, genericEntity);

        return ResponseEntity.ok(response);
    }


    @GetMapping("{id}")
    public ResponseEntity<R> findById(@PathVariable Long id) {
        log.info("Find by id request to resource : \"{}\" id : {} ", entityClass.getSimpleName(), id);

        R genericCreateDTO = dtoResolver.idDto(dtoClass, id);

        E genericEntity = this.genericCrudService.findById(genericCreateDTO.toEntity());

        R response = dtoResolver.response(dtoClass, genericEntity);

        return ResponseEntity.ok(response);

    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        log.info("Delete by id request to resource : \"{}\" id : {} ", entityClass.getSimpleName(), id);

        R genericCreateDTO = dtoResolver.idDto(dtoClass, id);

        this.genericCrudService.delete(genericCreateDTO.toEntity());

        return ResponseEntity.noContent().build();

    }


    @GetMapping
    public ResponseEntity<Page<R>> paginatedList(Pageable pageable) {
        log.info("List request to resource : {} ", entityClass.getSimpleName());

        Page<E> genericEntityPage = this.genericCrudService.paginatedList(pageable);

        Page<R> response = dtoResolver.pageResponse(dtoClass, genericEntityPage);

        return ResponseEntity.ok(response);

    }


}
