import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

// ClientHandler class
class ClientHandler_B extends Thread
{
    final InputStream dis;
    final OutputStream dos;
    final Socket c;


    // Constructor
    public ClientHandler_B(Socket c, InputStream dis, OutputStream dos)
    {
        this.c = c;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run()
    {
        String str;
       try {
           InputStream i = c.getInputStream();
           OutputStream o = c.getOutputStream();

           do {
               byte[] line = new byte[100];
               i.read(line);

               str = new String(line);
               if (str.trim().toLowerCase(Locale.ROOT).contains("pot")) {
                   String somente_numero = str.toLowerCase(Locale.ROOT)
                           .replaceAll("pot\\s*", "")
                           .replaceAll("\\D", " ");

                   String nums[] = somente_numero.split(" ");

                   double resultado = 0;
                   for (int count = 0; count <2; count++) {
                       resultado = count == 0 ? Double.valueOf(nums[count]) : Math.pow(resultado,  Double.valueOf(nums[count]));
                   }
                   String resposta = new String();
                   resposta = String.valueOf(resultado);

                   for (int j = 0; j < (line.length / 2); j++) {
                       resposta += " ";
                   }
                   o.write(resposta.getBytes(StandardCharsets.UTF_8));
               }

               if (str.trim().toLowerCase(Locale.ROOT).contains("raiz")) {
                   String somente_numero = str.toLowerCase(Locale.ROOT)
                           .replaceAll("raiz\\s*", "")
                           .replaceAll("\\D", " ");

                   String nums[] = somente_numero.split(" ");

                   double resultado = 0;
                   for (int count = 0; count < 1; count++) {
                       resultado = Math.sqrt(Double.valueOf(nums[count]));
                   }
                   String resposta = new String();
                   resposta = String.valueOf(resultado);

                   for (int j = 0; j < (line.length / 2); j++) {
                       resposta += " ";
                   }
                   o.write(resposta.getBytes(StandardCharsets.UTF_8));
               }

               if (str.trim().toLowerCase(Locale.ROOT).contains("porc")) {
                   String somente_numero = str.toLowerCase(Locale.ROOT)
                           .replaceAll("porc\\s*", "")
                           .replaceAll("\\D", " ");

                   String nums[] = somente_numero.split(" ");

                   double resultado = 0;
                   for (int count = 0; count < 2; count++) {
                       resultado = count == 0 ? Double.valueOf(nums[count]) : (resultado * Double.valueOf(nums[count])) / 100;
                   }
                   String resposta = new String();
                   resposta = String.valueOf(resultado);

                   for (int j = 0; j < (line.length / 2); j++) {
                       resposta += " ";
                   }
                   o.write(resposta.getBytes(StandardCharsets.UTF_8));
               }
               else
                   System.out.println("Comando Invalido".getBytes());
           } while (!str.trim().equals("bye"));
       } catch (Exception e){
           e.printStackTrace();
       }

    }
}