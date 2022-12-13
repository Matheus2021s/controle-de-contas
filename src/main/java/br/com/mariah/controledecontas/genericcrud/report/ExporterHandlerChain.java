package br.com.mariah.controledecontas.genericcrud.report;

import br.com.mariah.controledecontas.genericcrud.collection.Collection;
import br.com.mariah.controledecontas.genericcrud.domain.GenericEntity;
import jakarta.servlet.ServletOutputStream;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ExporterHandlerChain extends Collection<ExporterHandler> {


    public ExporterHandlerChain(final List<ExporterHandler> list) {
        super(list);
    }

    public void execute(ExporterType exporterType, Map<String, Object> parameters, Class<? extends GenericEntity> entityClass, Class<? extends GenericReportDTO> reportDTO, ServletOutputStream outputStream, JRBeanCollectionDataSource dataSource) {
        getList().stream().filter(exporterHandler -> exporterHandler.isHandle(exporterType))
                .forEach(exporterHandler -> exporterHandler.execute(parameters, entityClass, reportDTO, outputStream, dataSource));
    }
}
