package org.example;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerMain {

    static ArrayList<City> cities = new ArrayList<City>();
    static void buildCitiesList() {
        cities.add(new City(3,"Toronto",15.9));
        cities.add(new City(33,"Milan",25.94));
        cities.add(new City(55,"Rome",35.4));
        System.out.println(cities);
    }

    static String ritorno(String s) {
        String ss=null;
        switch (s) {
            case "hottest":
                ss=cities.get(2).toString();
                break;
            case "all":
                ss=cities.toString();
                break;
            case "sorted_by_name":
                cities.sort((o1, o2) -> {
                    return o1.getName().compareTo(o2.getName());
                });
                Gson gson = new Gson();
                ss = gson.toJson(cities);
                break;
            case "sorted_by_temp":
                cities.sort((o1, o2) -> {
                    return o1.getTemp().compareTo(o2.getTemp());
                });
                Gson gson2 = new Gson();
                ss = gson2.toJson(cities);
                break;
        }
        return ss;
    }

    static Boolean readLoop(BufferedReader in,  PrintWriter out ){
        // waits for data and reads it in until connection dies
        // readLine() blocks until the server receives a new line from client
        String s = "";
        try {
            while ((s = in.readLine()) != null) {
                System.out.println(s);
                String ret= ritorno(s);
                out.println(ritorno(s));
                out.flush();
            }

            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
    public static void main( String[] args ){
        buildCitiesList();
        ServerSocket serverSocket = null;
        try {
            serverSocket= new ServerSocket(1234);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Socket clientSocket = null;
        try {
            clientSocket=serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter out = null; // allocate to write answer to client.
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            readLoop(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Server done!");

    }

    }
