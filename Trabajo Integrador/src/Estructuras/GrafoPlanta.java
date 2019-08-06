package Estructuras;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Dominio.Insumo;
import Dominio.Planta;
import Gestores.GestorCaminos;
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

    public List<Vertice<Planta>> mejorCaminoLongitud(ArrayList<Planta> listaPlantas){
    	
    	ArrayList<Vertice<Planta>> listaVertices = new ArrayList<Vertice<Planta>>();
    	List<List<Vertice<Planta>>> caminos = this.caminos(getNodo(Planta.getPuerto()), getNodo(Planta.getFinal()));
    	
    	for(Vertice<Planta> ver : getVertices()) {
    		for(Planta pl : listaPlantas) {
    			if(ver.getValor().equals(pl)) {
    				listaVertices.add(ver);
    				break;
    			}
    		}
    	}
    	
    	for(List<Vertice<Planta>> camino : caminos) {
    			if(!camino.containsAll(listaVertices)) {
    				caminos.remove(camino);
    			}
    	}
    	
    	Double mejorLong = new Double(0);
    	List<Vertice<Planta>> mejorCamino = null;
    	
    	for(List<Vertice<Planta>> camino : caminos) {
    		Double longitudAux = new Double(0);
			for(int i = 0 ; i < camino.size()-1 ; i++) {
				longitudAux += GestorCaminos.getGestor().getCamino(camino.get(i).getValor(), camino.get(i+1).getValor()).getDistancia();
			}
			if(longitudAux < mejorLong) {
				mejorLong = longitudAux;
				mejorCamino = camino;
			}
    	}
    	
    	
    	
    	return mejorCamino;
    }
    
    /*@Override
    public void conectar(Planta n1, Planta n2, Number valor) {
        Vertice<Planta> n = new Vertice<>(n1);
        Vertice<Planta> m = new Vertice<>(n2);
        super.conectar(n1, n2,valor);
    }*/
}




