package programa;

import java.time.LocalDateTime;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;

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
    public static int cantidadEstancia;
    public static int[] tipoEstancia;
    public static int[] supEstancia;
    public static String[] nombreEstancia;
    public static LocalDateTime[] fechaEstancia;

    public static void main(String[] args) {
//        for (int i = 0; i < tipoEstancia.length; i++) {
//            fechaEstancia[i] = LocalDateTime.now();
//        }
        carga();
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
    }

    public static void guarda() {
        File archivo = new File("save.txt");
        try {
            PrintWriter save = new PrintWriter(archivo);
            save.println(cantidadEstancia);
            for (int i = 0; i < cantidadEstancia; i++) {
                save.println(tipoEstancia[i]);
            }
            for (int i = 0; i < cantidadEstancia; i++) {
                save.println(supEstancia[i]);
            }
            for (int i = 0; i < cantidadEstancia; i++) {
                save.print(nombreEstancia[i]);
                save.println();
            }
            for (int i = 0; i < cantidadEstancia; i++) {
                save.print(fechaEstancia[i]);
                save.println();
            }
            save.close();
            System.out.println("Guardado completo.");
        } catch (IOException ioe) {
            System.out.println("Ha habido un error en el guardado.");
        }
    }

    public static void carga() {
        try {
            FileReader fileReader = new FileReader("save.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String linea;
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