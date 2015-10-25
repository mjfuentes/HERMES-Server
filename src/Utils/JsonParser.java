package Utils;

import Model.Notificacion;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonParser {
    public static Notificacion parseNotificacion(String json) throws IOException {
        Notificacion notificacion = new ObjectMapper().readValue(json, Notificacion.class);
        return notificacion;
    }
}
