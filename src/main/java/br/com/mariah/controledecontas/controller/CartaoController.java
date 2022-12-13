package br.com.mariah.controledecontas.controller;

import br.com.mariah.controledecontas.domain.Cartao;
import br.com.mariah.controledecontas.dto.CartaoDTO;
import br.com.mariah.controledecontas.genericcrud.controller.GenericCrudController;
import br.com.mariah.controledecontas.genericcrud.dto.DTOResolver;
import br.com.mariah.controledecontas.genericcrud.report.ExporterHandlerChain;
import br.com.mariah.controledecontas.genericcrud.service.GenericCrudService;
import br.com.mariah.controledecontas.persistence.CartaoPersistence;
import br.com.mariah.controledecontas.report.dto.CartaoReportDTO;
import br.com.mariah.controledecontas.repository.CartaoRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cartao")
public class CartaoController extends GenericCrudController<Cartao, Long, CartaoReportDTO, CartaoDTO, CartaoRepository, CartaoPersistence> {


    public CartaoController(GenericCrudService<Cartao, Long, CartaoRepository, CartaoPersistence> genericCrudService, DTOResolver<CartaoDTO, Long, Cartao> dtoResolver, ExporterHandlerChain exporterHandlerChain) {
        super(genericCrudService, dtoResolver, Cartao.class, CartaoDTO.class, CartaoReportDTO.class, exporterHandlerChain);
    }


}
