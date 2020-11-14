package programa;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import static programa.AspiraDaw.ALERT;

/**
 * @author Alberto López Puertas
 * <alopezp90@gmail.com>
 */
public class Test {

    public static void main(String[] args) {

        LocalDateTime anterior = LocalDateTime.of(2020, 10, 13, 12, 0);
        LocalDateTime ahora = LocalDateTime.now();

        LocalDateTime tempDateTime = LocalDateTime.from(anterior);

        long years = tempDateTime.until(ahora, ChronoUnit.YEARS);
        tempDateTime = tempDateTime.plusYears(years);

        long months = tempDateTime.until(ahora, ChronoUnit.MONTHS);
        tempDateTime = tempDateTime.plusMonths(months);

        long days = tempDateTime.until(ahora, ChronoUnit.DAYS);
        tempDateTime = tempDateTime.plusDays(days);

        long hours = tempDateTime.until(ahora, ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusHours(hours);

        long minutes = tempDateTime.until(ahora, ChronoUnit.MINUTES);

        System.out.println(years + " years "
                + months + " months "
                + days + " days "
                + hours + " hours "
                + minutes + " minutes ");
    }
}
