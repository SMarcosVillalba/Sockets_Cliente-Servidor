package es;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketCliente {
    private static ObjectInputStream entrada;
    private static ObjectOutputStream salida;

    public static void main(String[] args) {
        System.out.println(" APLICACI�N CLIENTE");
        System.out.println("-----------------------------------");
        Scanner lector = new Scanner(System.in);
        try {
            Socket cliente = new Socket();
            InetSocketAddress direccionServidor = new InetSocketAddress("192.168.1.24", 2000);
            System.out.println("Esperando a que el servidor acepte la conexi�n");
            cliente.connect(direccionServidor);
            // Conectamos con el servidor.
            System.out.println("Comunicaci�n establecida con el servidor");

            salida = new ObjectOutputStream(cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());

            String id = "";
            while (!id.equals("FIN")) {
                System.out.println("ID del videojuego a consultar (FIN para terminar): ");
                id = lector.nextLine();
                // Envio consulta al servidor.
                salida.writeObject(id);
                // Recibo respuesta del servidor.
                Object vid = entrada.readObject();
                if (vid instanceof String) {
                    System.out.println(vid);
                    if (id.equals("FIN")) break;
                }
                else {
                    Videojuego videojuego = (Videojuego) vid;
                    System.out.println("Nombre videojuego: " + videojuego.getNombre());
                    System.out.println("PEGI: " + videojuego.getPegi());
                    System.out.println("Temática: " + videojuego.getTematica());
                    System.out.println("Precio: " + videojuego.getPrecio());
                    System.out.println("Plataforma: " + videojuego.getPlataforma());

                }
            }
            entrada.close();
            salida.close();
            cliente.close();
            System.out.println("Comunicación cerrada");
        } catch (UnknownHostException e) {
            System.out.println("No se puede establecer comunicación con el servidor");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S");
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        lector.close();
    }
}
