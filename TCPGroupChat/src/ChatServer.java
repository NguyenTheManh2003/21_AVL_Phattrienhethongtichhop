
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Set;

public class ChatServer {

    public static final int PORT = 5000;

    public static final List<String> ROOMS =
            Arrays.asList("Study", "Game", "TraiNghiem");

    private static final Set<ClientHandler> clients =
            ConcurrentHashMap.newKeySet();

    public static void addClient(ClientHandler client) {
        clients.add(client);
    }

    public static void removeClient(ClientHandler client) {
        clients.remove(client);
    }

    public static void broadcast(
            String room, String message, ClientHandler sender) {

        for (ClientHandler client : clients) {
            if (client != sender && room.equals(client.getRoom())) {
                client.sendMessage(message);
            }
        }
    }

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            System.out.println("SERVER DA KHOI DONG");
            System.out.println("Dang lang nghe tai cong: " + PORT);

            while (true) {
                Socket socket = serverSocket.accept();

                System.out.println("Client ket noi: "
                        + socket.getRemoteSocketAddress());

                ClientHandler client = new ClientHandler(socket);
                addClient(client);

                Thread thread = new Thread(client);
                thread.start();
            }

        } catch (IOException e) {
            System.out.println("Loi Server: " + e.getMessage());
        }
    }
}
