import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {

    public ArrayList<String> msgs = new ArrayList();
    public ArrayList<PrintWriter> outs = new ArrayList();

    public Server(){
        Thread clientConectorThread = new Thread(new SocketStarter(this));
        clientConectorThread.start();
        serverLoop();
    }

    public static void main(String[] args) {
        new Server();
    }

    public void serverLoop(){
        boolean run = true;
        System.out.println("Server started.");
        while (true) {
            Scanner tgb = new Scanner(System.in);
            while (run) {
                String command = null;
                if (command == "stop"){
                    run = false;
                }
                // System.out.println(msgs);
                if(!msgs.isEmpty()){
                    msgs.forEach(msg ->{
                        System.out.println(msg);
                        outs.forEach(out ->{
                            out.println(msg);
                        });
                    });
                    msgs.clear();
                }
            }
        }
    }
}
