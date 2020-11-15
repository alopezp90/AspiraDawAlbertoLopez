package programa;

/**
 * @author Alberto López Puertas
 * <alopezp90@gmail.com>
 */
public class Test {

    public static double bateria = 13;

    public static void main(String[] args) {

        modoCarga();

    }

    public static void modoCarga() {

        System.out.println("Cargando...Bateria al " + bateria + "%");

        //Pasa el valor de bateria al siguiente multiplo entero de 5
        bateria = Math.ceil(bateria);
        while (bateria % 5 != 0) {
            bateria++;
        }
        int valorBase = (int) bateria;
        //Imprime valores de 5 en 5 hasta 100%
        for (int i = 0; i < (100 - valorBase) / 5; i++) {
            System.out.println("Cargando...Bateria al " + bateria + "%");
            bateria += 5;
        }
        System.out.println("Cargando...Bateria al " + bateria + "%");
    }
}
