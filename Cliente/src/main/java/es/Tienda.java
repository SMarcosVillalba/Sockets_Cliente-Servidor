package es;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Tienda implements Serializable {
    private static final long serialVersionUID = 7924546406977610718L;

    Map<String, Videojuego> videojuegos;

    public Tienda(Map<String, Videojuego> videojuegos) {
        this.videojuegos = videojuegos;
    }

    public Tienda() {
        this.videojuegos = new HashMap<String, Videojuego>();
    }

    public void altaVideojuego(Videojuego vid) {
        videojuegos.put(String.valueOf(vid.getId()), vid);
    }

    public Map<String, Videojuego> getVideojuegos() {
        return videojuegos;
    }

    public void setVideojuego(Map<String, Videojuego> videojuegos) {
        this.videojuegos = videojuegos;
    }
}
