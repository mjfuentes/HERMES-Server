package Utils;

import com.sun.net.httpserver.HttpServer;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Properties;
import java.util.concurrent.Executors;

public class ServerThread extends Thread {

    @Override
    public void run() {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("conf.properties"));
            int port = Integer.valueOf(prop.getProperty("port"));
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext("/", new BaseHandler());
            server.setExecutor(Executors.newCachedThreadPool());
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
