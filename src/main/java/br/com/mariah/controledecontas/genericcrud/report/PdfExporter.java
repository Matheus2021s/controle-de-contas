package br.com.mariah.controledecontas.genericcrud.report;

import br.com.mariah.controledecontas.genericcrud.domain.GenericEntity;
import br.com.mariah.controledecontas.genericcrud.report.helper.JRXMLHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.OutputStream;
import java.util.Map;

import static br.com.mariah.controledecontas.genericcrud.report.ExporterType.PDF;

@RequiredArgsConstructor
@Log4j2
@Component
public class PdfExporter implements ExporterHandler {

    private static final String RESOURCE_REPORT_PATH_PLACEHOLDER = "src/main/resources/reports/%s.jrxml";
    private static final String CLASSPATH_REPORT_PATH_PLACEHOLDER = "classpath:reports/%s.jrxml";
    private final JRXMLHelper jrxmlHelper;

    @Override
    public Boolean isHandle(ExporterType exporterType) {
        return exporterType.equals(PDF);
    }

    @Override
    public void execute(Map<String, Object> parameters, Class<? extends GenericEntity> entityClass, Class<? extends GenericReportDTO> reportDTO, OutputStream outputStream, JRBeanCollectionDataSource dataSource) {
        String reportName = entityClass.getSimpleName();

        log.info("Generating pdf {}", reportName);

        try {
            File file = new File(String.format(CLASSPATH_REPORT_PATH_PLACEHOLDER, reportName));
            if (!file.exists()) {
                file = new File(String.format(RESOURCE_REPORT_PATH_PLACEHOLDER, reportName));
                if (!file.exists()) {
                    jrxmlHelper.generateClass(entityClass, reportDTO);
                    file = new File(String.format(RESOURCE_REPORT_PATH_PLACEHOLDER, reportName));
                }
            }


            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
}
