package Utils;

import Dao.DaoFactory;
import Dto.NotificacionDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class JsonFromFileReader {

    public void read(String filename) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(filename));
        String json = new String(encoded, Charset.defaultCharset());
        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        objectMapper.setDateFormat(df);
        List<NotificacionDTO> notificaciones = objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, NotificacionDTO.class));
        for(NotificacionDTO notificacion:notificaciones){
            notificacion.setFecha_recepcion(new Date());
            DaoFactory.getNotificacionDAO().guardarNotificacion(notificacion);
        }

    }
}
