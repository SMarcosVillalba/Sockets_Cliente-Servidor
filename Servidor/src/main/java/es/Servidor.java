package es;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Servidor
{
    public static void main( String[] args )
    {

        Tienda tienda = new Tienda();
        importarCSVVideouego(tienda);

        try {
            ServerSocket servidor = new ServerSocket();
            InetSocketAddress direccion = new InetSocketAddress("192.168.1.24", 2000);
            servidor.bind(direccion);
            System.out.println("Dirección IP: " + direccion.getAddress());
            while (true) {
                Socket enchufeAlCliente = servidor.accept();
                System.out.println("Comunicación entrante");
                new HiloEscuchador(enchufeAlCliente, tienda);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static List<Videojuego> importarCSVVideouego(Tienda tienda) {
        List<Videojuego> videojuegos = new ArrayList<Videojuego>();

        try {
            Scanner sc = new Scanner(new File("videojuegos.csv"));


            while (sc.hasNextLine()) {
                String subcategoria = sc.nextLine();
                Scanner linea = new Scanner(subcategoria);
                linea.useDelimiter(";");
                String id = linea.next();
                String nombre = linea.next();
                String pegi = linea.next();
                String tematica = linea.next();
                String precio = linea.next();
                String plataforma = linea.next();


                tienda.altaVideojuego(new Videojuego(Integer.parseInt(id), nombre, pegi, tematica, Integer.parseInt(precio), plataforma));
            }

            sc.close();


        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }

        return videojuegos;

    }
}
