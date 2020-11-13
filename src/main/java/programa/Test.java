package programa;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import static programa.AspiraDaw.ALERT;

/**
 * @author Alberto López Puertas
 * <alopezp90@gmail.com>
 */
public class Test {

    public static void main(String[] args) {
        String mensaje = "Este es un mensaje de error";
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
