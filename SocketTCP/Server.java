import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 5000;

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            System.out.println("TCP Echo Server dang chay...");
            System.out.println("Dang cho Client ket noi tai port " + port);

            while (true) {
                Socket socket = serverSocket.accept();

                System.out.println(
                    "Client da ket noi: " +
                    socket.getInetAddress().getHostAddress()
                );

                RequestProcessing request =
                        new RequestProcessing(socket);

                request.start();
            }

        } catch (IOException e) {
            System.out.println("Server Error: " + e);
        }
    }
}