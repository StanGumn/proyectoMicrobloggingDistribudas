package echoservers;

import java.io.*;
import java.net.*;
import java.lang.Thread;

public class Servidor_Quito {

    public static void main(String[] args) {
        Socket[] socketes;
        try {
            ServerSocket server = new ServerSocket(6666);
            while (true) {
                Socket cliente = server.accept();
                Hilo_Quito objetoHilo = new Hilo_Quito(cliente);
                objetoHilo.start();
            }
        } catch (Exception e) {
            //System.err.println("Exception caught:" + e);
        }
    }
}