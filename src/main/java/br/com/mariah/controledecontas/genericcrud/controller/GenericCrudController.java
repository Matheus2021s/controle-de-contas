package br.com.mariah.controledecontas.genericcrud.controller;

import br.com.mariah.controledecontas.genericcrud.domain.GenericEntity;
import br.com.mariah.controledecontas.genericcrud.dto.DTOResolver;
import br.com.mariah.controledecontas.genericcrud.dto.GenericDTO;
import br.com.mariah.controledecontas.genericcrud.resources.ResourceItem;
import br.com.mariah.controledecontas.genericcrud.resources.ResourceResolver;
import br.com.mariah.controledecontas.genericcrud.service.GenericCrudService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Setter
@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("api/crud/")
public class GenericCrudController {
    private final GenericCrudService genericCrudService;
    private final DTOResolver dtoResolver;


    @PostMapping
    public ResponseEntity<GenericDTO> create(@RequestAttribute("resourceType") ResourceItem resourceItem, @RequestBody String createDTOValues) {
        log.info("Create request to resource : {} ", resourceItem);

        GenericDTO genericCreateDTO = dtoResolver.create(resourceItem, createDTOValues);

        GenericEntity genericEntity = this.genericCrudService.create(resourceItem, genericCreateDTO.toEntity());

        GenericDTO response = dtoResolver.response(resourceItem, genericEntity);

        return ResponseEntity.ok(response);
    }


    @PutMapping
    public ResponseEntity<GenericDTO> update(@RequestAttribute("resourceType") ResourceItem resourceItem, @RequestBody String createDTOValues) {
        log.info("Update request to resource : {} ", resourceItem);

        GenericDTO genericCreateDTO = dtoResolver.update(resourceItem, createDTOValues);

        GenericEntity genericEntity = this.genericCrudService.update(resourceItem, genericCreateDTO.toEntity());

        GenericDTO response = dtoResolver.response(resourceItem, genericEntity);

        return ResponseEntity.ok(response);
    }


    @GetMapping("{id}")
    public ResponseEntity<GenericDTO> findById(@RequestAttribute("resourceType") ResourceItem resourceItem, @PathVariable Long id) {

        GenericDTO genericCreateDTO = dtoResolver.idDto(resourceItem, id);

        GenericEntity genericEntity = this.genericCrudService.findById(resourceItem, genericCreateDTO.toEntity());

        GenericDTO response = dtoResolver.response(resourceItem, genericEntity);

        return ResponseEntity.ok(response);

    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@RequestAttribute("resourceType") ResourceItem resourceItem, @PathVariable Long id) {
        log.info("Delete request to resource : {} ", resourceItem);

        GenericDTO genericCreateDTO = dtoResolver.idDto(resourceItem, id);

        this.genericCrudService.delete(resourceItem, genericCreateDTO.toEntity());

        return ResponseEntity.noContent().build();

    }


    @GetMapping
    public ResponseEntity<Page<GenericDTO>> paginatedList(@RequestAttribute("resourceType") ResourceItem resourceItem, Pageable pageable) {
        log.info("List request to resource : {} ", resourceItem);

        Page<GenericEntity> genericEntityPage = this.genericCrudService.paginatedList(resourceItem, pageable);

        Page<GenericDTO> response = dtoResolver.pageResponse(resourceItem, genericEntityPage);

        return ResponseEntity.ok(response);

    }


}
