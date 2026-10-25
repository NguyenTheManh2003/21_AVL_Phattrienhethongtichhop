package bai1;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class HelloClient {
    public static void main(String[] args) {
        try {
            Registry registry =
                    LocateRegistry.getRegistry("localhost", 1099);

            HelloInterface service =
                    (HelloInterface) registry.lookup("HelloService");

            System.out.println(service.sayHello());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}