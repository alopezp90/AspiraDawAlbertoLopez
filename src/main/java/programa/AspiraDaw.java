package programa;

import javax.swing.ImageIcon;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.time.temporal.ChronoUnit;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.util.Arrays;

/**
 * @author Alberto López Puertas
 * <alopezp90@gmail.com>
 */
public class AspiraDaw {

    public static final ImageIcon ICONO = new ImageIcon("src/main/resources/icon/icon_96x.jpg");
    public static final ImageIcon ALERT = new ImageIcon("src/main/resources/icon/alert_96x.jpg");
    public static final ImageIcon CONFIG = new ImageIcon("src/main/resources/icon/settings_96x.jpg");
    public static final ImageIcon BAT0 = new ImageIcon("src/main/resources/icon/battery0_96x.jpg");
    public static final ImageIcon BAT30 = new ImageIcon("src/main/resources/icon/battery30_96x.jpg");
    public static final ImageIcon BAT60 = new ImageIcon("src/main/resources/icon/battery60_96x.jpg");
    public static final ImageIcon BAT90 = new ImageIcon("src/main/resources/icon/battery90_96x.jpg");
        
    public static final double MODO1 = 1.5, MODO2 = 2.25, BATMIN = 3;
    public static final String MODOASPIRA = "aspiración", MODOFRIEGA = "fregado";
      
    public static int cantidadEstancia, posicion;
    public static long[] fechaRelativa = new long[5];
    public static long[][] estadoEstanciaTiempo;  
    public static double bateria, modo;
    public static boolean[] estadoEstancia;
    public static Estancia[] estancia;    
    public static String mensaje, modoString;

    public static void main(String[] args) {
        iniciaVivienda();

        estadoEstancia = new boolean[cantidadEstancia];
        Arrays.fill(estadoEstancia, false);
        estadoEstanciaTiempo = new long[cantidadEstancia][5];
        
        modoString = MODOASPIRA;
        modo = MODO1;

        menuPrincipal();
    }

