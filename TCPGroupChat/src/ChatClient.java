
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ChatClient {

    public static void main(String[] args) {

        String host = "127.0.0.1";
        int port = 5000;

        try (
                Socket socket = new Socket(host, port);

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream(),
                                StandardCharsets.UTF_8));

                PrintWriter out = new PrintWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream(),
                                StandardCharsets.UTF_8), true);

                Scanner scanner = new Scanner(
                        System.in, StandardCharsets.UTF_8)
        ) {

            System.out.println("DA KET NOI TOI SERVER");

            Thread receiveThread = new Thread(() -> {

                try {
                    String message;

                    while ((message = in.readLine()) != null) {
                        System.out.println(message);
                    }

                } catch (IOException e) {
                    if (!socket.isClosed()) {
                        System.out.println("Mat ket noi Server!");
                    }
                }
            });

            receiveThread.setDaemon(true);
            receiveThread.start();

            String message;

            while (scanner.hasNextLine()) {

                message = scanner.nextLine();

                out.println(message);

                if (message.trim().equalsIgnoreCase("/exit")) {
                    System.out.println("Dang ngat ket noi...");
                    break;
                }

                if (out.checkError()) {
                    System.out.println("Khong the gui tin nhan!");
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Khong the ket noi Server!");
            System.out.println("Chi tiet: " + e.getMessage());
        }
    }
}
