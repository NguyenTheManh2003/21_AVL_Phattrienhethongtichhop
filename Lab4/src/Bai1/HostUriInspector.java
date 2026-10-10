package Bai1;

import java.net.*;

public class HostUriInspector {

    public static void main(String[] args) {

        // Kiểm tra đủ 2 tham số: hostname và URI
        if (args.length != 2) {
            System.out.println(
                    "Usage: java Bai1.HostUriInspector <hostname> <URI>"
            );
            return;
        }

        String hostname = args[0];
        String uriString = args[1];

        // =========================
        // PHẦN 1: HOST INSPECTOR
        // =========================
        System.out.println("===== HOST INSPECTOR =====");
        System.out.println("Hostname: " + hostname);

        try {
            InetAddress[] addresses =
                    InetAddress.getAllByName(hostname);

            for (InetAddress address : addresses) {

                System.out.println("-------------------------");

                // In địa chỉ IP
                System.out.println(
                        "IP: " + address.getHostAddress()
                );

                // Kiểm tra IPv4 hoặc IPv6
                if (address instanceof Inet4Address) {
                    System.out.println("Type: IPv4");
                } else if (address instanceof Inet6Address) {
                    System.out.println("Type: IPv6");
                } else {
                    System.out.println("Type: Unknown");
                }

                // Kiểm tra Loopback
                System.out.println(
                        "Loopback: "
                                + address.isLoopbackAddress()
                );

                // Kiểm tra Site Local
                System.out.println(
                        "Site Local: "
                                + address.isSiteLocalAddress()
                );
            }

        } catch (UnknownHostException e) {

            System.out.println(
                    "ERROR: Khong phan giai duoc hostname: "
                            + hostname
            );
        }

        // =========================
        // PHẦN 2: URI INSPECTOR
        // =========================
        System.out.println();
        System.out.println("===== URI INSPECTOR =====");

        try {

            URI uri = new URI(uriString);

            System.out.println("URI: " + uri);
            System.out.println("Scheme: " + uri.getScheme());
            System.out.println("Host: " + uri.getHost());
            System.out.println("Port: " + uri.getPort());
            System.out.println("Path: " + uri.getPath());
            System.out.println("Query: " + uri.getQuery());
            System.out.println("Fragment: " + uri.getFragment());

        } catch (URISyntaxException e) {

            System.out.println(
                    "ERROR: URI khong hop le: " + uriString
            );
        }
    }
}