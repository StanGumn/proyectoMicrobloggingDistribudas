/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.net.*;
import java.lang.Thread;

public class miThread extends Thread{
  Socket client;
  miThread (Socket client) {
    this.client = client;
  }
  public void run(){
    long threadId = Thread.currentThread().getId();
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
      PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
      //writer.println("");
      
      while (true) {
        String line = reader.readLine();
        if (line.trim().equals("desconectar")) {
          writer.println("Desconectado");
          break;
        }
        System.out.println(" "+threadId +": "+line);
        writer.println("[echo] " + line );
      }
    } catch (Exception e) {
      System.err.println("cliente desconectado");
    } finally {
      try { client.close(); }
      catch (Exception e ){ ; }
    }
  }
}
