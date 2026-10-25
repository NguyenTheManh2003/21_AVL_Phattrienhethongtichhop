
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientHandler implements Runnable {

    private final Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    private String name;
    private volatile String room;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public String getRoom() {
        return room;
    }

    public void sendMessage(String message) {
        if (out != null) {
            out.println(message);
        }
    }

    private void showRooms() {
        sendMessage("\n===== DANH SACH PHONG =====");

        for (int i = 0; i < ChatServer.ROOMS.size(); i++) {
            sendMessage((i + 1) + ". " + ChatServer.ROOMS.get(i));
        }

        sendMessage("Nhap so phong de tham gia.");
        sendMessage("Nhap /exit de thoat.");
        sendMessage("Nhap lua chon:");
    }

    @Override
    public void run() {

        try {
            in = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream(),
                            StandardCharsets.UTF_8));

            out = new PrintWriter(
                    new OutputStreamWriter(
                            socket.getOutputStream(),
                            StandardCharsets.UTF_8), true);

            sendMessage("Nhap ten cua ban:");

            name = in.readLine();

            if (name == null || name.trim().isEmpty()) {
                sendMessage("Ten khong hop le!");
                return;
            }

            name = name.trim();

            System.out.println(name + " da ket noi.");

            showRooms();

            String message;

            while ((message = in.readLine()) != null) {

                message = message.trim();

                if (message.equalsIgnoreCase("/exit")) {
                    sendMessage("Tam biet " + name + "!");
                    break;
                }

                if (message.equalsIgnoreCase("/quit")) {

                    if (room != null) {
                        String oldRoom = room;
                        room = null;

                        ChatServer.broadcast(
                                oldRoom,
                                name + " da roi phong.",
                                this);

                        sendMessage("Ban da roi phong " + oldRoom);
                    }

                    showRooms();
                    continue;
                }

                if (room == null) {

                    try {
                        int choice = Integer.parseInt(message);

                        if (choice >= 1 &&
                                choice <= ChatServer.ROOMS.size()) {

                            room = ChatServer.ROOMS.get(choice - 1);

                            sendMessage("Ban da vao phong: " + room);
                            sendMessage("Nhap tin nhan de chat.");
                            sendMessage("/quit: Roi phong");
                            sendMessage("/exit: Thoat chuong trinh");

                            ChatServer.broadcast(
                                    room,
                                    name + " da tham gia phong.",
                                    this);

                            System.out.println(
                                    name + " vao phong " + room);

                        } else {
                            sendMessage("Phong khong ton tai!");
                            showRooms();
                        }

                    } catch (NumberFormatException e) {
                        sendMessage("Vui long nhap so phong hop le!");
                        showRooms();
                    }

                } else {

                    if (!message.isEmpty()) {

                        String chatMessage = name + ": " + message;

                        ChatServer.broadcast(
                                room, chatMessage, this);

                        System.out.println(
                                "[" + room + "] " + chatMessage);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Client mat ket noi: " + name);

        } finally {

            if (room != null && name != null) {
                ChatServer.broadcast(
                        room,
                        name + " da ngat ket noi.",
                        this);
            }

            ChatServer.removeClient(this);

            try {
                socket.close();
            } catch (IOException ignored) {
            }

            System.out.println("Da dong ket noi: " + name);
        }
    }
}
