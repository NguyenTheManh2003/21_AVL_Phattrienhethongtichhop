package Bai3;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class DateTimeUdpClient {

    private static final String HOST = "localhost";
    private static final int PORT = 5001;

    public static void main(String[] args) {

        System.out.println("=== UDP DATE TIME CLIENT ===");
        System.out.println("Các lệnh: DATE, TIME, DATETIME");
        System.out.println("Nhập EXIT để đóng Client.");

        try (
                DatagramSocket socket = new DatagramSocket();

                BufferedReader console =
                        new BufferedReader(
                                new InputStreamReader(
                                        System.in,
                                        StandardCharsets.UTF_8
                                )
                        )
        ) {

            // Không chờ vô hạn nếu Server không phản hồi
            socket.setSoTimeout(3000);

            InetAddress server =
                    InetAddress.getByName(HOST);

            while (true) {

                System.out.print("Client > ");

                String command = console.readLine();

                if (command == null ||
                        command.equalsIgnoreCase("EXIT")) {
                    break;
                }

                byte[] data =
                        command.getBytes(
                                StandardCharsets.UTF_8
                        );

                DatagramPacket request =
                        new DatagramPacket(
                                data,
                                data.length,
                                server,
                                PORT
                        );

                socket.send(request);

                byte[] buffer = new byte[4096];

                DatagramPacket response =
                        new DatagramPacket(
                                buffer,
                                buffer.length
                        );

                try {

                    socket.receive(response);

                    String result =
                            new String(
                                    response.getData(),
                                    response.getOffset(),
                                    response.getLength(),
                                    StandardCharsets.UTF_8
                            );

                    System.out.println(
                            "Server > " + result
                    );

                } catch (SocketTimeoutException e) {

                    System.out.println(
                            "Timeout: Server không phản hồi sau 3 giây."
                    );
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Lỗi UDP Client: " + e.getMessage()
            );
        }
    }
}