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
public class DatosNoAgrupados {

    static Fraction[] mediaAritmeticaFrecuencial(Fraction[] datos, int[] frecuencias) {
        int len = datos.length;
        int n = 0;
        Fraction[] fx = new Fraction[len + 2];
        fx[len] = new Fraction(0);
        for (int i = 0; i < len; i++) {
            fx[i] = datos[i].multiply(frecuencias[i]);
            fx[len] = fx[len].add(fx[i]);
            n += frecuencias[i];
        }
        fx[len + 1] = fx[len].divide(n);

        return fx;
    }

    static int[] frecuenciaAcumulada(int[] frecuencias) {
        int len = frecuencias.length;
        int[] frecuenciasAcumuladas = new int[len];
        for (int frecuencia : frecuencias) {
            frecuenciasAcumuladas[len] += frecuencia;//Indice del arreglo se sale de los límites
        }
        return frecuenciasAcumuladas;
    }

    static double[] mediaGeometricaFrecuencial(double[] datos, int[] frecuencias) {//No se usa
        int len = datos.length;
        double[] xfn = new double[len + 1];
        int n = 1;
        for (int i = 0; i < len; i++) {
            n += frecuencias[i];
        }
        for (int i = 0; i < len; i++) {
            xfn[i] = Math.pow(datos[i], (double) frecuencias[i] / n);
            xfn[len] *= xfn[i];
        }
        return xfn;
    }

    static Fraction[] mediaHarmonicaFrecuencial(Fraction[] datos, int[] frecuencias) {//No se usa
        int len = datos.length;
        int n = 0;
        Fraction[] fx = new Fraction[len + 2];
        fx[len] = new Fraction(0);
        for (int i = 0; i < len; i++) {
            fx[i] = new Fraction(frecuencias[i]).divide(datos[i]);
            fx[len] = fx[len].add(fx[i]);
            n += frecuencias[i];
        }
        fx[len + 1] = new Fraction(n).divide(datos[len]);//Indice del arreglo se sale de los límites

        return fx;
    }

    static Fraction mediana(Fraction[] datos, int[] frecuencias) {//No se usa
        int len = datos.length;
        int[] frecAcum = frecuenciaAcumulada(frecuencias);
        boolean mediaFlag = false;
        int mPos = frecAcum[len] / 2;
        if (mPos % 2 == 0) mediaFlag = true;
        int i = 0;
        while (frecAcum[i] < mPos) {
            i++;
        }
        if (mediaFlag) {
            if (frecAcum[i] == mPos) {
                return (datos[i].add(datos[i + 1])).divide(Fraction.TWO);
            }
        }
        return datos[i];

    }

    static Fraction[] moda(Fraction[] datos, int[] frecuencias) {
        int grande = 0;
        int iG = 0;
        for (int i = 0; i < datos.length; i++) {
            if (frecuencias[i] > grande) {
                grande = frecuencias[i];
                iG = i;
            }
        }
        return new Fraction[]{datos[iG], new Fraction(grande)};
    }

    static Fraction[] varianza(Fraction[] datos, int[] frecuencias) {//No se usa
        int len = datos.length;
        Fraction[] varianza = new Fraction[len + 4];
        System.arraycopy(mediaAritmeticaFrecuencial(datos, frecuencias), 0, varianza, 0, len + 2);
        varianza[len + 2] = new Fraction(0);
        for (int i = 0; i < len; i++) {
            varianza[i] = varianza[i].multiply(datos[i]);
            varianza[len + 2] = varianza[len + 2].add(varianza[i]);
        }
        if (len <= 30) {
            varianza[len + 3] = (varianza[len + 2].subtract((varianza[len].multiply(varianza[len])).divide(len))).divide(len - 1);
        } else {
            varianza[len + 3] = varianza[len + 2].divide(len).subtract(varianza[len + 1].multiply(varianza[len + 1]));
        }
        return varianza;
    }

    static double[] toDoubleArr(Fraction[] datos) {//No se usa
        int len = datos.length;
        double[] res = new double[len];
        for (int i = 0; i < len; i++) {
            res[i] = datos[i].doubleValue();
        }
        return res;
    }

    static Agrupado agruparDatos(Fraction[] datos) {//No se usa
        boolean desorden = false;//No se usa
        AgrupadoList frecuencial = new AgrupadoList(datos.length);
        for (Fraction dato : datos) {
            frecuencial.add(dato);
        }
        return frecuencial.toArray();
    }

    static Fraction[] fractionSort(Fraction[] datos) {//No se usa
        boolean desorden = false;//No se usa
        return datos;
    }
}
