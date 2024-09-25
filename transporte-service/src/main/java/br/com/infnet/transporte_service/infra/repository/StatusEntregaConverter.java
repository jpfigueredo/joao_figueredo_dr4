package br.com.infnet.transporte_service.infra.repository;

import br.com.infnet.transporte_service.domain.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusEntregaConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status entregaStatus) {
        return entregaStatus.toString();
    }

    @Override
    public Status convertToEntityAttribute(String entregaStatus) {
        return Status.valueOf(entregaStatus);
    }
}
