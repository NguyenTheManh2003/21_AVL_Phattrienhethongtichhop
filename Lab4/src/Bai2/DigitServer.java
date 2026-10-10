package Bai2;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class DigitServer {

    private static final int PORT = 5000;

    public static void main(String[] args) {

        System.out.println("=== DIGIT SERVER ===");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            System.out.println("Server dang chay tai port " + PORT);
            System.out.println("Dang cho Client ket noi...");

            while (true) {

                try (
                        Socket socket = serverSocket.accept();

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

                    System.out.println(
                            "Client da ket noi: "
                                    + socket.getRemoteSocketAddress()
                    );

                    String request;

                    while ((request = in.readLine()) != null) {

                        System.out.println("Client gui: [" + request + "]");

                        if (request.equalsIgnoreCase("QUIT")) {
                            out.println("OK BYE");
                            System.out.println("Client da QUIT.");
                            break;
                        }

                        String response = convertDigit(request);

                        out.println(response);

                        System.out.println("Server tra: " + response);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Loi Server: " + e.getMessage());
        }
    }

    private static String convertDigit(String input) {

        if (input.length() != 1) {
            return "Ko dươc qua 2 chu so";
        }

        switch (input.charAt(0)) {
            case '0': return " khumm";
            case '1': return " Một";
            case '2': return " Hazz";
            case '3': return " BaBa";
            case '4': return "Bốn";
            case '5': return "Lăm";
            case '6': return "Sáu";
            case '7': return "Bảy";
            case '8': return "Tám";
            case '9': return "Chín";
            default: return "Vui Lòng nhập 1 chữ số từ 0 đến 9";
        }
    }
}