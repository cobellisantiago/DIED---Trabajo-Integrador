package Gestores;

import Dominio.Camion;
import Dominio.Insumo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GestorCamiones {


    private static GestorCamiones gestor;

    private GestorCamiones(){ }

    public static GestorCamiones getGestor() {
        if (gestor == null) gestor = new GestorCamiones();
        return gestor;
    }

    public void crear(String marca, String modelo, Double dominio, Integer anio,
                      Double costoPorKm, Integer capacidad, Boolean liquido){
        new Camion(marca, modelo, dominio, anio, costoPorKm, capacidad, liquido);
    }

    public List<String> mejorSeleccion(Integer id){
        Camion camion = getCamionPorId(id);
        ArrayList<Double> pesos = new ArrayList<>();
        ArrayList<Double> costos = new ArrayList<>();
        List<Boolean> resultados = new ArrayList<>();

        for (Insumo i: Insumo.getInstances()) {

           pesos.add( i.getPeso());
           costos.add(i.getCosto());
        }

        /*resultados = Arrays.asList(camion.resolver(pesos.toArray(new Double[0]),costos.toArray(new Double[0])));
        for (:
             ) {

        }*/

        return null;

    }

    public Object[][] listarCamiones(){
        ArrayList<Camion> camiones = new ArrayList<Camion>();
        camiones = Camion.getInstances();
        int cantCamiones = camiones.size();
        Object[][] listaCamiones = new Object[cantCamiones][4];
        int col;
        int fila=0;
        for (Camion i : camiones) {
            col = 0; listaCamiones[fila][col] = i.getId();
            col = 1; listaCamiones[fila][col] = i.getCostoPorKm();
            col = 2; listaCamiones[fila][col] = i.getCapacidad();
            col = 3; listaCamiones[fila][col] = i.getLiquidos();
            fila++;
        }

        return listaCamiones;


    }

    public Camion getCamionPorId(Integer id){
        for (Camion c: Camion.getInstances()
             ) {
            if(c.getId() == id) return c;
        }
        return null;
    }
}

