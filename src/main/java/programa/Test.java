package programa;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import static programa.AspiraDaw.ALERT;
import java.util.Scanner;
import java.util.Arrays;

/**
 * @author Alberto López Puertas
 * <alopezp90@gmail.com>
 */
public class Test {

    public static void main(String[] args) {
        
        int n;
        
        String [] usuario;
        Scanner sc = new Scanner(System.in);
        System.out.println("Numero de usuarios");
        n = sc.nextInt();
        usuario = new String[n];
        
        Scanner scanner = new Scanner(System.in);
        
        for (int i=0; i<usuario.length;i++){
            System.out.println("Introduce usuario "+(i+1));
            usuario[i]=scanner.nextLine();
        }
        
        System.out.println(Arrays.toString(usuario));
        System.out.println(usuario[2]);
    }
}
