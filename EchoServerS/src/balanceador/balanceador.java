package balanceador;

import java.io.*;
import java.net.*;
import java.lang.Thread;

public class balanceador {

    public static void main(String[] args) {
        //Socket[] socketes = new Socket[3];
        try {
            ServerSocket server = new ServerSocket(8889);
            while (true) {
                Socket cliente = server.accept();
                Hilo objetoHilo = new Hilo(cliente);
                objetoHilo.start();
            }
        } catch (Exception e) {
            System.err.println("Exception caught:" + e);
        }
    }
}