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
    
    public Camino getCamino(Planta inicial, Planta fin) {
    	for(Camino c : Camino.getInstances())	if(c.getInicio().getValor().equals(inicial) && c.getFin().getValor().equals(fin))	return c;
    	return null;
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
    
    public Object[][] listarCaminos(){
        ArrayList<Camino> caminos = Camino.getInstances();
        int cantPlantas = caminos.size();
        
        Object[][] listaPlantas = new Object[cantPlantas][5];
        
        int fila =0;
        for(Camino i : caminos){
            listaPlantas[fila][0]= i.getInicio().getValor().getId();
            listaPlantas[fila][1]= i.getFin().getValor().getId();
            listaPlantas[fila][2]= i.getDistancia();
            listaPlantas[fila][3]= i.getDuracion();
            listaPlantas[fila][4]= i.getPesoMaximo();
            fila++;
        }

        return listaPlantas;
    }

    // ---- ------------ NO ENTIENDO NADA ------------------------ --------

    public Double obtenerFlujoMaximo(){
        Double resultado=0.0;
        resultado=Camino.getGrafoPlanta().flujoMaximo(Camino.getGrafoPlanta().getNodo(Planta.getPuerto()),Camino.getGrafoPlanta().getNodo(Planta.getFinal()));

        return resultado;
    }

    public void editar(Integer idPlantaO, Integer idPlantaF, Double dist, Integer dur, Double pesoM) {
    	Planta plantaO = GestorPlantas.getGestor().getPlanta(idPlantaO);
		Planta plantaF = GestorPlantas.getGestor().getPlanta(idPlantaF);
		Camino camino = getCamino(plantaO, plantaF);
		camino.setDistancia(dist);
		camino.setDuracion(dur);
		camino.setPesoMaximo(pesoM);
    }


    public void eliminiar(Integer idPlantaO, Integer idPlantaF) {
		Planta plantaO = GestorPlantas.getGestor().getPlanta(idPlantaO);
		Planta plantaF = GestorPlantas.getGestor().getPlanta(idPlantaF);
		Camino.getInstances().remove(getCamino(plantaO, plantaF));
    }

}