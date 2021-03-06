/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echoservers;
import controlador.PublicacionJpaController;
//import entidad.Publicacion;
import java.io.*;
import java.net.*;
import java.lang.Thread;
import static java.lang.Thread.currentThread;
import java.util.StringTokenizer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

public class Hilo_Guayaquil extends Thread {

    long threadID;
    Socket client;

    Hilo_Guayaquil(Socket client) {
        this.client = client;
    }

    public void insertarPublicacion(String descripconPublicacion, String geo) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Servidor_GuayaquilPU");
            PublicacionJpaController obj = new PublicacionJpaController(emf);
            Publicacion objPublicacion = new Publicacion();
            objPublicacion.setDescripconPublicacion(descripconPublicacion);
            objPublicacion.setLugGeoPublicacion(geo);
            objPublicacion.setIdPublicacion(511);
            System.out.println("entra" + objPublicacion);
            obj.create(objPublicacion);
            JOptionPane.showMessageDialog(null, "Publicacion exitosa");
        } catch (Exception e) {
            System.out.println("no vale el insertar publicacion en guayaquil");
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
                    System.out.println(threadID + ": " + mensaje + " - " + ciudad);

                }

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
