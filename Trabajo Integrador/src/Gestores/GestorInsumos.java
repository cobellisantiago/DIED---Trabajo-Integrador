package Gestores;

import Dominio.Insumo;
import Dominio.Planta;
import Dominio.Stock;
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

    /*public Double getStockTotal(Insumo ins) {
    	ArrayList<Stock> stock = Stock.getInstances();
    	Double result = new Double(0);
    	
    	for(Stock i : stock) {
    		if(i.getInsumo().equals(ins))	result += i.getCantidad();
    	}

    	return result;
    }*/
    
    public Object[][] buscarNombre(String nombre) {
    	List<Insumo> insumos = Insumo.getInstances();
        int cantInsumos = insumos.size();
        
    	ArbolBinarioBusqueda<Insumo> insumosArbol = new ArbolBinarioBusqueda<Insumo>(insumos.get(0));
    	for(int i = 1 ; i < cantInsumos ; i++)	insumosArbol.agregar(insumos.get(i), Insumo.comparadorNombre);
    	
    	insumos = insumosArbol.rango(new Insumo(new Double(0), new Double(0), nombre), new Insumo(new Double(0), new Double(0), nombre), Insumo.comparadorNombre);
    	
        Object[][] listaInsumos = new Object[cantInsumos][3];
        
        int fila = 0;
    	for(Insumo i : insumos) {
            listaInsumos[fila][0]= i.getId();
            listaInsumos[fila][1]= i.getDescripcion();
            listaInsumos[fila][2]= i.getStockTotal();
            fila++;
    	}
    	
        return listaInsumos;
    }
    
    public Object[][] buscarCosto(Double minimo, Double maximo) {
    	List<Insumo> insumos = Insumo.getInstances();
        int cantInsumos = insumos.size();
        
    	ArbolBinarioBusqueda<Insumo> insumosArbol = new ArbolBinarioBusqueda<Insumo>(insumos.get(0));
    	for(int i = 1 ; i < cantInsumos ; i++)	insumosArbol.agregar(insumos.get(i), Insumo.comparadorCosto);
    	
    	insumos = insumosArbol.rango(new Insumo(minimo, new Double(0), ""), new Insumo(maximo, new Double(0), ""), Insumo.comparadorCosto);
    	
        Object[][] listaInsumos = new Object[cantInsumos][3];
    	
        int fila = 0;
    	for(Insumo i : insumos) {
            listaInsumos[fila][0]= i.getId();
            listaInsumos[fila][1]= i.getDescripcion();
            listaInsumos[fila][2]= i.getStockTotal();
            fila++;
    	}
        
        return listaInsumos;
    }

    public Object[][] buscarStock(Double minimo, Double maximo) {
    	List<Insumo> insumos = Insumo.getInstances();
        int cantInsumos = insumos.size();
        
    	ArbolBinarioBusqueda<Insumo> insumosArbol = new ArbolBinarioBusqueda<Insumo>(insumos.get(0));
    	for(int i = 1 ; i < cantInsumos ; i++)	insumosArbol.agregar(insumos.get(i), Insumo.comparadorStock);
    	
    	insumos = insumosArbol.rango(new Insumo(new Double(0), minimo, ""), new Insumo(new Double(0), maximo, ""), Insumo.comparadorStock);
    	
        Object[][] listaInsumos = new Object[cantInsumos][3];
        
        int fila = 0;
    	for(Insumo i : insumos) {
            listaInsumos[fila][0]= i.getId();
            listaInsumos[fila][1]= i.getDescripcion();
            listaInsumos[fila][2]= i.getStockTotal();
            fila++;
    	}
        
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
            listaInsumos[fila][col]= i.getStockTotal();
            fila++;
        }

        return listaInsumos;
    }

    public Insumo getInsumo(Integer id) {
    	ArrayList<Insumo> insumos = Insumo.getInstances();
    	for(Insumo i : insumos) {
    		if(i.getId().equals(id))	return i;
    	}
    	return null;
    }
    
    public void agregarStock(Insumo ins, Double cant) {
    	ins.setStockTotal(ins.getStockTotal() + cant);
    }
    
    public void editar() {

    }
    public void eliminiar() {

    }

}