import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer implements Runnable {
    int port;
    private ServerSocket serverSocket;
    public HttpServer(int port) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(port);
    }
    public void run() {
        System.out.println("SimpleBBS Server Listening on: " +  port);

        while(true){
            Socket socket = null;
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            HttpRequestHandler handler = new HttpRequestHandler(socket);
            Thread handlerThread = new Thread(handler);
            handlerThread.start();
        }
    }

}