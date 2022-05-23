package Chat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable{
    Socket socket;
    Scanner in;
    PrintStream out;
    ChatServer server;
    String name;

    public Client(Socket socket, ChatServer server) {
        this.socket = socket;
        this.server = server;
        new Thread(this).start();
    }

    public void receive(String message) {
        out.println(message);
    }

    @Override
    public void run() {
        try{
            in = new Scanner(socket.getInputStream());
            out = new PrintStream(socket.getOutputStream());

            out.println("Welcome!!!");
            out.print("What is your name? - ");
            name = in.nextLine();
            String input = in.nextLine();
            while(!input.equals("bye")) {
                server.sendAll(name + ": " + input);
                input = in.nextLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            in.close();
            out.close();
        }
    }
}
