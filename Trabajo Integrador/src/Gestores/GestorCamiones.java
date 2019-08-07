package Gestores;

import Dominio.Camion;
import Dominio.Insumo;
import Dominio.Planta;

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

    public void crear(String marca, String modelo, String dominio, Integer anio,
                      Double costoPorKm, Integer capacidad, Boolean liquido){
        new Camion(marca, modelo, dominio, anio, costoPorKm, capacidad, liquido);
    }

    public List<String> mejorSeleccion(Integer id,List<Insumo> insumosNecesarios){
        Camion camion = getCamionPorId(id);
        ArrayList<Double> pesos = new ArrayList<>();
        ArrayList<Double> costos = new ArrayList<>();
        Boolean[] resultados = new Boolean[insumosNecesarios.size()];
        List<String> insumos = new ArrayList<>();


        for (Insumo i: insumosNecesarios) {
           pesos.add( i.getPeso());
           costos.add(i.getCosto());
        }

        resultados = camion.resolver(pesos.toArray(new Double[0]),costos.toArray(new Double[0]));

        for(int i=0;i<resultados.length;i++){
            if(resultados[i]) insumos.add(insumosNecesarios.get(i).getDescripcion());
        }


        return insumos;

    }

    public Object[][] listarCamiones(){
        ArrayList<Camion> camiones = new ArrayList<Camion>();
        camiones = Camion.getInstances();
        int cantCamiones = camiones.size();
        Object[][] listaCamiones = new Object[cantCamiones][8];
        int fila=0;
        for (Camion i : camiones) {
            listaCamiones[fila][0] = i.getId();
            listaCamiones[fila][1] = i.getMarca();
            listaCamiones[fila][2] = i.getModelo();
            listaCamiones[fila][3] = i.getDominio();
            listaCamiones[fila][4] = i.getAnio();
            listaCamiones[fila][5] = i.getCostoPorKm();
            listaCamiones[fila][6] = i.getCapacidad();
            listaCamiones[fila][7] = i.getLiquidos();
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
    
    public void editar(Integer id, String marca, String modelo, String dominio, Integer anio, Double costo, Integer capacidad, Boolean liquidos) {
    	Camion camion = getCamionPorId(id);
    	camion.setMarca(marca);
    	camion.setModelo(modelo);
    	camion.setDominio(dominio);
    	camion.setAnio(anio);
    	camion.setCostoPorKm(costo);
    	camion.setCapacidad(capacidad);
    	camion.setLiquidos(liquidos);
}


public void eliminiar(Integer id) {
		Camion.getInstances().remove(getCamionPorId(id));
}
}

