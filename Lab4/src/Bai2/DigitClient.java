package Bai2;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class DigitClient {

    private static final String HOST = "localhost";
    private static final int PORT = 5000;

    public static void main(String[] args) {

        System.out.println("=== DIGIT CLIENT ===");

        try (
                Socket socket = new Socket(HOST, PORT);

                BufferedReader console = new BufferedReader(
                        new InputStreamReader(
                                System.in,
                                StandardCharsets.UTF_8
                        )
                );

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream(),
                                StandardCharsets.UTF_8
                        )
                );

                PrintWriter out = new PrintWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream(),
                                StandardCharsets.UTF_8
                        ),
                        true
                )
        ) {

            System.out.println("Đã kết nối Server.");
            System.out.println("Nhập chữ số 0-9.");
            System.out.println("Nhập QUIT để thoát.");

            while (true) {

                System.out.print("Client > ");

                String request = console.readLine();

                if (request == null) {
                    break;
                }

                out.println(request);

                String response = in.readLine();

                if (response == null) {
                    System.out.println(
                            "Server đã đóng kết nối."
                    );
                    break;
                }

                System.out.println(
                        "Server > " + response
                );

                if (request.equalsIgnoreCase("QUIT")) {
                    break;
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Lỗi kết nối: " + e.getMessage()
            );
        }
    }
}