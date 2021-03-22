package MathStat;

import org.apache.commons.math3.fraction.Fraction;
import org.apache.commons.math3.fraction.FractionFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Santi
 */
public class StatHelpR {
    public static void main(String[] args) {
        Scanner teclitas = new Scanner(System.in);
        boolean contin = true;
        ArrayList<Fraction> valores = new ArrayList<>();
        ArrayList<Integer> frecuencias = new ArrayList<>();
        FractionFormat ff = new FractionFormat();
        while (contin) {
            System.out.print("[valor]-[frecuencia] (para terminar use 'fin'): ");
            String get = teclitas.nextLine();
            if (get.equals("fin")) {
                contin = false;
                continue;
            }
            String[] vals = get.split("-");
            valores.add(ff.parse(vals[0]));
            frecuencias.add(Integer.parseInt(vals[1]));
        }
        Fraction[] datos = valores.toArray(new Fraction[0]);
        int[] frec = new int[datos.length];
        for (int i = 0; i < datos.length; i++) {
            frec[i] = frecuencias.get(i);
        }

        Fraction[] varianza = DatosNoAgrupados.mediaAritmeticaFrecuencial(datos, frec);
        System.out.println("" + Arrays.toString(varianza));
        System.out.println("fx: " + varianza[0]);
        System.out.println("fx²: " + varianza[1]);
        System.out.println("Σfx: " + varianza[2]);
        System.out.println("Media ari: " + varianza[3]);
    }
}
