package programa;

import java.time.LocalDateTime;

/**
 * @author Alberto López Puertas
 * <alopezp90@gmail.com>
 */
public class Estancia {

    int tipo, superficie;
    String nombre;
    LocalDateTime fecha;

    public Estancia(int tipo, String nombre, int superficie, LocalDateTime fecha) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.superficie = superficie;
        this.fecha = fecha;
    }

}
