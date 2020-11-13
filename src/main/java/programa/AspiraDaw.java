package programa;

import javax.swing.ImageIcon;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.util.Arrays;

/**
 * @author Alberto López Puertas
 * <alopezp90@gmail.com>
 */
public class AspiraDaw {

    public static int bateria, cantidadEstancia, posicion;
    public static boolean[] estadoEstancia;
    public static Estancia[] estancia;
    public static String mensaje;

    public static final ImageIcon ICONO = new ImageIcon("src/main/resources/icon/icon_96x.jpg");
    public static final ImageIcon ALERT = new ImageIcon("src/main/resources/icon/alert_96x.jpg");
    public static final ImageIcon CONFIG = new ImageIcon("src/main/resources/icon/settings_96x.jpg");
    public static final ImageIcon BAT0 = new ImageIcon("src/main/resources/icon/battery0_96x.jpg");
    public static final ImageIcon BAT30 = new ImageIcon("src/main/resources/icon/battery30_96x.jpg");
    public static final ImageIcon BAT60 = new ImageIcon("src/main/resources/icon/battery60_96x.jpg");
    public static final ImageIcon BAT90 = new ImageIcon("src/main/resources/icon/battery90_96x.jpg");

    public static void main(String[] args) {

        iniciaVivienda();
        estadoEstancia = new boolean[cantidadEstancia];
        Arrays.fill(estadoEstancia, false);
        switch (menuPrincipal()) {
            case 0:
                modoLimpieza();
                break;
            case 1:
                modoCarga();
                break;
            case 2:
                configuracion();
                break;
            default:
                menuSalir();
        }
    }

    public static void iniciaVivienda() {
        int tmpTipo, tmpSuperficie;
        String tmpNombre;
        LocalDateTime tmpFecha;
        String ruta = "src/main/resources/saves/";
        String archivo = "save.txt";

        try {
            FileReader fileReader = new FileReader(ruta + archivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            bateria = Integer.parseInt(bufferedReader.readLine());

            cantidadEstancia = Integer.parseInt(bufferedReader.readLine());

            posicion = Integer.parseInt(bufferedReader.readLine());

            estancia = new Estancia[cantidadEstancia];

            for (int i = 0; i < cantidadEstancia; i++) {
                tmpTipo = Integer.parseInt(bufferedReader.readLine());
                tmpNombre = bufferedReader.readLine();
                tmpSuperficie = Integer.parseInt(bufferedReader.readLine());
                tmpFecha = LocalDateTime.parse(bufferedReader.readLine());
                estancia[i] = new Estancia(tmpTipo, tmpNombre, tmpSuperficie, tmpFecha);
            }
            System.out.println("Carga de " + archivo + " realizada con éxito.");

        } catch (IOException ioe) {
            mensaje = "Ha habido un error en la carga, inicializa desde configuración.";
            System.out.println(mensaje);
            if (archivo.equals("default.txt")) {
                mensaje = "Se ha producido un error al cargar la vivienda por defecto. Defina vivienda nueva en menú de configuración";
                System.out.println(mensaje);
            }
            mensajeError(mensaje);
            configuracion();

        } catch (NullPointerException e) {
            mensaje = "Ha habido un error NullPointerException, inicializa desde configuración.";
            System.out.println(mensaje);
            if (archivo.equals("default.txt")) {
                mensaje = "Se ha producido un error al cargar la vivienda por defecto. Defina vivienda nueva en menú de configuración";
                System.out.println(mensaje);
            }
            mensajeError(mensaje);
            configuracion();
        }
    }

    public static int menuPrincipal() {
        int opcion;

        opcion = JOptionPane.showOptionDialog(
                null,
                new JLabel(creaEstado()),
                "Menú Principal",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                ICONO,
                new Object[]{"Modo Carga", "Modo LImpieza", "Configuración", "Salir"}, null);
        return opcion;
    }

    public static void modoLimpieza() {

    }

    public static void modoCarga() {

    }

    public static void configuracion() {

    }

    public static boolean menuSalir() {
        int opcion;
        boolean repite = true;
        do {
            opcion = JOptionPane.showOptionDialog(
                    null,
                    "Indique qué desea hacer:",
                    "Hasta la próxima",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    ICONO,
                    new Object[]{"Guardar y salir", "Salir sin guardar"}, null);
            switch (opcion) {
                case 0:
                    repite = false;
                    guardaVivienda();
                    break;
                case 1:
                    JOptionPane.showConfirmDialog(null, "Gracias por usar AspiraDaw!", "Hasta la próxima", JOptionPane.DEFAULT_OPTION);
                    repite = false;
                    break;
            }
        } while (repite);
        return true;
    }

    public static void guardaVivienda() {
        File archivo = new File("src/main/resources/saves/save.txt");

        try (PrintWriter printWriter = new PrintWriter(archivo)) {
            printWriter.println(bateria);
            printWriter.println(cantidadEstancia);
            printWriter.println(posicion);
            for (int i = 0; i < cantidadEstancia; i++) {
                printWriter.println(estancia[i].tipo);
                printWriter.println(estancia[i].nombre);
                printWriter.println(estancia[i].superficie);
                printWriter.println(estancia[i].fecha);
            }
            System.out.println("Guardado completo.");
        } catch (IOException ioe) {
            mensaje = "Ha habido un fallo en el guardado.";
            System.out.println(mensaje);
            mensajeError(mensaje);
        }
    }

    public static String creaEstado() {
        String estado, lugar = "";
        char SIMBOLO = 9670;

        if (posicion == -1) {
            lugar = "la base de carga ";
        } else {
            switch (estancia[posicion].tipo) {
                case 1:
                    lugar = "el salón " + estancia[posicion].nombre;
                    break;
                case 2:
                    lugar = "la cocina " + estancia[posicion].nombre;
                    break;
                case 3:
                    lugar = "el baño " + estancia[posicion].nombre;
                    break;
                case 4:
                    lugar = "el dormitorio " + estancia[posicion].nombre;
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
        LocalDateTime fecha = LocalDateTime.now();
        String fechaString = fecha.format(formatter);

        estado = "<html>" + SIMBOLO + " Son las " + fechaString + "<br/>"
                + SIMBOLO + " AspiraDaw se encuentra en " + lugar + " con un " + bateria + "% de batería.<br/><br/>"
                + SIMBOLO + " Estado de la vivienda:<br/><table><tr><td><ul>";

        for (int i = 0; i < estancia.length; i++) {
            switch (estancia[i].tipo) {
                case 1:
                    lugar = " Salón ";
                    break;
                case 2:
                    lugar = " Cocina ";
                    break;
                case 3:
                    lugar = " Baño ";
                    break;
                case 4:
                    lugar = " Dormitorio ";
            }
            estado = estado + "<li>" + lugar + "<em>" + estancia[i].nombre + "</em></li>";
        }

        estado = estado + "</ul></td><td>";

        for (int i = 0; i < estadoEstancia.length; i++) {
            if (estadoEstancia[i]){
                estado = estado + " ---> <font color='green'>LIMPIO</font><br/>";
            } else {
                estado = estado + " ---> <font color='green'>LIMPIO</font><br/>";
            }
        }
        
        estado = estado + "</td><tr/></table></html>";

        return estado;
    }
    
    public static void mensajeError(String mensaje){
        JOptionPane.showOptionDialog(
                    null,
                    mensaje,
                    "ERROR",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    ALERT,
                    new Object[]{"Ok"}, null);
    }
}
