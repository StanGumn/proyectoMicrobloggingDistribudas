package echoservers;

import java.io.*;
import java.net.*;
import java.lang.Thread;

public class Servidor_Respaldo {

    public static void main(String[] args) {
        Socket[] socketes;
        try {
            ServerSocket server = new ServerSocket(6665);
            while (true) {
                Socket cliente = server.accept();
                Hilo_Respaldo objetoHilo = new Hilo_Respaldo(cliente);
                objetoHilo.start();
            }
        } catch (Exception e) {
            //System.err.println("Exception caught:" + e);
        }
    }
}