package br.com.infnet.almoxarifado_service.infra.repository;

import br.com.infnet.almoxarifado_service.domain.Descricao;
import jakarta.persistence.AttributeConverter;

public class DescricaoConverter implements AttributeConverter<Descricao, String> {

    @Override
    public String convertToDatabaseColumn(Descricao descricao) {
        return descricao.getValor();
    }

    @Override
    public Descricao convertToEntityAttribute(String string) {
        return new Descricao(string);
    }
}
