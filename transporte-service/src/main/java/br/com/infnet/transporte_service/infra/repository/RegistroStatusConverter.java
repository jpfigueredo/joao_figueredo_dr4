package br.com.infnet.transporte_service.infra.repository;

import br.com.infnet.transporte_service.domain.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RegistroStatusConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status status) {
        return status.toString();
    }

    @Override
    public Status convertToEntityAttribute(String status) {
        return Status.valueOf(status);
    }
}
