package br.com.mariah.controledecontas.genericcrud.controller;

import br.com.mariah.controledecontas.genericcrud.domain.GenericEntity;
import br.com.mariah.controledecontas.genericcrud.persistence.GenericCrudPersistence;
import br.com.mariah.controledecontas.genericcrud.report.ExporterHandlerChain;
import br.com.mariah.controledecontas.genericcrud.report.ExporterType;
import br.com.mariah.controledecontas.genericcrud.report.GenericReportDTO;
import br.com.mariah.controledecontas.genericcrud.service.GenericCrudService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Setter
@RequiredArgsConstructor
@Log4j2
public class GenericReportable<E extends GenericEntity, ID, G extends GenericReportDTO, T extends JpaRepository<E, ID>, P extends GenericCrudPersistence<E, ID, T>> {

    public static final String CONTENT_DISPOSITION = "Content-Disposition";
    public static final String APPLICATION_X_DOWNLOAD = "application/x-download";
    public static final String ATTACHMENT_FILENAME_S_PDF = "attachment; filename=\"%s.pdf\"";
    public static final String REPORT_PDF = "report/pdf";
    private final GenericCrudService<E, ID, T, P> genericCrudService;
    private final Class<E> entityClass;
    private final Class<G> reportDTOClass;
    private final ExporterHandlerChain exporterHandlerChain;

    @SneakyThrows
    @GetMapping(REPORT_PDF)
    public void exportPDF(HttpServletResponse httpServletResponse, Pageable pageable) {

        Map<String, Object> parameters = new HashMap<>();

        List<G> collect = genericCrudService.paginatedList(pageable).get()
                .map(e -> (G) BeanUtils.instantiateClass(reportDTOClass).from(e))
                .collect(Collectors.toList());

        httpServletResponse.setContentType(APPLICATION_X_DOWNLOAD);

        httpServletResponse.setHeader(CONTENT_DISPOSITION, String.format(ATTACHMENT_FILENAME_S_PDF, entityClass.getSimpleName()));

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(collect);

        try {
            exporterHandlerChain.execute(ExporterType.PDF, parameters, entityClass, reportDTOClass, httpServletResponse.getOutputStream(), dataSource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
