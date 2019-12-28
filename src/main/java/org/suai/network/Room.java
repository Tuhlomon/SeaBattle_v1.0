package org.suai.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class Room {
    private Socket[] player = new Socket[2];
    private BufferedReader[] input = new BufferedReader[2];
    private PrintWriter[] output = new PrintWriter[2];
    private int i = 0;
    private boolean exit = false;

    public Room(Socket play){
        try {
            player[0] = play;
            input[0] = new BufferedReader(new InputStreamReader(player[0].getInputStream()));
            output[0] = new PrintWriter(player[0].getOutputStream(), true);
            Random rand = new Random(System.currentTimeMillis());
            i = rand.nextInt(2);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void addOpponent(Socket oppon){
        try {
            player[1] = oppon;
            input[1] = new BufferedReader(new InputStreamReader(player[1].getInputStream()));
            output[1] = new PrintWriter(player[1].getOutputStream(), true);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public int doGame(){
        try {
            output[0].println((char)107);
            output[1].println((char)107);
            int response = 0;
            while (response != 108){
                response = input[0].read();
                input[0].readLine();
            }
            response = 0;
            while (response != 108){
                response = input[1].read();
                input[1].readLine();
            }
            response = 0;
            int shot = 0;
            output[i++%2].println((char)0);
            output[i++%2].println((char)1);
            while (true) {
                shot = input[i % 2].read();
                input[i++ % 2].readLine();
                output[i % 2].println((char) shot);
                response = input[i % 2].read();
                input[i++ % 2].readLine();
                output[i % 2].println((char) response);
                if (response == 5) {
                    output[i++ % 2].println((char) 167); // победитель
                    output[i++ % 2].println((char) 168); // проигравший
                    i = i % 2;
                    System.out.println("WINNER IS " + i);
                    exit = true;
                    return i; //if 0 - win
                }
                if (response == 2){
                    i = (i+1) % 2;
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("WINNER IS " + i);
        return i;
    }

    public int exitGame(){
        while (!exit){

        }
        return i; //if 1 - win
    }
}
