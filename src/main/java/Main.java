import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        port(8080);
        staticFileLocation("/public");
        //get("/", (req, res) -> "Hello World! It's fun to use sparkly emojis in code!");
        get("/", (request, response) -> {
            response.header("Content-Type", "text/html; charset=utf-8");
            response.status(200);
            return new View(request).index();
        });
        get("/soma/*", (req, res) -> {
            String expre = "soma " + req.splat()[0];
            return ConectarServer(expre);
        });
        get("/sub/*", (req, res) -> {
            String expre = "sub " + req.splat()[0];
            return ConectarServer(expre);
        });
        get("/div/*", (req, res) -> {
            String expre = "div " + req.splat()[0];
            return ConectarServer(expre);
        });
        get("/mult/*", (req, res) -> {
            String expre = "mult " + req.splat()[0];
            return ConectarServer(expre);
        });
        get("/porc/*", (req, res) -> {
            String expre = "porc " + req.splat()[0];
            return ConectarServer(expre);
        });
        get("/raiz/*", (req, res) -> {
            String expre = "raiz " + req.splat()[0];
            return ConectarServer(expre);
        });
        get("/pot/*", (req, res) -> {
            String expre = "pot " + req.splat()[0];
            return ConectarServer(expre);
        });
    }
    public static String ConectarServer(String dados){
        try {
            Socket s = null;
            InputStream i = null;
            OutputStream o = null;
            String str;
            do {
                for (int pos = dados.length(); pos < 100; pos++)
                {
                    dados += " ";
                }

                byte[] line = dados.getBytes();
                str = new String(line);

                if(str.trim().toLowerCase(Locale.ROOT).contains("soma")
                        || str.trim().toLowerCase(Locale.ROOT).contains("sub")
                        || str.trim().toLowerCase(Locale.ROOT).contains("div")
                        || str.trim().toLowerCase(Locale.ROOT).contains("mult")){
                    s = new Socket("127.0.0.1", 9999); // Server A
                }

                if(str.trim().toLowerCase(Locale.ROOT).contains("pot")
                        || str.trim().toLowerCase(Locale.ROOT).contains("raiz")
                        || str.trim().toLowerCase(Locale.ROOT).contains("porc")){
                    s = new Socket("127.0.0.1", 9998); // Server B
                }
                i = s.getInputStream();
                o = s.getOutputStream();

                o.write(line);
                i.read(line);
                str = new String(line);

                return "{\"resultado\": \"" + str.trim() + "\"}";

            } while ( !str.trim().equals("bye") );
        }
        catch (Exception err) {
            System.err.println(err);
        }
        return "";
    }

}
