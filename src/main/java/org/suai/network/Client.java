package org.suai.network;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private String hostName = "localhost";
    private int portNumber = Integer.parseInt("9876");
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private int myID = -1;

    public Client(){
        try{
            socket = new Socket(hostName, portNumber);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println("HELLO");
        }
        catch (UnknownHostException e) {
            System.out.println("CONNECTING ERROR" + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    public int getID(){
        return myID;
    }

    public void sent(int x){
        out.println((char)x);
    }

    public void sentString(String s){
        out.println(s);
    }

    public int authorization(String lognpas){
        out.println((char)253);
        out.println(lognpas);
        myID = give();
        if (myID == 65535) myID = -1;
        return myID;
    }

    public boolean registration(String lognpass){
        out.println((char)254);
        out.println(lognpass);
        int response = give();
        if (response == 1) return false;
        return true;
    }

    public String getInfo(){
        out.println((char)252);
        out.println((char)myID);
        String response = giveString();
        return response;
    }

    public int give(){
        try {
            int x = in.read();
            in.readLine();
            return x;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public boolean isReady(){
        try {
            return in.ready();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public String giveString(){
        try {
            return in.readLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

}
