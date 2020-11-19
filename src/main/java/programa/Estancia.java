package programa;

import java.time.LocalDateTime;

/**
 * @author Alberto López Puertas
 * <alopezp90@gmail.com>
 */
public class Estancia {

    private int tipo, superficie;
    private String nombre;
    private LocalDateTime fecha;

    public Estancia(int tipo, String nombre, int superficie, LocalDateTime fecha) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.superficie = superficie;
        this.fecha = fecha;
    }
    
    public Estancia() {
        this.tipo = 0;
        this.nombre = "";
        this.superficie = 0;
        this.fecha = null;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

}
