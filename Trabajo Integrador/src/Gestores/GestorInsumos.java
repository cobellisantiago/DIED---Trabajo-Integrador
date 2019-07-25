package Gestores;

import Dominio.Insumo;
import Dominio.Unidades.UnidadDeMedida;
import Estructuras.ArbolBinarioBusqueda;
import jdk.nashorn.internal.ir.annotations.Ignore;

import java.util.ArrayList;
import java.util.List;

public class GestorInsumos{
    private static GestorInsumos gestor;

    private GestorInsumos(){}

    public static GestorInsumos getGestor() {
        if(gestor == null)	gestor = new GestorInsumos();
        return gestor;
    }

    public void crear(String descrip, Double costoActual, Double pesoEnKg, Boolean refrigeracion) {
        new Insumo(descrip, costoActual, pesoEnKg, refrigeracion);

    }

    public Double getStockTotal(Insumo i) {
    	//Se necesita realizar la implementacion de la relacion entre las plantas y los insumos.
    	
    	return new Double(0);
    }
    
    public Object[][] buscarNombre(String nombre) {
        List<Insumo> insumos = Insumo.getInstances();
        int cantInsumos = insumos.size();
        Object[][] listaInsumos = new Object[cantInsumos][3];
        int fila=0;
        int col;
        for (Insumo i: insumos) {
            col=0;
            if(i.getDescripcion().equals(nombre)){
                listaInsumos[fila][col]= i.getId();
                col=1;
                listaInsumos[fila][col]= i.getDescripcion();
                col=2;
                listaInsumos[fila][col]= 0.0;
                fila++;
            }
        }

        return listaInsumos;

    }
    
    public Object[][] buscarCosto(Double minimo, Double maximo) {
    	List<Insumo> insumos = Insumo.getInstances();
        int cantInsumos = insumos.size();
        
    	ArbolBinarioBusqueda<Insumo> insumosArbol = new ArbolBinarioBusqueda<Insumo>(insumos.get(0));
    	for(int i = 1 ; i < cantInsumos ; i++)	insumosArbol.agregarCosto(insumos.get(i));
    	
    	insumos = insumosArbol.rangoCosto(new Insumo(minimo, new Double(0)), new Insumo(maximo, new Double(0)));
    	
        Object[][] listaInsumos = new Object[cantInsumos][3];
    	
    	int fila = 0;
    	for(Insumo i : insumos) {
            listaInsumos[fila][0]= i.getId();
            listaInsumos[fila][1]= i.getDescripcion();
            listaInsumos[fila][2]= 0.0;
            fila++;
    	}
    	

        
        return listaInsumos;
    }

    public Object[][] buscarStock(Double minimo, Double maximo) {
    	List<Insumo> insumos = Insumo.getInstances();
        int cantInsumos = insumos.size();
        
    	ArbolBinarioBusqueda<Insumo> insumosArbol = new ArbolBinarioBusqueda<Insumo>(insumos.get(0));
    	for(int i = 1 ; i < cantInsumos ; i++)	insumosArbol.agregar(insumos.get(i));
    	
    	
    	
        Object[][] listaInsumos = new Object[cantInsumos][3];
        
        return listaInsumos;
    }
    
    public Object[][] listarInsumos(){ //Utilizado para mostrar los insumos en la tabla de insumos
        ArrayList<Insumo> insumos = new ArrayList<Insumo>();
        insumos = Insumo.getInstances();
        int cantInsumos = insumos.size();
        Object[][] listaInsumos = new Object[cantInsumos][3];
        int col;
        int fila =0;
        for(Insumo i : insumos){
            col=0;
            listaInsumos[fila][col]= i.getId();
            col=1;
            listaInsumos[fila][col]= i.getDescripcion();
            col=2;
            listaInsumos[fila][col]= 0.0;
            fila++;
        }

        return listaInsumos;
    }


    public void editar() {

    }
    public void eliminiar() {

    }

}