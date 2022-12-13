package br.com.mariah.controledecontas.genericcrud.report;

import br.com.mariah.controledecontas.genericcrud.domain.GenericEntity;

public interface GenericReportDTO<T extends GenericEntity> {

    GenericReportDTO from(T entity);

}
