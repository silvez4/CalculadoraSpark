import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

// ClientHandler class
class ClientHandler_A extends Thread
{
    final InputStream dis;
    final OutputStream dos;
    final Socket c;


    // Constructor
    public ClientHandler_A(Socket c, InputStream dis, OutputStream dos)
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
               if (str.trim().toLowerCase(Locale.ROOT).contains("soma")) {
                   String somente_numero = str.toLowerCase(Locale.ROOT)
                           .replaceAll("soma\\s*", "")
                           .replaceAll("\\D", " ");

                   String nums[] = somente_numero.split(" ");

                   double resultado = 0;
                   for (int count = 0; count < nums.length; count++) {
                       resultado += Double.valueOf(nums[count]);
                   }
                   String resposta = new String();
                   resposta = String.valueOf(resultado);

                   for (int j = 0; j < (line.length / 2); j++) {
                       resposta += " ";
                   }
                   o.write(resposta.getBytes(StandardCharsets.UTF_8));
               }

               if (str.trim().toLowerCase(Locale.ROOT).contains("sub")) {
                   String somente_numero = str.toLowerCase(Locale.ROOT)
                           .replaceAll("sub\\s*", "")
                           .replaceAll("\\D", " ");

                   String nums[] = somente_numero.split(" ");

                   double resultado = 0;
                   for (int count = 0; count < nums.length; count++) {
                       if (count == 0)
                           resultado = Double.valueOf(nums[count]);
                       else
                           resultado -= Double.valueOf(nums[count]);
                   }
                   String resposta = new String();
                   resposta = String.valueOf(resultado);

                   for (int j = 0; j < (line.length / 2); j++) {
                       resposta += " ";
                   }
                   o.write(resposta.getBytes(StandardCharsets.UTF_8));
               }

               if (str.trim().toLowerCase(Locale.ROOT).contains("div")) {
                   String somente_numero = str.toLowerCase(Locale.ROOT)
                           .replaceAll("div\\s*", "")
                           .replaceAll("\\D", " ");

                   String nums[] = somente_numero.split(" ");

                   double resultado = 0;
                   for (int count = 0; count < nums.length; count++) {
                       if (count == 0)
                           resultado = Double.valueOf(nums[count]);
                       else
                           resultado /= Double.valueOf(nums[count]);
                   }
                   String resposta = new String();
                   resposta = String.valueOf(resultado);

                   for (int j = 0; j < (line.length / 2); j++) {
                       resposta += " ";
                   }
                   o.write(resposta.getBytes(StandardCharsets.UTF_8));
               }

               if (str.trim().toLowerCase(Locale.ROOT).contains("mult")) {
                   String somente_numero = str.toLowerCase(Locale.ROOT)
                           .replaceAll("mult\\s*", "")
                           .replaceAll("\\D", " ");

                   String nums[] = somente_numero.split(" ");

                   double resultado = 0;
                   for (int count = 0; count < nums.length; count++) {
                       if (count == 0)
                           resultado = Double.valueOf(nums[count]);
                       else
                           resultado *= Double.valueOf(nums[count]);
                   }
                   String resposta = new String();
                   resposta = String.valueOf(resultado);

                   for (int j = 0; j < (line.length / 2); j++) {
                       resposta += " ";
                   }
                   o.write(resposta.getBytes(StandardCharsets.UTF_8));
               } else
                   System.out.println("Comando Invalido".getBytes());
           } while (!str.trim().equals("bye"));
       } catch (Exception e){
           e.printStackTrace();
       }

    }
}