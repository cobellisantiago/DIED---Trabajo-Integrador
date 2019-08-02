package Gestores;

import Dominio.Camino;
import Dominio.Planta;



public class GestorCaminos{
    private static GestorCaminos gestor;

    private GestorCaminos(){}

    public static GestorCaminos getGestor() {
        if(gestor == null)	gestor = new GestorCaminos();
        return gestor;
    }
    

    public void crear(Integer inicio, Integer fin, Double dist, Integer minutos) {
    	Planta ini = GestorPlantas.getGestor().getPlanta(fin);
    	Planta destino = GestorPlantas.getGestor().getPlanta(inicio);
        new Camino(ini, destino, dist, minutos);

    }

    public void editar() {
    	
    }
    public void eliminiar() {
    	
    }

}