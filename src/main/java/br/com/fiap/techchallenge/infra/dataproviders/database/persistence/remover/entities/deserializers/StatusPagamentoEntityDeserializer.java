package br.com.fiap.techchallenge.infra.dataproviders.database.persistence.remover.entities.deserializers;

import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.remover.entities.StatusPagamentoEntity;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class StatusPagamentoEntityDeserializer extends JsonDeserializer<StatusPagamentoEntity> {
    @Override
    public StatusPagamentoEntity deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        return new StatusPagamentoEntity(jsonParser.getText());
    }
}
