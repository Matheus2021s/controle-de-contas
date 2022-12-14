package br.com.mariah.controledecontas.controller;

import br.com.mariah.controledecontas.domain.Banco;
import br.com.mariah.controledecontas.dto.BancoDTO;
import br.com.mariah.controledecontas.genericcrud.controller.GenericCrudController;
import br.com.mariah.controledecontas.genericcrud.dto.DTOResolver;
import br.com.mariah.controledecontas.genericcrud.report.ExporterHandlerChain;
import br.com.mariah.controledecontas.report.dto.BancoReportDTO;
import br.com.mariah.controledecontas.service.BancoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("banco")
public class BancoController extends GenericCrudController{


    protected BancoController(final BancoService bancoService, DTOResolver<BancoDTO, Long, Banco> dtoResolver, ExporterHandlerChain exporterHandlerChain) {
        super(bancoService, dtoResolver, Banco.class, BancoDTO.class, BancoReportDTO.class, exporterHandlerChain);
    }
}
