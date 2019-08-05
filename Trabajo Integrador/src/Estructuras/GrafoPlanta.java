package Estructuras;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Dominio.Insumo;
import Dominio.Planta;
import Dominio.Camino;

public class GrafoPlanta extends Grafo<Planta> {

    public void imprimirDistanciaAdyacentes(Planta inicial) {
        List<Planta> adyacentes = super.getAdyacentes(inicial);
        for(Planta unAdyacente: adyacentes) {
            Arista<Planta> camino = super.buscarArista(inicial, unAdyacente);
            System.out.println("camino de "+inicial.getNombre()+" a "+
                    unAdyacente.getNombre()+ " tiene valor de "+ camino.getValor() );
        }
    }
    // a
    public Planta buscarPlanta(Planta inicial, Insumo i, Integer saltos) {

        if(inicial.necesitaInsumo(i)) return inicial;
        List<Planta> listaPlantas;
        listaPlantas = getAdyacentes(inicial);
        for(int a=saltos;a>=0;a--) {
            for (Planta p : listaPlantas) {
                if (p.necesitaInsumo(i)) return p;
            }
            List<Planta> auxLista = new ArrayList<>();
            for (Planta p : listaPlantas) {
                auxLista.addAll(getAdyacentes(p));
            }
            listaPlantas = auxLista;
        }

        return null;

    }
    // b
    public Planta buscarPlanta(Planta inicial, Insumo i) {

        return null;
    }
    // c
    public Planta buscarPlanta(Insumo i) {

        return null;
    }

    public ArrayList<Planta> getMejorCamino(){
    	return null;
    }
    
    /*@Override
    public void conectar(Planta n1, Planta n2, Number valor) {
        Vertice<Planta> n = new Vertice<>(n1);
        Vertice<Planta> m = new Vertice<>(n2);
        super.conectar(n1, n2,valor);
    }*/
}




