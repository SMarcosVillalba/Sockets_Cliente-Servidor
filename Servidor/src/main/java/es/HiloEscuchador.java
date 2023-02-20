package es;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HiloEscuchador implements Runnable {
    private Thread hilo;
    private static int numCliente = 0;
    private Socket enchufeAlCliente;
    private Tienda tienda;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;

    public HiloEscuchador(Socket cliente, Tienda tienda) throws IOException {
        numCliente++;
        hilo = new Thread(this, "Videojuego" + numCliente);
        this.enchufeAlCliente = cliente;
        this.tienda = tienda;
        hilo.start();
    }

    @Override
    public void run() {
        System.out.println("Estableciendo comunicaci�n con " + hilo.getName());
        try {
            salida = new ObjectOutputStream(enchufeAlCliente.getOutputStream());
            entrada = new ObjectInputStream(enchufeAlCliente.getInputStream());
            String id;
            do {
                id = (String) entrada.readObject();
                if (id.trim().equals("FIN")) {
                    salida.writeObject("Hasta pronto, gracias por establecer conexión");
                    System.out.println(hilo.getName() + " ha cerrado la comunicación");
                } else {
                    System.out.println(hilo.getName() + " consulta el videojuego: " + id);
                    // Enviamos el objeto correspondiente al alumno consultado.

                    Videojuego vid = tienda.getVideojuegos().get(id);


                    if (vid==null) {
                        salida.writeObject("Videojuego no encontrado");
                    }
                    else {
                        salida.writeObject(vid);
                    }
                }
            } while ((!id.trim().equals("FIN")));
            entrada.close();
            salida.close();
            enchufeAlCliente.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
