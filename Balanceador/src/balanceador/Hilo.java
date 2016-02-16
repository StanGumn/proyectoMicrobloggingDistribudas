/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balanceador;

import java.io.*;
import java.net.*;
import java.lang.Thread;
import java.util.*;
//FUNCIONServidor1

public class Hilo extends Thread {

    long threadID;
    Socket client;

    Hilo(Socket client) {
        this.client = client;
    }

    public void run() {

        try {
            ArrayList<String> listaDeMensajes = new ArrayList<String>();
            int identificadorDestinatario;

            BufferedReader leerEntrada = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
            //writer.println("Para cerrar coneion, escriba chao");

            threadID = currentThread().getId();
            /*writer.println("Ingrese el ID del destinatario\n");
            BufferedReader readerID = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    String lineaID = readerID.readLine();
                    
                    
                    identificadorDestinatario= Integer.parseInt(lineaID);
                
                    System.out.println(identificadorDestinatario);*/
            do {
                String lineaEntrada = leerEntrada.readLine();
                String ciudad="", mensaje="";
                StringTokenizer lineaTrozeada = new StringTokenizer(lineaEntrada, "-");
                while (lineaTrozeada.hasMoreTokens()){
                    ciudad = lineaTrozeada.nextToken();
                    mensaje = lineaTrozeada.nextToken();
                }
                
                if (lineaEntrada.trim().equals("chao")) {
                    writer.println("chao mijin");
                    break;
                }
                //reader.readLine();

                //listaDeMensajes.add(identificadorDestinatario, line);
                try {
                    
               
                switch (ciudad) {
                    case "QUITO":
                        enviarServidorQuito(mensaje, ciudad);
//                        enviarServidorAlterno(mensaje,ciudad);
                        break;
                    case "GUAYAQUIL":
                        enviarServidorGuayaquil(mensaje, ciudad);
                        break;
                    case "CUENCA":
                        enviarServidorCuenca(mensaje, ciudad);
                        break;
                }
                
                 } catch (Exception e) {
                     System.out.println("solucionar el problema el servidor de quito dejo de funcionar ");
                }

//                System.out.println(threadID + ": " + line);
//                writer.println(threadID + " " + line);
                /*writer.println("Escriba el ID del destinatario");
                String destinatarioID = readerID.readLine();
                if (destinatarioID.trim().equals("10")) {
                System.out.println("DEstinatario ID:" + threadID);
                writer.println("bye!");
                break;
                }*/
            } while (true);
        } catch (Exception e) {
            System.out.println("Gracias por usar Pichones Publica");
        } finally {
            try {
                client.close();
            } catch (Exception e) {;
            }
        }
    }
    

    public void enviarServidorQuito(String mensaje, String ciudad) {
        String hostName = "192.168.10.3";
        int portNumber = 6666;

        try (
                Socket echoSocket = new Socket(hostName, portNumber);
                PrintWriter out
                = new PrintWriter(echoSocket.getOutputStream(), true);) {
            out.println(mensaje+"-"+ciudad);
            System.out.println("Servidor quito funcionando");
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to "
                    + hostName);
            System.out.println("el servidor de Quito dejo de funcionar solucionar problema");
            enviarServidorAlterno(mensaje, ciudad);
//            System.exit(1);
        }
    }
    
     public void enviarServidorAlterno(String mensaje, String ciudad) {
        String hostName = "192.168.10.3";
        int portNumber = 6665;

        try (
                Socket echoSocket = new Socket(hostName, portNumber);
                PrintWriter out
                = new PrintWriter(echoSocket.getOutputStream(), true);) {
            out.println(mensaje+"-"+ciudad);
            System.out.println("Servidor alterno funcionando");
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to "
                    + hostName);
            System.exit(1);
        }
    }
//servidor alterno en caso de aberias 
    public void enviarServidorCuenca(String mensaje, String ciudad) {
        String hostName = "192.168.10.3";
        int portNumber = 6667;
        try (
                Socket echoSocket = new Socket(hostName, portNumber);
                PrintWriter out
                = new PrintWriter(echoSocket.getOutputStream(), true);) {
            out.println(mensaje+"-"+ciudad);
            System.out.println("Servidor Cuenca funcionando");
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to "
                    + hostName);
            System.out.println("el servidor de Cuenca dejo de funcionar solucionar problema");
             enviarServidorAlterno(mensaje, ciudad);
             
//            System.exit(1);
        }
    }

    public void enviarServidorGuayaquil(String mensaje, String ciudad) {
        String hostName = "192.168.10.3";
        int portNumber = 6668;

        try (
                Socket echoSocket = new Socket(hostName, portNumber);
                PrintWriter out
                = new PrintWriter(echoSocket.getOutputStream(), true);) {
            out.println(mensaje+"-"+ciudad);
            System.out.println("Servidor Guayaquil funcionando");
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to "
                    + hostName);
            System.out.println("el servidor de Guayaquil dejo de funcionar solucionar problema");
             enviarServidorAlterno(mensaje, ciudad);
             
//            System.exit(1);
        }
    }
}
