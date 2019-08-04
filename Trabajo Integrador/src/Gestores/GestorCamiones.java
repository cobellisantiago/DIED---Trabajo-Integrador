package Gestores;

import Dominio.Camion;

public class GestorCamiones {


    private static GestorCamiones gestor;

    private GestorCamiones(){ }

    public static GestorCamiones getGestor() {
        if (gestor == null) gestor = new GestorCamiones();
        return gestor;
    }

    public void crear(String marca, String modelo, Integer dominio, Integer anio,
                      Integer costoPorKm, Integer capacidad){
        new Camion(marca, modelo, dominio, anio, costoPorKm, capacidad);
    }


}

