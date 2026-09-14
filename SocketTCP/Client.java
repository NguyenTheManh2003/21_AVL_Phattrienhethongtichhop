import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 5000;

        try {
            Socket socket = new Socket(host, port);

            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            PrintWriter output = new PrintWriter(
                    socket.getOutputStream(), true
            );

            output.println("Xin chao Server!");

            String response = input.readLine();
            System.out.println("Server tra loi: " + response);

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}