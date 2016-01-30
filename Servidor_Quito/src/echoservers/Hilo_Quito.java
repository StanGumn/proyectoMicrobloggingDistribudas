/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echoservers;

import controlador.PublicacionJpaController;
import java.io.*;
import java.net.*;
import java.lang.Thread;
import java.util.StringTokenizer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

public class Hilo_Quito extends Thread {

    long threadID;
    Socket client;

    Hilo_Quito(Socket client) {
        this.client = client;
    }

    public void insertarPublicacion(String descripconPublicacion, String geo) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Servidor_QuitoPU");
            PublicacionJpaController obj = new PublicacionJpaController(emf);
            Publicacion objPublicacion = new Publicacion();
            objPublicacion.setDescripconPublicacion(descripconPublicacion);
            objPublicacion.setLugGeoPublicacion(geo);
            objPublicacion.setIdPublicacion(50);
            //System.out.println("entra" + objPublicacion);
            obj.create(objPublicacion);
//            JOptionPane.showMessageDialog(null, "Publicacion exitosa");
        } catch (Exception e) {
            System.out.println("no funciona el servidor de quito");
        }

    }

    public void run() {

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            //BufferedReader readerID = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
            //writer.println("[Escribe 'topez' para cerrar la conexion]");
            threadID = currentThread().getId();

            while (true) {

                String line = reader.readLine();
                String ciudad = "";
                String mensaje = "";
                StringTokenizer trozeado = new StringTokenizer(line, "-");

                while (trozeado.hasMoreTokens()) {

                    mensaje = trozeado.nextToken();
                    ciudad = trozeado.nextToken();
                    insertarPublicacion(mensaje, ciudad);

                }
                System.out.println(threadID + ": " + mensaje + " - " + ciudad);
                if (line.trim().equals("escape")) {
                    writer.println("Adios");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("\n");
        } finally {
            try {
                client.close();
            } catch (Exception e) {
                System.out.println("ERROR EN LA LECTURA");
            }
        }
    }
}
