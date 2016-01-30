package echoservers;

import java.io.*;
import java.net.*;
import java.lang.Thread;

public class Servidor_Guayaquil {

    public static void main(String[] args) {
        Socket[] socketes;
        try {
            ServerSocket server = new ServerSocket(6668);
            while (true) {
                Socket cliente = server.accept();
                Hilo_Guayaquil objetoHilo = new Hilo_Guayaquil(cliente);
                objetoHilo.start();
            }
        } catch (Exception e) {
            //System.err.println("Exception caught:" + e);
        }
    }
}