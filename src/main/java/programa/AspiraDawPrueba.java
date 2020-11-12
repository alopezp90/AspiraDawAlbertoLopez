package programa;

import java.time.LocalDateTime;
import java.io.*;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

/**
 * @author Alberto López Puertas
 * <alopezp90@gmail.com>
 */
public class AspiraDawPrueba {

    public static int bateria, cantidadEstancia;
    public static int[] tipoEstancia, supEstancia;
    public static String[] nombreEstancia;
    public static LocalDateTime[] fechaEstancia;
    public static boolean[] estadoEstancia;
    
    public static final ImageIcon ICONO = new ImageIcon("src/main/resources/icon/icon_96x.jpg");
    public static final ImageIcon ALERT = new ImageIcon("src/main/resources/icon/alert_96x.jpg");
    public static final ImageIcon CONFIG = new ImageIcon("src/main/resources/icon/settings_96x.jpg");
    public static final ImageIcon BAT0 = new ImageIcon("src/main/resources/icon/battery0_96x.jpg");
    public static final ImageIcon BAT30 = new ImageIcon("src/main/resources/icon/battery30_96x.jpg");
    public static final ImageIcon BAT60 = new ImageIcon("src/main/resources/icon/battery60_96x.jpg");
    public static final ImageIcon BAT90 = new ImageIcon("src/main/resources/icon/battery90_96x.jpg");

    public static void main(String[] args) {
        boolean continua = true;
        int opcion;
        
        iniciaVivienda();        
        while (continua){
            opcion = menuPrincipal();
            switch (opcion){
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
    }

    public static void iniciaVivienda() {
        String path = "src/main/resources/saves/";
        String archivo = "save.txt";
        boolean errorArchivo = false;
        while (!errorArchivo) {
            try {
                FileReader fileReader = new FileReader(path + archivo);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String linea;
                
                linea = bufferedReader.readLine();
                bateria = Integer.parseInt(linea);
                
                linea = bufferedReader.readLine();
                cantidadEstancia = Integer.parseInt(linea);

                tipoEstancia = new int[cantidadEstancia];
                for (int i = 0; i < cantidadEstancia; i++) {
                    linea = bufferedReader.readLine();
                    tipoEstancia[i] = Integer.parseInt(linea);
                }
                supEstancia = new int[cantidadEstancia];
                for (int i = 0; i < cantidadEstancia; i++) {
                    linea = bufferedReader.readLine();
                    supEstancia[i] = Integer.parseInt(linea);
                }
                nombreEstancia = new String[cantidadEstancia];
                for (int i = 0; i < cantidadEstancia; i++) {
                    nombreEstancia[i] = bufferedReader.readLine();
                }
                fechaEstancia = new LocalDateTime[cantidadEstancia];
                for (int i = 0; i < cantidadEstancia; i++) {
                    linea = bufferedReader.readLine();
                    fechaEstancia[i] = LocalDateTime.parse(linea);
                }
                break;
            } catch (IOException ioe) {
                System.out.println("Ha habido un error en la carga, se intentará cargar vivienda por defecto.");
                if (archivo.equals("default.txt")) {
                    System.out.println("Se ha producido un error al cargar la vivienda por defecto. Defina vivienda nueva en menú de configuración");
                    errorArchivo = true;
                }
                archivo = "default.txt";
            } catch (NullPointerException e) {
                System.out.println("Ha habido un error NullPointerException, se intentará cargar vivienda por defecto.");
                if (archivo.equals("default.txt")) {
                    System.out.println("Se ha producido un error al cargar la vivienda por defecto. Defina vivienda nueva en menú de configuración");
                    errorArchivo = true;
                }
                archivo = "default.txt";
            }
        }
    }
    
    
    public static int menuPrincipal() {
        int opcion = 3;
        
        return opcion;
    }
    
    public static void modoLimpieza(){
        
    }
    
    public static void modoCarga(){
        
    }
    
    public static void configuracion(){
        
    }
    
    public static void menuSalir(){
        int opcion;
        boolean repite = true;
        while (repite) {
            opcion = JOptionPane.showOptionDialog(
                    null, 
                    "Indique qué desea hacer:", 
                    "Hasta la próxima",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    ICONO,
                    new Object[] { "Guardar y salir", "Salir sin guardar"},null);
            switch (opcion){
                case 0:
                    repite = false;
                    guardaVivienda();
                    break;
                case 1:
                    JOptionPane.showConfirmDialog(null, "Gracias por usar AspiraDaw!", "Hasta la próxima", JOptionPane.DEFAULT_OPTION);
                    repite = false;
                    break;
            }
        }
    }
    
    public static void guardaVivienda(){
        File archivo = new File("src/main/resources/saves/save.txt");
        try {
            PrintWriter printWriter = new PrintWriter(archivo);
            printWriter.println(bateria);
            printWriter.println(cantidadEstancia);
            for (int i = 0; i < cantidadEstancia; i++) {
                printWriter.println(tipoEstancia[i]);
            }
            for (int i = 0; i < cantidadEstancia; i++) {
                printWriter.println(supEstancia[i]);
            }
            for (int i = 0; i < cantidadEstancia; i++) {
                printWriter.print(nombreEstancia[i]);
                printWriter.println();
            }
            for (int i = 0; i < cantidadEstancia; i++) {
                printWriter.print(fechaEstancia[i]);
                printWriter.println();
            }
            printWriter.close();
            System.out.println("Guardado completo.");
        } catch (IOException ioe) {
            System.out.println("Ha habido un error en el guardado.");
        }
    }
    

}
