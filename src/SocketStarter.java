import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import static java.lang.System.in;

public class SocketStarter implements Runnable{
    private int port = 8000;
    private Server server;

    public SocketStarter(Server server){
        this.server = server;
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            System.out.println("server is ready");
            Socket socket;
            try {
                socket = serverSocket.accept();
                server.outs.add(new PrintWriter(socket.getOutputStream(), true));
                new Thread(new ServerThread(socket,server)).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    public void stop()  {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
