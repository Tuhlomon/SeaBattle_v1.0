package org.suai.network;

import java.sql.*;
import java.util.logging.*;
import java.util.Scanner;

public class DataBase {
    private Connection connection = null;
    private Statement statement = null;
    public DataBase(){
        try{
            Class.forName("org.postgresql.Driver");
            System.out.println("Драйвер подключен");
            connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/table", "postgres", "1707");
            System.out.println("Соединение установлено");
            statement = connection.createStatement();
        } catch (Exception ex) {
            //выводим наиболее значимые сообщения
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getInfo(int id){
        String response = "";
        try {
            ResultSet result = statement.executeQuery("SELECT * FROM users");
            while (result.next()) {
                if(id==result.getInt("ID")){
                    response = "Nickname: " + result.getString("Nickname") + " Wins: " + result.getInt("Wins") + " Total: " + result.getInt("Total");
                }
            }
        } catch (Exception ex) {
            //выводим наиболее значимые сообщения
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    public int authorization(String lognpass) {
        try {
            String[] str = lognpass.split(" ");
            ResultSet result = statement.executeQuery("SELECT * FROM users");
            while(result.next()){
                String login = result.getString("Nickname");
                String password = result.getString("Password");
                if(str[0].equals(login) && str[1].equals(password)) return result.getInt("ID");
            }
        } catch (Exception ex) {
            //выводим наиболее значимые сообщения
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int registration(String lognpass) {
        try {
            String[] str = lognpass.split(" ");
            statement.executeUpdate("INSERT INTO users (Nickname, Password) VALUES('" + str[0] + "'" + ",'" + str[1] + "')");
            return 0;
        } catch (Exception ex) {
            //выводим наиболее значимые сообщения
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }
}