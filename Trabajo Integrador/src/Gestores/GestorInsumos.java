package Gestores;

import Dominio.Insumo;
import Dominio.Unidades.UnidadDeMedida;

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
    public void buscar() {
        List<Insumo> insumos = Insumo.getInstances();

    }
    public String[][] listarInsumos(){ //Utilizado para mostrar los insumos en la tabla de insumos
        String[][] listaInsumos;



        return listaInsumos;
    }
    public void editar() {

    }
    public void eliminiar() {

    }

}