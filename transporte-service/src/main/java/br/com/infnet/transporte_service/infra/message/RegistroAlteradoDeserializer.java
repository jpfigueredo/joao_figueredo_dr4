package br.com.infnet.transporte_service.infra.message;


import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import br.com.infnet.transporte_service.eventos.RegistroAlterado;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RegistroAlteradoDeserializer extends StdDeserializer<RegistroAlterado> {
    
    public RegistroAlteradoDeserializer() {
        super(RegistroAlterado.class);
    }

    @Override
    public RegistroAlterado deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JacksonException {
        RegistroAlterado evento = null;
        JsonNode node = jp.getCodec().readTree(jp);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
        try {
            evento = new RegistroAlterado(
                    node.get("pedidoID").asLong(),
                    node.get("status").asText(),
                    sdf.parse(node.get("momento").asText())
            );
        } catch (ParseException e) {
            throw new IOException("Erro na data");
        }
        return evento;
    }
}
