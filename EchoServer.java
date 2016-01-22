/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.lang.Thread;
import java.net.*;
import java.io.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.util.Random;
 
public class EchoServer {
  public static void main(String[] args) throws IOException {
    try {
      ServerSocket server = new ServerSocket(5566);
      
      while (true) {
        Socket client = server.accept();
        miThread thread = new miThread(client);
        Archivo sonda = new Archivo();
        sonda.medir();

        Random rnd = new Random();
        thread.start();

        

      }
    }
    catch (Exception e) {
      System.err.println("Exception caught:" + e);
    }
  }
}
