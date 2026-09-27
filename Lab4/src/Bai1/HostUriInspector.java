package Bai1;

import java.net.*;

public class HostUriInspector {
    public static void main(String[] args) {
        if (args.length !=2) {
            System.out.print(
                    "Usage: java Bai1.HostUriInspector <hostname> <URL> "
            );
            return;
        }

        String hostname = args[0];
        String uriString = args[1];

//        Host name
        System.out.print("===== Host Inspector =====");
        System.out.println("Hostname:" + hostname);

        try {
            InetAddress[] addresses =
                    InetAddress.getAllByName(hostname);
            for (InetAddress address: addresses) {
                System.out.println("----------------------");
                System.out.println(
                        "IP: " + address.getHostName()
                );

                if (address instanceof Inet4Address) {
                    System.out.println("Type: IPV4");
                } else if (address instanceof Inet6Address) {
                    System.out.println("Type: IPV6");
                }else {
                    System.out.println("Type: Unknown");
                }

                System.out.println("Loopback:" + address.isLoopbackAddress());
                System.out.println("Site Local: " + address.isLoopbackAddress());
            }

        } catch (UnknownHostException e) {
            System.out.println("Khong phan gia duoc host name:" + hostname);
        }

//        Phan URI
        System.out.println();
        System.out.println("======= URI Inspector");

        try {
            URI uri = new URI(uriString);
            System.out.println("URI: " + uri);
            System.out.println("Scheme: " + uri.getScheme());
            System.out.println("Host" + uri.getHost());
            System.out.println("Port: " + uri.getPort());
            System.out.println("Path: " + uri.getPath());
            System.out.println("Query:" + uri.getQuery());
            System.out.println("Fragment: " + uri.getFragment());
        } catch (URISyntaxException e) {
            System.err.println("URI khong hop le" + uriString   );
        }
    }


}
