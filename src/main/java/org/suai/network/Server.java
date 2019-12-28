package org.suai.network;

import javafx.util.Pair;
import java.net.*;
import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) throws IOException {
        int portNumber = Integer.parseInt("9876");
        ArrayDeque<Room> rooms = new ArrayDeque<>();
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            while(true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    ClientThread cliThread = new ClientThread(clientSocket, rooms);
                    cliThread.start();
                }
                catch(IOException ioe) { }
            }
        } catch (IOException e) { }
    }
}