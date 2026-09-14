import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 5000;

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server dang cho ket noi tai port " + port + "...");

            Socket socket = serverSocket.accept();
            System.out.println("Client da ket noi!");

            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            PrintWriter output = new PrintWriter(
                    socket.getOutputStream(), true
            );

            String message = input.readLine();
            System.out.println("Client gui: " + message);

            output.println("Server da nhan: " + message);

            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}