package br.com.mariah.controledecontas.controller;

import br.com.mariah.controledecontas.domain.Emprestimo;
import br.com.mariah.controledecontas.dto.EmprestimoDTO;
import br.com.mariah.controledecontas.genericcrud.controller.GenericCrudController;
import br.com.mariah.controledecontas.genericcrud.dto.DTOResolver;
import br.com.mariah.controledecontas.genericcrud.report.ExporterHandlerChain;
import br.com.mariah.controledecontas.report.dto.EmprestimoReportDTO;
import br.com.mariah.controledecontas.service.EmprestimoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("emprestimo")
public class EmprestimoController extends GenericCrudController {

    protected EmprestimoController(EmprestimoService emprestimoService, DTOResolver<EmprestimoDTO, Long, Emprestimo> dtoResolver, ExporterHandlerChain exporterHandlerChain) {
        super(emprestimoService, dtoResolver, Emprestimo.class, EmprestimoDTO.class, EmprestimoReportDTO.class, exporterHandlerChain);
    }


}
