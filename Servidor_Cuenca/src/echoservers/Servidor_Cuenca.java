package echoservers;

import java.io.*;
import java.net.*;
import java.lang.Thread;

public class Servidor_Cuenca {

    public static void main(String[] args) {
        Socket[] socketes;
        try {
            ServerSocket server = new ServerSocket(6667);
            while (true) {
                Socket cliente = server.accept();
                Hilo_Cuenca objetoHilo = new Hilo_Cuenca(cliente);
                objetoHilo.start();
            }
        } catch (Exception e) {
            //System.err.println("Exception caught:" + e);
        }
    }
}