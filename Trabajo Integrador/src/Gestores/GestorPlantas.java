package Gestores;

import Dominio.Insumo;
import Dominio.Planta;
import Dominio.Unidades.UnidadDeMedida;
import Estructuras.ArbolBinarioBusqueda;
import jdk.nashorn.internal.ir.annotations.Ignore;

import java.util.ArrayList;
import java.util.List;

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
    
    public Object[][] listarPlantas(){
        ArrayList<Planta> plantas = new ArrayList<Planta>();
        plantas = Planta.getInstances();
        int cantPlantas = plantas.size();
        
        Object[][] listaPlantas = new Object[cantPlantas][3];
        
        int fila =0;
        for(Planta i : plantas){
            listaPlantas[fila][0]= i.getId();
            listaPlantas[fila][1]= i.getNombre();
            listaPlantas[fila][2]= 0.0;
            fila++;
        }

        return listaPlantas;
    }

    public Planta getPlanta(Integer id) {
    	ArrayList<Planta> plantas = Planta.getInstances();
    	for(Planta i : plantas) {
    		if(i.getId().equals(id))	return i;
    	}
    	return null;
    }

    public void editar() {

    }


    public void eliminiar() {

    }

}