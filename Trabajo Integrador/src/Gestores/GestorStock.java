package Gestores;

import Dominio.Insumo;
import Dominio.Planta;
import Dominio.Stock;
import Dominio.Unidades.UnidadDeMedida;
import Estructuras.ArbolBinarioBusqueda;
import jdk.nashorn.internal.ir.annotations.Ignore;

import java.util.ArrayList;
import java.util.List;

public class GestorStock{
    private static GestorStock gestor;

    private GestorStock(){}

    public static GestorStock getGestor() {
        if(gestor == null)	gestor = new GestorStock();
        return gestor;
    }

    public Boolean crear(Double cant, Integer puntoPed, Integer ins, Integer plant) {
    	Insumo insu = GestorInsumos.getGestor().getInsumo(ins);
    	Planta plan = GestorPlantas.getGestor().getPlanta(plant);
    	
        if(!(insu == null || plan == null)) {
        	new Stock(cant, puntoPed, insu, plan);
        	return true;
        }
        return false;
    }
    
    public Object[][] listarStock(){
        ArrayList<Stock> stock = new ArrayList<Stock>();
        stock = Stock.getInstances();
        int cantStock = stock.size();
        
        Object[][] listaStock = new Object[cantStock][3];
        
        int fila =0;
        for(Stock i : stock){
            listaStock[fila][0]= i.getPlanta().getId();
            listaStock[fila][1]= i.getInsumo().getId();
            listaStock[fila][2]= i.getCantidad();
            fila++;
        }

        return listaStock;
    }


    public void editar() {

    }
    public void eliminiar() {

    }

}