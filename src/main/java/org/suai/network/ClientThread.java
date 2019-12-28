package org.suai.network;

import javafx.util.Pair;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientThread extends Thread {

    private Socket socket;
    private ArrayDeque<Room> rooms;
    private DataBase db = new DataBase();

    public ClientThread(Socket s, ArrayDeque<Room> r) {
        socket = s;
        rooms = r;
    }

    public void run() {
        try {
            System.out.println("Someone Connected");
            BufferedReader myIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter myOut = new PrintWriter(socket.getOutputStream(), true);
            String inputLine;
            System.out.println(myIn.readLine());
            int number = 0;
            while(true) {
                number = myIn.read();
                myIn.readLine();
                System.out.println(number);
                switch(number){
                    case 252:
                        number = myIn.read();
                        myIn.readLine();
                        myOut.println(db.getInfo(number));
                        break;
                    case 253:
                        inputLine = myIn.readLine();
                        myOut.println((char)db.authorization(inputLine));
                        System.out.println(inputLine);
                        break;
                    case 254:
                        inputLine = myIn.readLine();
                        myOut.println((char)db.registration(inputLine));
                        System.out.println(inputLine);
                        break;
                    case 106:
                        if (rooms.isEmpty()){
                            Room room = new Room(socket);
                            rooms.push(room);
                            System.out.println("Somebody in queue");
                            room.exitGame();
                        }
                        else{
                            Room room = rooms.pop();
                            room.addOpponent(socket);
                            room.doGame();
                        }
                        break;
                    default:
                        System.out.println(number);
                }
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
