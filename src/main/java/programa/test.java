package programa;

import java.time.LocalDateTime;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;
import javax.swing.*;

/**
 * @author Alberto López Puertas
 * <alopezp90@gmail.com>
 */
public class test {

//    public static int cantidadEstancia = 5;
//    public static int[] tipoEstancia = {1, 2, 3, 4, 4};
//    public static int[] supEstancia = {10, 8, 6, 12, 13};
//    public static String[] nombreEstancia = {"principal", "", "pequeño", "Paco", "Antonio"};
//    public static LocalDateTime[] fechaEstancia = new LocalDateTime[tipoEstancia.length];
    public static final ImageIcon ICONO = new ImageIcon("src/main/resources/icon/icon_96x.jpg");
    
    public static int bateria;
    public static int cantidadEstancia;
    public static int[] tipoEstancia;
    public static int[] supEstancia;
    public static String[] nombreEstancia;
    public static LocalDateTime[] fechaEstancia;

    public static void main(String[] args) {
//        for (int i = 0; i < tipoEstancia.length; i++) {
//            fechaEstancia[i] = LocalDateTime.now();
//        }
//        guarda();
        carga();
        System.out.println(bateria);
        System.out.println(cantidadEstancia);
        for (int i = 0; i < cantidadEstancia; i++) {
            System.out.println(tipoEstancia[i]);
        }
        for (int i = 0; i < cantidadEstancia; i++) {
            System.out.println(supEstancia[i]);
        }
        for (int i = 0; i < cantidadEstancia; i++) {
            System.out.println(nombreEstancia[i]);
        }
        for (int i = 0; i < cantidadEstancia; i++) {
            System.out.println(fechaEstancia[i]);
        }

//        if (nombreEstancia[1].equals("")) {
//            System.out.println("cocina esta vacio");
//        }
        menuSalir();
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
                    guarda();
                    break;
                case 1:
                    repite = false;
                    break;
            }
        }
    }

    public static void guarda() {
        File archivo = new File("src/main/resources/saves/save.txt");
        try {
            PrintWriter printWriter = new PrintWriter(archivo);
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

    public static void carga() {
        try {
            FileReader fileReader = new FileReader("src/main/resources/saves/save.txt");
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
        } catch (IOException ioe) {
            System.out.println("Ha habido un error en la carga");
        } catch (NullPointerException e) {
            System.out.println("Ha habido un error NullPointerException");
        }
    }
}
