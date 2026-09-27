package Bai3;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUdpServer {

    private static final int PORT = 5001;

    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd MM yyyy");

    private static final DateTimeFormatter TIME_FORMAT =
            DateTimeFormatter.ofPattern("HH mm ss");

    public static void main(String[] args) {

        byte[] buffer = new byte[4096];

        System.out.println("=== UDP DATE TIME SERVER ===");

        try (DatagramSocket socket = new DatagramSocket(PORT)) {

            System.out.println(
                    "UDP Server đang chạy tại port " + PORT
            );

            while (true) {

                DatagramPacket request =
                        new DatagramPacket(
                                buffer,
                                buffer.length
                        );

                socket.receive(request);

                String command = new String(
                        request.getData(),
                        request.getOffset(),
                        request.getLength(),
                        StandardCharsets.UTF_8
                );

                System.out.println(
                        "Client gửi: " + command
                );

                String result = processCommand(command);

                byte[] responseData =
                        result.getBytes(
                                StandardCharsets.UTF_8
                        );

                DatagramPacket response =
                        new DatagramPacket(
                                responseData,
                                responseData.length,
                                request.getAddress(),
                                request.getPort()
                        );

                socket.send(response);

                System.out.println(
                        "Server trả: " + result
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "UDP Server error: " + e.getMessage()
            );
        }
    }

    private static String processCommand(String command) {

        LocalDateTime now = LocalDateTime.now();

        switch (command.toUpperCase()) {

            case "DATE":
                return "OK " + now.format(DATE_FORMAT);

            case "TIME":
                return "OK " + now.format(TIME_FORMAT);

            case "DATETIME":
                return "OK "
                        + now.format(DATE_FORMAT)
                        + " "
                        + now.format(TIME_FORMAT);

            default:
                return "ERR UNKNOWN_COMMAND";
        }
    }
}