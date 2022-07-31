package edu.school21.sockets.app;

import edu.school21.sockets.server.Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
       if (args.length == 1 && args[0].startsWith("--port=")
               && args[0].substring(7).matches("\\d+") && args[0].length() < 13) {
           try {
               new Server(Integer.parseInt(args[0].substring(7))).start();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
    }
}
