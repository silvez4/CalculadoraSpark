import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_A {

    public static void main(String[] args) 	{
        try {
            ServerSocket s = new ServerSocket(9999);

            while (true) {
                Socket c = s.accept();
                InputStream i = c.getInputStream();
                OutputStream o = c.getOutputStream();

                Thread t = new ClientHandler_A(c, i, o);
                t.start();
            }
        }
        catch (Exception err){
            System.err.println(err);
        }
    }

}