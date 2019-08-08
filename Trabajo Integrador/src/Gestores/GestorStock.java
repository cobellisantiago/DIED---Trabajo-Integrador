package Gestores;

import Dominio.Insumo;
import Dominio.Planta;
import Dominio.Stock;
import Dominio.Unidades.UnidadDeMedida;
import Estructuras.ArbolBinarioBusqueda;
import jdk.nashorn.internal.ir.annotations.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        	Stock nuevo = new Stock(cant, puntoPed, insu, plan);
        	GestorInsumos.getGestor().agregarStock(insu, cant);
        	GestorPlantas.getGestor().agregarStock(nuevo);
        	return true;
        }
        return false;
    }

    public List<Stock> insumosFaltantes(){
        //List<Insumo> insumosFaltantes = new ArrayList<>();
        List<Stock> insumosFaltantes = new ArrayList<>();
        for(Stock s: Stock.getInstances()){
            if(s.getCantidad()<=s.getPuntoPedido()) insumosFaltantes.add(s);
        }
        return insumosFaltantes;
    }
    
    public Object[][] listarStock(){
        ArrayList<Stock> stock = new ArrayList<Stock>();
        stock = Stock.getInstances();
        int cantStock = stock.size();
        
        Object[][] listaStock = new Object[cantStock][4];
        
        int fila =0;
        for(Stock i : stock){
            listaStock[fila][0]= i.getPlanta().getId();
            listaStock[fila][1]= i.getInsumo().getId();
            listaStock[fila][2]= i.getCantidad();
            listaStock[fila][3]= i.getPuntoPedido();
            fila++;
        }

        return listaStock;
    }

    public Stock getStock(Integer idPlanta, Integer idInsumo) {
    	for(Stock s : Stock.getInstances())	if(s.getPlanta().getId().equals(idPlanta) && s.getInsumo().getId().equals(idInsumo))	return s;
    	return null;
    }

    public void editar(Integer idPlanta, Integer idInsumo, Double cant, Integer puntoPed) {
    	Stock s = getStock(idPlanta, idInsumo);
    	s.setCantidad(cant);
    	s.setPuntoPedido(puntoPed);
    }
    public void eliminiar(Integer idPlanta, Integer idInsumo) {
    	Stock.getInstances().remove(getStock(idPlanta, idInsumo));
    }

}