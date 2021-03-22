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
public class DatosAgrupados {

    static Fraction[] marcaDeClase(Fraction[][] datos) {
        int len = datos.length;
        Fraction[] x = new Fraction[len];
        for (int i = 0; i < len; i++) {
            x[i] = (datos[i][0].add(datos[i][1])).divide(Fraction.TWO);
        }
        return x;
    }

    static Fraction distClase(Fraction[][] datos, int clase) {
        return datos[clase][0].subtract(datos[clase - 1][1]);
    }

    static Fraction anchoClase(Fraction[] datos) {
        return datos[1].subtract(datos[0]);
    }

    static Fraction[][] limitesReales(Fraction[][] datos) {
        int len = datos.length;
        Fraction distanciaClase = distClase(datos, 1).divide(Fraction.TWO);
        for (int i = 1; i < datos.length - 1; i++) {
            datos[i][0] = datos[i][0].subtract(distanciaClase);
            datos[i][1] = datos[i][1].add(distanciaClase);
        }
        return datos;
    }

    static Fraction moda(Fraction[][] datos, int[] frecuencia) {
        Fraction[] marcasDeCla = marcaDeClase(datos);
        Fraction[] moda = DatosNoAgrupados.moda(marcasDeCla, frecuencia);
        Fraction[][] limitReal = limitesReales(datos);
        Fraction delta1;
        Fraction delta2;
        int indexModal = moda[1].intValue();
        Fraction limitInferior = limitReal[indexModal][0];
        Fraction anchoClas = anchoClase(datos[indexModal]);
        if (moda[1].intValue() == 0) {
            delta1 = new Fraction(frecuencia[indexModal]);
        } else {
            delta1 = new Fraction(frecuencia[indexModal] - frecuencia[indexModal - 1]);
        }

        if (moda[1].intValue() == frecuencia.length - 1) {
            delta2 = new Fraction(frecuencia[indexModal]);
        } else {
            delta2 = new Fraction(frecuencia[indexModal] - frecuencia[indexModal + 1]);
        }

        return limitInferior.add(delta1.divide(delta1.add(delta2).multiply(anchoClas)));
    }

    static Fraction mediana(Fraction[][] datos, int[] frecuencia) {
        int[] frecAcumuladas = DatosNoAgrupados.frecuenciaAcumulada(frecuencia);
        Fraction[][] limitesReales = limitesReales(datos);
        int len = datos.length;
        int n = frecAcumuladas[len];
        Fraction n2 = new Fraction(n).divide(Fraction.TWO);
        //Fraction[] marcasDeClas = marcaDeClase(datos);
        Fraction medianaPos = (new Fraction(n).add(Fraction.ONE)).divide(Fraction.TWO);

        int mdClase = 0;
        for (int i = 0; i < len; i++) {
            if (medianaPos.compareTo(new Fraction(frecAcumuladas[i])) < 0) {
                mdClase = i;
                break;
            }
        }

        Fraction anchoClas = anchoClase(datos[mdClase]);
        Fraction limInferior = datos[mdClase][0];

        int Fakm1;

        if (mdClase == 0) {
            Fakm1 = 0;
        } else {
            Fakm1 = frecAcumuladas[mdClase - 1];
        }

        int Fk = frecuencia[mdClase];

        return limInferior.add(((n2.subtract(Fakm1)).divide(Fk)).multiply(anchoClas));
    }

    boolean assertDistanciaUniforme(Fraction[][] datos, int clase) {
        int len = datos.length;
        Fraction ancho = distClase(datos, 1);
        for (int i = 2; i < len - 1; i++) {
            if (distClase(datos, i).compareTo(ancho) != 0) return false;
        }
        return true;
    }
}