    public static void iniciaVivienda() {
        String ruta = "src/main/resources/saves/";
        String archivo = "save.txt";

        try {
            FileReader fileReader = new FileReader(ruta + archivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            bateria = Double.parseDouble(bufferedReader.readLine());

            cantidadEstancia = Integer.parseInt(bufferedReader.readLine());

            posicion = Integer.parseInt(bufferedReader.readLine());

            estancia = new Estancia[cantidadEstancia];

            for (int i = 0; i < cantidadEstancia; i++) {
                estancia[i] = new Estancia();
                estancia[i].setTipo(Integer.parseInt(bufferedReader.readLine()));
                estancia[i].setNombre(bufferedReader.readLine());
                estancia[i].setSuperficie(Integer.parseInt(bufferedReader.readLine()));
                estancia[i].setFecha(LocalDateTime.parse(bufferedReader.readLine()));
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

    public static void menuPrincipal() {
        int opcion;

        do {
            opcion = JOptionPane.showOptionDialog(
                    null,
                    new JLabel(creaEstado()),
                    "Menú Principal",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    ICONO,
                    new Object[]{"Modo Limpieza", "Modo Carga", "Configuración", "Salir"}, null);

            switch (opcion) {
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
        } while (opcion == 0 || opcion == 1 || opcion == 2);
    }

    public static void modoLimpieza() {
        int opcion;

        do {
            mensaje = "AspiraDaw se encuentra en modo de " + modoString + ", seleccione opcion deseada:";

            opcion = JOptionPane.showOptionDialog(
                    null,
                    new JLabel(mensaje),
                    "Modo Limpieza",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    ICONO,
                    new Object[]{"Modo Aspiración", "Modo Fregado", "Limpieza Completa", "Limpieza Personalizada"}, null);

            switch (opcion) {
                case 0:
                    modoString = MODOASPIRA;
                    modo = MODO1;
                    break;
                case 1:
                    modoString = MODOFRIEGA;
                    modo = MODO2;
                    break;
                case 2:
                    limpiaTodo();
                    break;
                case 3:
                    limpiaAlgo();
                    break;
             }
        } while (opcion == 0 || opcion == 1);
    }

    public static void limpiaTodo() {

    }

    public static void limpiaAlgo() {
        Object[] opciones = new Object[cantidadEstancia];
        for (int i = 0; i < cantidadEstancia; i++) {
            String tmp;
            switch (estancia[i].getTipo()) {
                case 1:
                    tmp = "Salón ";
                    break;
                case 2:
                    tmp = "Cocina";
                    break;
                case 3:
                    tmp = "Baño ";
                    break;
                default:
                    tmp = "Dormitorio ";
            }
            opciones[i] = tmp + estancia[i].getNombre();
        }
        Object opcion = JOptionPane.showInputDialog(
                null,
                "Seleccione estancia a limpiar",
                "Limpieza Personalizada",
                JOptionPane.QUESTION_MESSAGE,
                ICONO,
                opciones,
                null);
    }

    public static boolean limpiable(int i) {
        return estancia[i].getSuperficie() / modo < bateria - BATMIN;
    }

    public static void modoCarga() {
        ImageIcon icono;
        if (bateria < 10) {
            icono = BAT0;
        } else if (bateria < 40) {
            icono = BAT30;
        } else if (bateria < 75) {
            icono = BAT60;
        } else {
            icono = BAT90;
        }
        mensaje = "<html><p align='center'>La batería se encuentra al <u>" + bateria + "%</u><br/>"
                + "¿Desea enviar a ApiraDaw al punto de carga?</p></html>";
        int opcion = JOptionPane.showOptionDialog(
                null,
                new JLabel(mensaje),
                "Modo Carga",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                icono,
                new Object[]{"Enviar", "Volver"}, null);
        if (opcion == 0) {
            bateria = 100;
            posicion = -1;
            mensaje = "<html><p align='center'>AspiraDaw se ha cargado por completo,<br/>"
                    + "espera instrucciones en el punto de carga.</p></html>";
            JOptionPane.showOptionDialog(
                    null,
                    new JLabel(mensaje),
                    "Modo Carga",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    BAT90,
                    new Object[]{"Ok"}, null);
        }
    }

    public static void configuracion() {

    }

    public static boolean menuSalir() {
        int opcion;
        boolean repite = true;
        do {
            opcion = JOptionPane.showOptionDialog(
                    null,
                    "Indique qué desea hacer",
                    "Salir",
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
                    repite = false;
                    break;
            }
        } while (repite);
        JOptionPane.showOptionDialog(
                null,
                "¡Gracias por usar AspiraDaw!",
                "Hasta la próxima",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.DEFAULT_OPTION,
                ICONO,
                new Object[]{"Adios"}, null);
        return true;
    }

    public static void guardaVivienda() {
        File archivo = new File("src/main/resources/saves/save.txt");

        try (PrintWriter printWriter = new PrintWriter(archivo)) {
            printWriter.println(bateria);
            printWriter.println(cantidadEstancia);
            printWriter.println(posicion);
            for (int i = 0; i < cantidadEstancia; i++) {
                printWriter.println(estancia[i].getTipo());
                printWriter.println(estancia[i].getNombre());
                printWriter.println(estancia[i].getSuperficie());
                printWriter.println(estancia[i].getFecha());
            }
            System.out.println("Guardado completo.");
        } catch (IOException ioe) {
            mensaje = "Ha habido un fallo en el guardado.";
            System.out.println(mensaje);
            mensajeError(mensaje);
        }
    }

    public static String creaEstado() {
        String estado, lugar = "", cuantoTiempo = "";
        char SIMBOLO = 9670;
        char FLECHA = 10230;

        if (posicion == -1) {
            lugar = "la base de carga ";
        } else {
            switch (estancia[posicion].getTipo()) {
                case 1:
                    lugar = "el salón " + estancia[posicion].getNombre();
                    break;
                case 2:
                    lugar = "la cocina " + estancia[posicion].getNombre();
                    break;
                case 3:
                    lugar = "el baño " + estancia[posicion].getNombre();
                    break;
                case 4:
                    lugar = "el dormitorio " + estancia[posicion].getNombre();
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
        LocalDateTime fecha = LocalDateTime.now();
        String fechaString = fecha.format(formatter);

        estado = "<html>" + SIMBOLO + " Son las " + fechaString + "<br/>"
                + SIMBOLO + " AspiraDaw se encuentra en " + lugar + " con un " + bateria + "% de batería.<br/><br/>"
                + SIMBOLO + " Estado de la vivienda:<br/><table><tr><td><ul>";

        for (int i = 0; i < estancia.length; i++) {
            switch (estancia[i].getTipo()) {
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
            estado = estado + "<li>" + lugar + "<em>" + estancia[i].getNombre() + "</em></li>";
        }

        estado = estado + "</ul></td><td>";

        for (int i = 0; i < estadoEstancia.length; i++) {
            if (estadoEstancia[i]) {
                estado = estado + "<font color='green'><em>LIMPIO</em></font><br/>";
            } else {
                fechaRelativa = calculaTiempo(i);
                if (fechaRelativa[0] == 1) {
                    cuantoTiempo = fechaRelativa[0] + " año";
                } else if (fechaRelativa[0] > 1) {
                    cuantoTiempo = fechaRelativa[0] + " años";
                } else if (fechaRelativa[1] == 1) {
                    cuantoTiempo = fechaRelativa[1] + " mes";
                } else if (fechaRelativa[1] > 1) {
                    cuantoTiempo = fechaRelativa[1] + " meses";
                } else if (fechaRelativa[2] == 1) {
                    cuantoTiempo = fechaRelativa[2] + " dia";
                } else if (fechaRelativa[2] > 1) {
                    cuantoTiempo = fechaRelativa[2] + " dias";
                } else if (fechaRelativa[3] == 1) {
                    cuantoTiempo = fechaRelativa[3] + " hora";
                } else if (fechaRelativa[3] > 1) {
                    cuantoTiempo = fechaRelativa[3] + " horas";
                } else if (fechaRelativa[4] == 1) {
                    cuantoTiempo = fechaRelativa[4] + " minuto";
                } else if (fechaRelativa[4] > 1) {
                    cuantoTiempo = fechaRelativa[4] + " minutos";
                }
                estado = estado + FLECHA + " <font color='red'>" + cuantoTiempo + "</font><br/>";
                estadoEstanciaTiempo[i] = fechaRelativa;
            }
        }

        estado = estado + "</td><tr/></table></html>";

        return estado;
    }

    public static void mensajeError(String mensaje) {
        JOptionPane.showOptionDialog(
                null,
                mensaje,
                "ERROR",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.ERROR_MESSAGE,
                ALERT,
                new Object[]{"Ok"}, null);
    }

    public static long[] calculaTiempo(int i) {
        long[] tiempo = new long[5];
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime anterior = estancia[i].getFecha();

        LocalDateTime tempDateTime = LocalDateTime.from(anterior);

        tiempo[0] = tempDateTime.until(ahora, ChronoUnit.YEARS);
        tempDateTime = tempDateTime.plusYears(tiempo[0]);

        tiempo[1] = tempDateTime.until(ahora, ChronoUnit.MONTHS);
        tempDateTime = tempDateTime.plusYears(tiempo[1]);

        tiempo[2] = tempDateTime.until(ahora, ChronoUnit.DAYS);
        tempDateTime = tempDateTime.plusYears(tiempo[2]);

        tiempo[3] = tempDateTime.until(ahora, ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusYears(tiempo[3]);

        tiempo[4] = tempDateTime.until(ahora, ChronoUnit.MINUTES);

        return tiempo;
    }
}
