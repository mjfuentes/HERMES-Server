package Utils;

import Dao.DaoFactory;
import Dto.NotificacionDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BaseHandler implements HttpHandler {
    public void handle(HttpExchange t){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(t.getRequestBody()));
            StringBuilder json = new StringBuilder();
            String inputStr;
            while ((inputStr = reader.readLine()) != null)
                json.append(inputStr);
            ObjectMapper objectMapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            objectMapper.setDateFormat(df);
            List<NotificacionDTO> notificaciones;
            notificaciones = objectMapper.readValue(json.toString(), objectMapper.getTypeFactory().constructCollectionType(List.class, NotificacionDTO.class));
            for(NotificacionDTO notificacion:notificaciones) {
                notificacion.setFecha_recepcion(new Date());
                DaoFactory.getNotificacionDAO().guardarNotificacion(notificacion);
            }
            String response = "OK";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
