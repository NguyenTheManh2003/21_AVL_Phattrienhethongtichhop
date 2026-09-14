import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class RequestProcessing extends Thread {
    private Socket channel;

    public RequestProcessing(Socket s) {
        channel = s;
    }

    @Override
    public void run() {
        try {
            OutputStream os = channel.getOutputStream();
            InputStream is = channel.getInputStream();

            while (true) {
                int n = is.read();

                if (n == -1) {
                    break;
                }

                os.write(n);
            }

            channel.close();

        } catch (IOException e) {
            System.out.println("Request Processing Error: " + e);
        }
    }
}