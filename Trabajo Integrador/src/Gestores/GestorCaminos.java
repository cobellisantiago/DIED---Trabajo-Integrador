package Gestores;

import Dominio.Camino;
import Dominio.Planta;
import Estructuras.Arista;
import Estructuras.GrafoPlanta;
import Estructuras.Vertice;

import java.util.ArrayList;
import java.util.List;


public class GestorCaminos extends GrafoPlanta{
    private static GestorCaminos gestor;

    private GestorCaminos(){}

    public static GestorCaminos getGestor() {
        if(gestor == null)	gestor = new GestorCaminos();
        return gestor;
    }
    

    public void crear(Integer inicio, Integer fin, Double dist, Integer minutos,Double peso) {

    	Planta ini = GestorPlantas.getGestor().getPlanta(inicio);
    	Planta destino = GestorPlantas.getGestor().getPlanta(fin);
        new Camino(ini, destino, dist, minutos,peso);

        System.out.println(Camino.getGrafoPlanta().getNodo(ini));
        if(Camino.getGrafoPlanta().getNodo(ini) == null){
            Camino.getGrafoPlanta().addNodo(ini);
           // System.out.println("Agrego inicio: "+inicio);
        }

        if(Camino.getGrafoPlanta().getNodo(destino) == null){
            Camino.getGrafoPlanta().addNodo(destino);
           // System.out.println("Agrego fin: "+fin);
        }
        Camino.getGrafoPlanta().conectar(ini,destino,peso);


    }

    // ---- ------------ NO ENTIENDO NADA ------------------------ --------

    public Double obtenerFlujoMaximo(){
        Double resultado=0.0;
        resultado=Camino.getGrafoPlanta().flujoMaximo(Camino.getGrafoPlanta().getNodo(Planta.getPuerto()),Camino.getGrafoPlanta().getNodo(Planta.getFinal()));

        return resultado;
    }

    public void editar() {
    	
    }
    public void eliminiar() {
    	
    }

}