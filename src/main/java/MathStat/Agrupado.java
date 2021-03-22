/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MathStat;

import org.apache.commons.math3.fraction.Fraction;

/**
 * @author Santi
 */
public class Agrupado {
    public Fraction[] datos;
    public int[] frecuencias;

    Agrupado(Fraction[] datos, int[] frecuencias) {
        this.datos = datos;
        this.frecuencias = frecuencias;
    }
}
