package Chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    ArrayList<Client> clients = new ArrayList<>();
    ServerSocket serverSocket;

    public ChatServer() throws IOException {
        serverSocket = new ServerSocket(8000);
    }

    public void sendAll(String message) {
        for (Client client : clients) {
            client.receive(message);
        }
    }

    public void run() {
        while (true) {
            System.out.println("Waiting...");
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");
                clients.add(new Client(socket, this));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new ChatServer().run();
    }
}
