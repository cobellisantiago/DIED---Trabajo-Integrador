package Dominio;

import java.util.ArrayList;

import Estructuras.Arista;
import Estructuras.GrafoPlanta;
import Estructuras.Vertice;
import GUI.PantallaBase;
import GUI.Plantas.PantallaMapaPlanta;

public class Camino extends Arista<Planta> {

	private static ArrayList<Camino> instances = new ArrayList<Camino>();
    private static GrafoPlanta grafoPlanta = new GrafoPlanta();
    private Double distancia; //Medida en Km
    private Integer duracion; //Medido en minutos
    private Double pesoMaximo; //Medido en toneladas


    @SuppressWarnings("unused")
	private Camino() {}
    
    public Camino(Planta inicio, Planta fin, Double dist, Integer minutos, Double pesoSoportado){

    	super(new Vertice<Planta>(inicio), new Vertice<Planta>(fin));
        distancia = dist;
        duracion = minutos;
        pesoMaximo = pesoSoportado;
        instances.add(this);

    }

    public static ArrayList<Camino> getInstances(){
    	return instances;
    }

    public static GrafoPlanta getGrafoPlanta() {
        return grafoPlanta;
    }
    
    public Double getDistancia() {
    	return distancia;
    }
    
    public void setDistancia(Double distancia) {
    	this.distancia = distancia;
    }
    
    public Integer getDuracion() {
    	return duracion;
    }
    
    public void setDuracion(Integer duracion) {
    	this.duracion = duracion;
    }
    
    public Double getPesoMaximo() {
    	return pesoMaximo;
    }
    
    public void setPesoMaximo(Double pesoMaximo) {
    	this.pesoMaximo = pesoMaximo;
    }
}
