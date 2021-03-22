/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MathStat;

import org.apache.commons.math3.fraction.Fraction;

import java.util.ArrayList;

/**
 * @author Santi
 */
public class AgrupadoList {
    public ArrayList<Fraction> datos;
    public ArrayList<Integer> frecuencias;
    public int len;

    AgrupadoList(int maxLen) {
        datos = new ArrayList<>(maxLen);
        frecuencias = new ArrayList<>(maxLen);
        len = 0;
    }

    void add(Fraction dato) {
        int i = contains(dato);
        if (i == -1) {
            datos.add(dato);
            frecuencias.add(1);
            len++;
        } else {
            frecuencias.set(i, frecuencias.get(i) + 1);
        }
    }

    int contains(Fraction dato) {

        for (int i = 0; i < len; i++) {
            if (dato.compareTo(datos.get(i)) == 0) {
                return i;
            }
        }
        return -1;
    }

    Agrupado toArray() {
        Fraction[] dato = new Fraction[len];
        int[] frecuencia = new int[len];
        for (int i = 0; i < len; i++) {
            dato[i] = datos.get(i);
            frecuencia[i] = frecuencias.get(i);
        }
        return new Agrupado(dato, frecuencia);
    }
}
