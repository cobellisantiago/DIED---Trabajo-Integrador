package Gestores;

import Dominio.Camino;
import Dominio.Insumo;
import Dominio.Planta;
import Dominio.Stock;
import Dominio.Unidades.UnidadDeMedida;
import Estructuras.ArbolBinarioBusqueda;
import Estructuras.GrafoPlanta;
import Estructuras.Vertice;
import jdk.nashorn.internal.ir.annotations.Ignore;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.function.IntUnaryOperator;

public class GestorPlantas{
    private static GestorPlantas gestor;

    private GestorPlantas(){}

    public static GestorPlantas getGestor() {
        if(gestor == null)	gestor = new GestorPlantas();
        return gestor;
    }

    public void crear(String nombre) {
        new Planta(nombre);

    }

    public Object[][] pageRank(){
        GrafoPlanta grafo = Camino.getGrafoPlanta();

        List<Vertice<Planta>> vertices = grafo.getVertices();
        vertices.sort((v1, v2) -> grafo.gradoEntrada(v2).compareTo(grafo.gradoEntrada(v1)));

        Object[][] listaPlantas = new Object[Planta.getInstances().size()][3];
        for(int i = 0 ; i < Planta.getInstances().size() ; i++){
            listaPlantas[i][0]= vertices.get(i).getValor().getId();
            listaPlantas[i][1]= vertices.get(i).getValor().getNombre();
            listaPlantas[i][2]= grafo.gradoEntrada(vertices.get(i));
        }

        return listaPlantas;
    }
    
    public Object[][] listarPlantas(){
        ArrayList<Planta> plantas = new ArrayList<Planta>();
        plantas = Planta.getInstances();
        int cantPlantas = plantas.size();
        
        Object[][] listaPlantas = new Object[cantPlantas][2];
        
        int fila =0;
        for(Planta i : plantas){
            listaPlantas[fila][0]= i.getId();
            listaPlantas[fila][1]= i.getNombre();
            fila++;
        }

        return listaPlantas;
    }

    public Object[][] listaFaltanteStock(){
        //"Id-Insumo","Nombre","Cantidad Faltante","Id-Planta","Nombre"

        /*ArrayList<Planta> plantas = new ArrayList<Planta>();
        plantas = Planta.getInstances();*/
        LinkedHashSet<Planta> plantas = new LinkedHashSet<>();
        LinkedHashSet<Insumo> insumos = new LinkedHashSet<>();
        int cantLineas = 0;

        for (Planta p:Planta.getInstances()) {
            for (Insumo i: Insumo.getInstances()) {
                if(p.necesitaInsumo(i)){
                    plantas.add(p);
                    insumos.add(i);
                    cantLineas++;
                }
            }

        }


        Object[][] listaPlantas = new Object[cantLineas][5];

        int fila =0;
        for(Planta p : plantas){
            for (Insumo i:insumos) {
                if(p.necesitaInsumo(i)){
                    listaPlantas[fila][0]= i.getId();
                    listaPlantas[fila][1]= i.getDescripcion();
                    listaPlantas[fila][2]= stockNecesario(p,i);
                    listaPlantas[fila][3]= p.getId();
                    listaPlantas[fila][4]= p.getNombre();
                    fila++;
                    System.out.println("Insumo: "+i+" Planta: "+p);
                }
            }


        }
        return listaPlantas;
    }

    public void agregarStock(Stock stock){

        stock.getPlanta().agregarStock(stock);
    }

    public Double stockNecesario(Planta p, Insumo i){
        for (Stock s: p.getListaStocks()) {
            if(s.getInsumo().equals(i))return (s.getPuntoPedido()-s.getCantidad());
        }
        return 0.0;
    }

    public Planta getPlanta(Integer id) {
    	ArrayList<Planta> plantas = Planta.getInstances();
    	for(Planta i : plantas) {
    		if(i.getId().equals(id))	return i;
    	}
    	return null;
    }

    public void editar(Integer id, String nombre) {
    		getPlanta(id).setNombre(nombre);
    }

    public void eliminiar(Integer id) {
    		Planta.getInstances().remove(getPlanta(id));
    }

}