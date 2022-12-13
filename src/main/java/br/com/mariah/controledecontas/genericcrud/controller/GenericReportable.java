package br.com.mariah.controledecontas.genericcrud.controller;

import br.com.mariah.controledecontas.genericcrud.domain.GenericEntity;
import br.com.mariah.controledecontas.genericcrud.persistence.GenericCrudPersistence;
import br.com.mariah.controledecontas.genericcrud.report.ExporterHandlerChain;
import br.com.mariah.controledecontas.genericcrud.report.ExporterType;
import br.com.mariah.controledecontas.genericcrud.report.GenericReportDTO;
import br.com.mariah.controledecontas.genericcrud.service.GenericCrudService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Log4j2
public class GenericReportable<E extends GenericEntity, ID, G extends GenericReportDTO, T extends JpaRepository<E, ID>, P extends GenericCrudPersistence<E, ID, T>> {

    private final GenericCrudService<E, ID, T, P> genericCrudService;

    private final Class<E> entityClass;

    private final Class<G> reportDTOClass;
    private final ExporterHandlerChain exporterHandlerChain;

    @SneakyThrows
    @GetMapping("report")
    public void export(HttpServletResponse httpServletResponse, Pageable pageable) {

        Page<E> paginatedList = genericCrudService.paginatedList(pageable);

        Map<String, Object> parameters = new HashMap<>();

        List<G> collect = paginatedList.get().map(e -> (G) BeanUtils.instantiateClass(reportDTOClass).from(e)).collect(Collectors.toList());


        httpServletResponse.setContentType("application/x-download");
        httpServletResponse.setHeader("Content-Disposition", String.format("attachment; filename=\"%s.pdf\"", entityClass.getSimpleName()));

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(collect);
        try {
            exporterHandlerChain.execute(ExporterType.PDF, parameters, entityClass, reportDTOClass, httpServletResponse.getOutputStream(), dataSource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
