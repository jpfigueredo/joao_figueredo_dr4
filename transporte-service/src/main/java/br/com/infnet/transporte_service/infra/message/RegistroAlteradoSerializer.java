package br.com.infnet.transporte_service.infra.message;

import br.com.infnet.transporte_service.eventos.RegistroAlterado;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class RegistroAlteradoSerializer extends StdSerializer<RegistroAlterado> {

    public RegistroAlteradoSerializer() {
        super(RegistroAlterado.class);
    }

    @Override
    public void serialize(RegistroAlterado evento, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        jgen.writeNumberField("pedidoID", evento.getPedidoID());
        jgen.writeStringField("status", evento.getStatus());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
        String data = sdf.format(evento.getMomento());
        jgen.writeStringField("momento", data);
    }
}
