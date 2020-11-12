package programa;

import javax.swing.ImageIcon;
import java.time.LocalDateTime;
import java.io.*;
import javax.swing.JOptionPane;

/**
 * @author Alberto López Puertas 
 * <alopezp90@gmail.com>
 */
public class AspiraDaw {
    
    //Inicialización de variables
    public static int bateria, cantidadEstancia;
    public static boolean[] estadoEstancia;
    public static Estancia[] estancia;
    
    //Inicialización de los iconos para los menús de JOption
    public static final ImageIcon ICONO = new ImageIcon("src/main/resources/icon/icon_96x.jpg");
    public static final ImageIcon ALERT = new ImageIcon("src/main/resources/icon/alert_96x.jpg");
    public static final ImageIcon CONFIG = new ImageIcon("src/main/resources/icon/settings_96x.jpg");
    public static final ImageIcon BAT0 = new ImageIcon("src/main/resources/icon/battery0_96x.jpg");
    public static final ImageIcon BAT30 = new ImageIcon("src/main/resources/icon/battery30_96x.jpg");
    public static final ImageIcon BAT60 = new ImageIcon("src/main/resources/icon/battery60_96x.jpg");
    public static final ImageIcon BAT90 = new ImageIcon("src/main/resources/icon/battery90_96x.jpg");
    
    //Estructura lógica principal del programa
    public static void main(String[] args) {
        
    iniciaVivienda();        
        switch (menuPrincipal()){
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

            estancia = new Estancia[cantidadEstancia];

            for (int i = 0; i < cantidadEstancia; i++) {
                tmpTipo = Integer.parseInt(bufferedReader.readLine());
                tmpNombre = bufferedReader.readLine();
                tmpSuperficie = Integer.parseInt(bufferedReader.readLine());
                tmpFecha = LocalDateTime.parse(bufferedReader.readLine());
                estancia[i] = new Estancia (tmpTipo, tmpNombre, tmpSuperficie,tmpFecha);
            }
            System.out.println("Carga de "+archivo+" realizada con éxito.");
            
        } catch (IOException ioe) {
            System.out.println("Ha habido un error en la carga, inicializa desde configuración.");
            if (archivo.equals("default.txt")) {
                System.out.println("Se ha producido un error al cargar la vivienda por defecto. Defina vivienda nueva en menú de configuración");
            }

        } catch (NullPointerException e) {
            System.out.println("Ha habido un error NullPointerException, inicializa desde configuración.");
            if (archivo.equals("default.txt")) {
                System.out.println("Se ha producido un error al cargar la vivienda por defecto. Defina vivienda nueva en menú de configuración");
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
    
    public static boolean menuSalir(){
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
        } while (repite);
        return true;
    }
    
    public static void guardaVivienda(){ //testear
        File archivo = new File("src/main/resources/saves/save.txt");
        try {
            PrintWriter printWriter = new PrintWriter(archivo);
            printWriter.println(bateria);
            printWriter.println(cantidadEstancia);
            for (int i = 0; i < cantidadEstancia; i++) { //probar impresion de objetos
                printWriter.println(estancia[i].tipo);
                printWriter.println(estancia[i].nombre);
                printWriter.println(estancia[i].superficie);
                printWriter.println(estancia[i].fecha);
            }
            printWriter.close();
            System.out.println("Guardado completo.");
        } catch (IOException ioe) {
            System.out.println("Ha habido un error en el guardado.");
        }
    }

}