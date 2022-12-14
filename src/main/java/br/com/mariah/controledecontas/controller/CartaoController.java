package br.com.mariah.controledecontas.controller;

import br.com.mariah.controledecontas.domain.Cartao;
import br.com.mariah.controledecontas.dto.CartaoDTO;
import br.com.mariah.controledecontas.genericcrud.controller.GenericCrudController;
import br.com.mariah.controledecontas.genericcrud.dto.DTOResolver;
import br.com.mariah.controledecontas.genericcrud.report.ExporterHandlerChain;
import br.com.mariah.controledecontas.report.dto.CartaoReportDTO;
import br.com.mariah.controledecontas.service.CartaoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cartao")
public class CartaoController extends GenericCrudController {


    protected CartaoController(final CartaoService cartaoService, final DTOResolver<CartaoDTO, Long, Cartao> dtoResolver, final ExporterHandlerChain exporterHandlerChain) {
        super(cartaoService, dtoResolver, Cartao.class, CartaoDTO.class, CartaoReportDTO.class, exporterHandlerChain);
    }
}
