/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.*;
import java.net.*;
public class clasePichonPublica {
    

    /**
     * @param args the command line arguments
     */
    
     public void llamarCliente(String mensaje,String ciudad){
         
//        if (args.length != 2) {
//            System.err.println(
//                "Usage: java clasePichonPublica <host name> <port number>");
//            System.exit(1);
//        }
// 
        String hostName = "192.168.10.2";
        int portNumber = 8889;
 
        try (
            Socket echoSocket = new Socket(hostName, portNumber);
            PrintWriter out =
                new PrintWriter(echoSocket.getOutputStream(), true);
//            BufferedReader in =
//                new BufferedReader(
//                    new InputStreamReader(echoSocket.getInputStream()));
//            BufferedReader stdIn =
//                new BufferedReader(
//                    new InputStreamReader(System.in))
        ) {
            
                out.println(ciudad+"-"+mensaje);
                System.out.println( ciudad+"-"+mensaje);
            
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        } 
    }    // TODO code application logic here
    }
    

