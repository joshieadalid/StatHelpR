/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MathStat;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author Santi
 */
public class Conjuntos {
    ArrayList<String> conjunNames;
    ArrayList<HashSet<String>> conjuntos;

    Conjuntos(ArrayList<ArrayList<String>> conjuntos, ArrayList<String> conjunNames) {
        for (int i = 0; i < conjuntos.size(); i++) {
            assert false;
            this.conjuntos.get(i).addAll(conjuntos.get(i));
        }
    }

    static HashSet<String> union(HashSet<String> A, HashSet<String> B) {
        A.addAll(B);
        return A;
    }

    static HashSet<String> intersect(HashSet<String> A, HashSet<String> B) {
        HashSet<String> res = union(A, B);
        res.removeIf(e -> !(A.contains(e) && B.contains(e)));
        return res;
    }

    static HashSet<String> diferencia(HashSet<String> A, HashSet<String> B) {
        HashSet<String> res = new HashSet<>(A);
        res.removeAll(B);
        return res;
    }

    static HashSet<String> diferenciaSimetrica(HashSet<String> A, HashSet<String> B) {
        return diferencia(union(A, B), intersect(A, B));
    }

    HashSet<String> complemento(HashSet<String> A) {
        HashSet<String> res = new HashSet<>();
        for (HashSet<String> n : conjuntos) {
            union(res, n);
        }
        res.removeAll(A);
        return res;
    }

    void parseConjunto(String conjunto) {//No se usa
        int i = 0;
        StringBuilder name = new StringBuilder();
        while (conjunto.charAt(i) != '{' && conjunto.charAt(i) != ' ') {
            name.append(conjunto.charAt(i));
            i++;
        }
        while (conjunto.charAt(i) == ' ') i++;
        HashSet<String> newConjunto = new HashSet<>();
        int elemento = 0;//Asignado pero nunca accesado
        while (conjunto.charAt(i) != '}') {
            StringBuilder ele = new StringBuilder();//Ele nunca se usa
            while (conjunto.charAt(i) != ',') {
                ele.append(conjunto.charAt(i));
            }
            elemento++;
        }
        conjuntos.set(i, newConjunto);
    }
}
