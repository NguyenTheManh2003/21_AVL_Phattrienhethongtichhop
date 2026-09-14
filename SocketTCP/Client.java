import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 5000;

        try {
            Socket socket = new Socket(host, port);

            BufferedReader keyboard = new BufferedReader(
                    new InputStreamReader(System.in)
            );

            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            PrintWriter output = new PrintWriter(
                    socket.getOutputStream(), true
            );

            System.out.print("Nhap noi dung gui Server: ");
            String message = keyboard.readLine();

            output.println(message);

            String response = input.readLine();

            System.out.println(
                    "Server Echo lai: " + response
            );

            socket.close();

        } catch (IOException e) {
            System.out.println("Client Error: " + e);
        }
    }
}