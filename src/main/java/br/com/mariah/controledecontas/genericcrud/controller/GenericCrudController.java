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
@RequestMapping("api/crud")
public class GenericCrudController {
    private final GenericCrudService genericCrudService;
    private final ResourceResolver resourceResolver;
    private final DTOResolver dtoResolver;


    @PostMapping("/{resource}")
    public ResponseEntity<GenericDTO> create(@PathVariable String resource, @RequestBody String createDTOValues) {
        log.info("Create request to resource : {} ", resource);
        ResourceItem resolvedResource = resourceResolver.resolve(resource);

        GenericDTO genericCreateDTO = dtoResolver.create(resolvedResource, createDTOValues);

        GenericEntity genericEntity = this.genericCrudService.create(resolvedResource, genericCreateDTO.toEntity());

        GenericDTO response = dtoResolver.response(resolvedResource, genericEntity);

        return ResponseEntity.ok(response);
    }


    @PutMapping("/{resource}")
    public ResponseEntity<GenericDTO> update(@PathVariable String resource, @RequestBody String createDTOValues) {
        log.info("Update request to resource : {} ", resource);
        ResourceItem resolvedResource = resourceResolver.resolve(resource);

        GenericDTO genericCreateDTO = dtoResolver.update(resolvedResource, createDTOValues);

        GenericEntity genericEntity = this.genericCrudService.update(resolvedResource, genericCreateDTO.toEntity());

        GenericDTO response = dtoResolver.response(resolvedResource, genericEntity);

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{resource}/{id}")
    public ResponseEntity<GenericDTO> findById(@PathVariable String resource, @PathVariable Long id) {

        ResourceItem resolvedResource = resourceResolver.resolve(resource);

        GenericDTO genericCreateDTO = dtoResolver.idDto(resolvedResource, id);

        GenericEntity genericEntity = this.genericCrudService.findById(resolvedResource, genericCreateDTO.toEntity());

        GenericDTO response = dtoResolver.response(resolvedResource, genericEntity);

        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/{resource}/{id}")
    public ResponseEntity delete(@PathVariable String resource, @PathVariable Long id) {

        ResourceItem resolvedResource = resourceResolver.resolve(resource);

        GenericDTO genericCreateDTO = dtoResolver.idDto(resolvedResource, id);

        this.genericCrudService.delete(resolvedResource, genericCreateDTO.toEntity());

        return ResponseEntity.noContent().build();

    }


    @GetMapping("/{resource}")
    public ResponseEntity<Page<GenericDTO>> paginatedList(@PathVariable String resource, Pageable pageable) {

        ResourceItem resolvedResource = resourceResolver.resolve(resource);

        Page<GenericEntity> genericEntityPage = this.genericCrudService.paginatedList(resolvedResource, pageable);

        Page<GenericDTO> response = dtoResolver.pageResponse(resolvedResource, genericEntityPage);

        return ResponseEntity.ok(response);

    }


}
