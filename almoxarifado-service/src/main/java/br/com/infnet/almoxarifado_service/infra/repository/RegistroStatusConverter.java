package br.com.infnet.almoxarifado_service.infra.repository;

import br.com.infnet.almoxarifado_service.domain.RegistroStatus;
import jakarta.persistence.AttributeConverter;

public class RegistroStatusConverter implements AttributeConverter<RegistroStatus, String> {
    @Override
    public String convertToDatabaseColumn(RegistroStatus registroStatus) {
        return registroStatus.toString();
    }

    @Override
    public RegistroStatus convertToEntityAttribute(String s) {
        return RegistroStatus.valueOf(s);
    }
}
