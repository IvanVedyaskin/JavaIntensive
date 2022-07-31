package edu.school21.sockets.server;

import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.services.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final UserService service;
    private ServerSocket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private Socket client;

    public Server(int port) throws IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
        service = context.getBean("userService", UserService.class);
        socket = new ServerSocket(port);
        client = socket.accept();
        reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
    }

    public void start() throws IOException {
        printAndFlush("Hello from Server!\n");
        if (reader.readLine().equals("SignUp"))
            registration();
        printAndFlush("close\n");
        reader.close();
        writer.close();
        client.close();
        socket.close();
    }

    private void registration() throws IOException {
        printAndFlush("Enter username:\n");
        String userName = reader.readLine();
        printAndFlush("Enter password:\n");
        String password = reader.readLine();
        if (service.signUp(userName, password))
            printAndFlush("Successful!\n");
    }

    private void printAndFlush(String text) throws IOException {
        writer.write(text);
        writer.flush();
    }
}
