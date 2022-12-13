package br.com.mariah.controledecontas.controller;

import br.com.mariah.controledecontas.domain.Banco;
import br.com.mariah.controledecontas.dto.BancoDTO;
import br.com.mariah.controledecontas.genericcrud.controller.GenericCrudController;
import br.com.mariah.controledecontas.genericcrud.dto.DTOResolver;
import br.com.mariah.controledecontas.genericcrud.report.ExporterHandlerChain;
import br.com.mariah.controledecontas.genericcrud.service.GenericCrudService;
import br.com.mariah.controledecontas.persistence.BancoPersistence;
import br.com.mariah.controledecontas.report.dto.BancoReportDTO;
import br.com.mariah.controledecontas.repository.BancoRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("banco")
public class BancoController extends GenericCrudController<Banco, Long, BancoReportDTO, BancoDTO, BancoRepository, BancoPersistence> {


    protected BancoController(GenericCrudService<Banco, Long, BancoRepository, BancoPersistence> genericCrudService, DTOResolver<BancoDTO, Long, Banco> dtoResolver, ExporterHandlerChain exporterHandlerChain) {
        super(genericCrudService, dtoResolver, Banco.class, BancoDTO.class, BancoReportDTO.class, exporterHandlerChain);
    }
}
