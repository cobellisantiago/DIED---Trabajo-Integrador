package Gestores;

import Dominio.Camion;

import java.util.ArrayList;

public class GestorCamiones {


    private static GestorCamiones gestor;

    private GestorCamiones(){ }

    public static GestorCamiones getGestor() {
        if (gestor == null) gestor = new GestorCamiones();
        return gestor;
    }

    public void crear(String marca, String modelo, Double dominio, Integer anio,
                      Double costoPorKm, Double capacidad, Boolean liquido){
        new Camion(marca, modelo, dominio, anio, costoPorKm, capacidad, liquido);
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


}

