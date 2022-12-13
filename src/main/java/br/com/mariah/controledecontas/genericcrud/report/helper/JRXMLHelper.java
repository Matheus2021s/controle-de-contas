package br.com.mariah.controledecontas.genericcrud.report.helper;

import br.com.mariah.controledecontas.genericcrud.domain.GenericEntity;
import br.com.mariah.controledecontas.genericcrud.report.GenericReportDTO;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Field;

@Component
public class JRXMLHelper {

    private static final String HEADER_FIELD_TEMPLATE = """
            <staticText>
            	<reportElement x="%s" y="0" width="100" height="30" />
            	<text><![CDATA[%s]]></text>
            </staticText>""";
    private static final String DETAIL_FIELD_TEMPLATE = """
               <textField>
                   <reportElement x="%s" y="0" width="100" height="30"/>
                   <textFieldExpression><![CDATA[$F{%s}]]></textFieldExpression>
               </textField>
            """;
    private static final String FIELD_TEMPLATE = "<field name=\"%s\" class=\"%s\"/>";

    private static final String TEMPLATE_DESCRIPTION_TARGET = "<text><![CDATA[Templates cadastrados ]]></text>";

    private static final String TEMPLATE_DESCRIPTION_PLACEHOLDER = "<text><![CDATA[%S CADASTRADOS ]]></text>";

    private static final String FILE_NAME_TARGET = "/jasperreport.xsd\" name=\"template\"";

    private static final String FILE_NAME_PLACEHOLDER = "/jasperreport.xsd\" name=\"%s\"";

    private static final String TEMPLATE_NAME_TARGET = "<text><![CDATA[Template name]]></text>";

    private static final String TEMPLATE_NAME_PLACEHOLDER = "<text><![CDATA[%S]]></text>";

    private static final String BAND_PLACEHOLDER = "<band height=\"50\">%s</band>";

    @SneakyThrows
    public void generateClass(Class<? extends GenericEntity> classEntity,Class<? extends GenericReportDTO> reportDTO) {

        File file = ResourceUtils.getFile("src/main/resources/reports/template.jrxml");

        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = fileInputStream.readAllBytes();
        String fileAsString = new String(bytes);

        int columnHeaderStart = fileAsString.indexOf("<columnHeader>") + "<columnHeader>".length();
        int columnHeaderEnd = fileAsString.indexOf("</columnHeader>") - "</columnHeader>".length() + 15;
        String columnHeader = fileAsString.substring(columnHeaderStart, columnHeaderEnd);

        int detailStart = fileAsString.indexOf("<detail>") + "<detail>".length();
        int detailEnd = fileAsString.indexOf("</detail>") - "</detail>".length() + 9;
        String detail = fileAsString.substring(detailStart, detailEnd);

        int fieldStart = fileAsString.indexOf("</queryString>") + "</queryString>".length();
        int fieldEnd = fileAsString.indexOf("<title>") - "<title>".length() + 5;

        String field = fileAsString.substring(fieldStart, fieldEnd);

        String newFields = "";
        String newColumns = "";
        String newDetails = "";

        int fieldXValue = 0;

        for (Field declaredField : reportDTO.getDeclaredFields()) {
            declaredField.setAccessible(true);

            String name = declaredField.getName();
            String typeName = declaredField.getType().getTypeName();

            newFields += "\n" + String.format(FIELD_TEMPLATE, name, typeName);
            newColumns += "\n" + String.format(HEADER_FIELD_TEMPLATE,fieldXValue, name);
            newDetails += "\n" + String.format(DETAIL_FIELD_TEMPLATE,fieldXValue, name);

            fieldXValue += 200;

            declaredField.setAccessible(false);

        }


        fileAsString = fileAsString.replace(field, newFields);
        fileAsString = fileAsString.replace(columnHeader, String.format(BAND_PLACEHOLDER, newColumns));
        fileAsString = fileAsString.replace(detail, String.format(BAND_PLACEHOLDER, newDetails));
        fileAsString = fileAsString.replace(TEMPLATE_DESCRIPTION_TARGET, String.format(TEMPLATE_DESCRIPTION_PLACEHOLDER, classEntity.getSimpleName()));
        fileAsString = fileAsString.replace(FILE_NAME_TARGET, String.format(FILE_NAME_PLACEHOLDER, classEntity.getSimpleName()));
        fileAsString = fileAsString.replace(TEMPLATE_NAME_TARGET, String.format(TEMPLATE_NAME_PLACEHOLDER, classEntity.getSimpleName()));


        File destinationFile = new File(String.format("src/main/resources/reports/%s.jrxml", classEntity.getSimpleName()));
        try (FileOutputStream fileOutputStream = new FileOutputStream(destinationFile.getAbsolutePath())) {
            fileOutputStream.write(fileAsString.getBytes());
        }
    }

}
