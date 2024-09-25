package br.com.infnet.almoxarifado_service.infra.message;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import br.com.infnet.almoxarifado_service.events.RegistroAlterado;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class RegistroAlteradoSerializer extends StdSerializer<RegistroAlterado> {

    public RegistroAlteradoSerializer() {
        super(RegistroAlterado.class);
    }

    @Override
    public void serialize(RegistroAlterado event, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        jgen.writeNumberField("idPedido", event.getIdPedido());
        jgen.writeStringField("estado", event.getEstado());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
        String data = sdf.format(event.getMomento());
        jgen.writeStringField("momento", data);
    }
}
