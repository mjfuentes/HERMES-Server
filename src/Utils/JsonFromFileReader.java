package Utils;

import Dao.DaoFactory;
import Dao.NotificacionDAO;
import Model.Notificacion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonFromFileReader {

    public void read(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        try {
            String line = br.readLine();
            while (line != null) {
                try {
                    Notificacion notificacion = JsonParser.parseNotificacion(line);
                    DaoFactory.getNotificacionDAO().guardarNotificacion(notificacion);
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }
    }
}
