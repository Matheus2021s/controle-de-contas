package br.com.mariah.controledecontas.genericcrud.report;

import br.com.mariah.controledecontas.genericcrud.collection.CollectionTarget;
import br.com.mariah.controledecontas.genericcrud.domain.GenericEntity;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.OutputStream;
import java.util.Map;

public interface ExporterHandler extends CollectionTarget {

    Boolean isHandle(ExporterType exporterType);

    void execute(Map<String, Object> parameters, Class<? extends GenericEntity> entityClass, Class<? extends GenericReportDTO> reportDTO, OutputStream outputStream, JRBeanCollectionDataSource dataSource);
}
