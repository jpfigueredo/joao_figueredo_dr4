package br.com.infnet.almoxarifado_service.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PrecoConverter implements AttributeConverter<Preco, String> {

    @Override
    public String convertToDatabaseColumn(Preco preco) {
        return preco.toString();
    }

    @Override
    public Preco convertToEntityAttribute(String valor) {
        return valor != null ? new Preco(Double.parseDouble(valor)) : null;
    }

}
