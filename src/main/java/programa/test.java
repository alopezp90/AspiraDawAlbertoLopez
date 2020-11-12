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

    public static int bateria = 100;
    public static int cantidadEstancia = 3;
    public static Estancia[] estancia;

    public static void main(String[] args) {

        estancia = new Estancia[cantidadEstancia];

        estancia[0] = new Estancia(1, "Principal", 10, LocalDateTime.now());
        estancia[1] = new Estancia(2, "", 8, LocalDateTime.now());
        estancia[2] = new Estancia(3, "Paco", 12, LocalDateTime.now());

        guardaVivienda();
        iniciaVivienda();
        
        System.out.println(estancia[0].tipo);
        System.out.println(estancia[1].nombre);
        System.out.println(estancia[2].fecha);
    }

    public static void guardaVivienda() { //testear
        File archivo = new File("src/main/resources/saves/savePrueba.txt");
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
    
    public static void iniciaVivienda() {
        int tmpTipo, tmpSuperficie;
        String tmpNombre;
        LocalDateTime tmpFecha;
        String ruta = "src/main/resources/saves/";
        String archivo = "savePrueba.txt";
        
            try {
                FileReader fileReader = new FileReader(ruta + archivo);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                System.out.println("Hasta aqui");
                bateria = Integer.parseInt(bufferedReader.readLine());
                
                cantidadEstancia = Integer.parseInt(bufferedReader.readLine());
                
                estancia = new Estancia[cantidadEstancia];
                System.out.println("Hasta aqui 2");
                for (int i = 0; i < cantidadEstancia; i++) {
                    tmpTipo = Integer.parseInt(bufferedReader.readLine());
                    tmpNombre = bufferedReader.readLine();
                    tmpSuperficie = Integer.parseInt(bufferedReader.readLine());
                    tmpFecha = LocalDateTime.parse(bufferedReader.readLine());
                    estancia[i] = new Estancia (tmpTipo, tmpNombre, tmpSuperficie,tmpFecha);
                    System.out.println("hasta aqui :"+i);
                }
                
                System.out.println("Se ha cargado "+archivo);
            } catch (IOException ioe) {
                System.out.println("Ha habido un error en la carga, se intentará cargar vivienda por defecto.");
                if (archivo.equals("default.txt")) {
                    System.out.println("Se ha producido un error al cargar la vivienda por defecto. Defina vivienda nueva en menú de configuración");
                }
                archivo = "default.txt";
                
            } catch (NullPointerException e) {
                System.out.println("Ha habido un error NullPointerException, se intentará cargar vivienda por defecto.");
                if (archivo.equals("default.txt")) {
                    System.out.println("Se ha producido un error al cargar la vivienda por defecto. Defina vivienda nueva en menú de configuración");
                }
                archivo = "default.txt";
            }
    }
}
